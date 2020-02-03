package com.luke.xchange.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends BaseCustomException {

	public enum BadRequestExceptionError {
		BAD_REQUEST_ERROR(HttpStatus.BAD_REQUEST, "BadRequest",
				"Bad Request Error"),
		EMPTY_VALUE_ERROR(HttpStatus.BAD_REQUEST, "EmptyValue",
				"Empty Value Error"),
		INVALID_VALUE_ERROR(HttpStatus.BAD_REQUEST, "InvalidValue",
				"Invalid Value Error");
		private HttpStatus httpStatusCode;
		private String errorCode;
		private String errorDescription;

		BadRequestExceptionError(HttpStatus httpStatusCode, String errorCode, String errorDescription) {
	        this.httpStatusCode = httpStatusCode;
	        this.errorCode = errorCode;
	        this.errorDescription = errorDescription;
	    }

		public String getErrorCode() {
			return errorCode;
		}

		public String getErrorDescription() {
			return errorDescription;
		}
	}

	public BadRequestException(BadRequestExceptionError error) {
		super(error.httpStatusCode, error.errorCode, error.errorDescription);
	}
}