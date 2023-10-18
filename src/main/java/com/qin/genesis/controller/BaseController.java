package com.qin.genesis.controller;

import com.qin.genesis.common.RestResponse;

public class BaseController<T> {

    public RestResponse SUCCESS(String message){
        return new RestResponse(200, message);
    }

    public RestResponse<T> SUCCESS(T data) {
        return new RestResponse(200, data);
    }

    public RestResponse<T> SUCCESS(T data, String message) {
        return new RestResponse(200, data, message);
    }

    public RestResponse ERROR(String message) {
        return new RestResponse(400, message);
    }

    public RestResponse ERROR(Integer code, String message) {
        return new RestResponse(code, message);
    }
    public RestResponse<T> ERROR(Integer code, T data, String message) {
        return new RestResponse(code, data, message);
    }
}
