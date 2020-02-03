package com.luke.xchange.exception;

import org.springframework.http.HttpStatus;

public class AppException extends BaseCustomException {

	public enum AppExceptionError {
		INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "InternalServerError",
				"Internal Server Error");
		private HttpStatus httpStatusCode;
		private String errorCode;
		private String errorDescription;

		AppExceptionError(HttpStatus httpStatusCode, String errorCode, String errorDescription) {
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

	public AppException(AppExceptionError appExceptionError) {
		super(appExceptionError.httpStatusCode, appExceptionError.errorCode, appExceptionError.errorDescription);
	}
}