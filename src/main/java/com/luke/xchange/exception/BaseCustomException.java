package com.luke.xchange.exception;

import org.springframework.http.HttpStatus;

public abstract class BaseCustomException extends Exception {

    private HttpStatus httpStatusCode;
    private String errorCode;
    private String errorDescription;

    public BaseCustomException(HttpStatus httpStatusCode, String errorCode, String errorDescription) {
        super(errorDescription);

        this.httpStatusCode = httpStatusCode;
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }


    public HttpStatus getHttpStatusCode() {
        return httpStatusCode;
    }


    public String getErrorCode() {
        return errorCode;
    }


    public String getErrorDescription() {
        return errorDescription;
    }
}
