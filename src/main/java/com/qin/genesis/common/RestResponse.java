package com.qin.genesis.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResponse<T> {
    private Integer code;
    private String message;
    private T data;

    public RestResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public RestResponse(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public RestResponse(Integer code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }
}
