package com.luke.xchange.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ResourceNotFoundException extends BaseCustomException {

	public enum ResourceNotFoundExceptionError {
		RESOURCE_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "ResourceNotFound",
				"Resource Not Found Error"),
		CURRENCY_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "CurrencyNotFound", "Currency Not Found Error");
		private HttpStatus httpStatusCode;
		private String errorCode;
		private String errorDescription;

		ResourceNotFoundExceptionError(HttpStatus httpStatusCode, String errorCode, String errorDescription) {
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

	public ResourceNotFoundException(ResourceNotFoundExceptionError error) {
		super(error.httpStatusCode, error.errorCode, error.errorDescription);
	}
}
