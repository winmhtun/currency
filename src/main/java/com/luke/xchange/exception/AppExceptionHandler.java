package com.luke.xchange.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.luke.xchange.dto.ApiResponseDto;
import com.luke.xchange.dto.ErrorResponseDto;

@ControllerAdvice
public class AppExceptionHandler {
	
	@ExceptionHandler(BaseCustomException.class)
	public ResponseEntity<?> baseCustomException(BaseCustomException ex) {
		ErrorResponseDto response = new ErrorResponseDto(new Date(), ex.getErrorCode(), ex.getErrorDescription());
		return new ResponseEntity<>(new ApiResponseDto(false, null, null, response), ex.getHttpStatusCode());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> generalException(Exception ex) {
		ErrorResponseDto response = new ErrorResponseDto(new Date(), ex.getMessage(), ex.getMessage());
		return new ResponseEntity<>(new ApiResponseDto(false, null, null, response), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
