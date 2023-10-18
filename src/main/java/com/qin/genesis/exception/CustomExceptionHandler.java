package com.qin.genesis.exception;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.sql.SQLException;
import java.util.UUID;

@Component
public class CustomExceptionHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(CustomExceptionHandler.class);

    private final static String EMPTY_STACK_TRACE = "";

    private ApplicationContext applicationContext;


    public CustomExceptionHandler(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public ResponseEntity<ExceptionResult> handle(Throwable t) {
        ExceptionResult exceptionResult = null;
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        String stackTrace = ExceptionUtils.getStackTrace(t);
        String exceptionId = UUID.randomUUID().toString();
        if (t != null) {

            exceptionResult = searchSqlException(t);
            if (exceptionResult == null) {
                httpStatus = handleUnknowException(t);
                String message = t.getMessage();
                if (t instanceof MethodArgumentNotValidException) {
                    message = ((MethodArgumentNotValidException) t).getBindingResult().getFieldError().getDefaultMessage();
                }
                exceptionResult = ExceptionResult.builder()
                        .errorMsg(message)
                        .errorCode(String.valueOf(httpStatus.value()))
                        .stackTrace(EMPTY_STACK_TRACE) // stackTrace
                        .build();
            }
        }

        exceptionResult.setExceptionId(exceptionId);
        recordStackTrace(exceptionId, stackTrace, t);
        return new ResponseEntity<>(exceptionResult, httpStatus);
    }


    /**
     * 搜索SqlException
     *
     * @param t
     * @return
     */
    private ExceptionResult searchSqlException(Throwable t) {
        ExceptionResult exceptionResponseModel = null;
        Throwable cause = t.getCause();
        while (cause != null) {
            if (cause instanceof SQLException) {
                exceptionResponseModel = handleSQLException(cause);
                break;
            }

            cause = cause.getCause();
        }

        return exceptionResponseModel;
    }

    /**
     * 处理其他未知的Exception
     *
     * @param t
     * @return
     */
    private HttpStatus handleUnknowException(Throwable t) {
        if (t instanceof HttpRequestMethodNotSupportedException) {
            return HttpStatus.METHOD_NOT_ALLOWED;
        } else if (t instanceof HttpMediaTypeNotSupportedException) {
            return HttpStatus.UNSUPPORTED_MEDIA_TYPE;
        } else if (t instanceof HttpMediaTypeNotAcceptableException) {
            return HttpStatus.NOT_ACCEPTABLE;
        } else if (t instanceof MissingPathVariableException) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (t instanceof MissingServletRequestParameterException) {
            return HttpStatus.BAD_REQUEST;
        } else if (t instanceof ServletRequestBindingException) {
            return HttpStatus.BAD_REQUEST;
        } else if (t instanceof ConversionNotSupportedException) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (t instanceof TypeMismatchException) {
            return HttpStatus.BAD_REQUEST;
        } else if (t instanceof HttpMessageNotReadableException) {
            return HttpStatus.BAD_REQUEST;
        } else if (t instanceof HttpMessageNotWritableException) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (t instanceof MethodArgumentNotValidException) {
            return HttpStatus.BAD_REQUEST;
        } else if (t instanceof MissingServletRequestPartException) {
            return HttpStatus.BAD_REQUEST;
        } else if (t instanceof BindException) {
            return HttpStatus.BAD_REQUEST;
        } else if (t instanceof NoHandlerFoundException) {
            return HttpStatus.NOT_FOUND;
        } else if (t instanceof AsyncRequestTimeoutException) {
            return HttpStatus.SERVICE_UNAVAILABLE;
        }

        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    /**
     * 对SQLException作特殊处理
     *
     * @param cause
     * @return
     */
    private ExceptionResult handleSQLException(Throwable cause) {
        boolean showTrace = true;
        boolean showSqlTrace = false;
        ExceptionResult exceptionResult = ExceptionResult.builder().errorCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .build();
        String stackTrace = ExceptionUtils.getStackTrace(cause);
        if (showTrace && showSqlTrace) {
            exceptionResult.setErrorMsg(cause.getMessage());
            exceptionResult.setStackTrace(stackTrace);
        } else {
            exceptionResult.setErrorMsg("Database related exception");
            exceptionResult.setStackTrace(EMPTY_STACK_TRACE);
        }

        return exceptionResult;
    }

    /**
     * 同时将日志记录到本地
     *
     * @param exceptionId
     * @param stackTrace
     */
    private void recordStackTrace(String exceptionId, String stackTrace, Throwable t) {
        recordStackTraceToLocal(exceptionId, stackTrace);
    }

    /**
     * 记录异常日志
     *
     * @param exceptionId
     * @param stackTrace
     */
    private void recordStackTraceToLocal(String exceptionId, String stackTrace) {
        LOGGER.error("exceptionId: {}, exception: {}", exceptionId, stackTrace);
    }
}
