package com.qin.genesis.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionResult {

    private String stackTrace;

    private String errorCode;

    private String errorMsg;

    private String exceptionId;
}
