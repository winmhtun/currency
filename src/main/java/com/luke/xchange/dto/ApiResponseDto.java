package com.luke.xchange.dto;


public class ApiResponseDto {
	private Boolean success;
	private String message;
	private Object data;
	private Object error;
	
	public ApiResponseDto(Boolean success, String message) {
		this.success = success;
		this.message = message;
	}
	
	public ApiResponseDto(Boolean success, String message, Object data) {
		this.success = success;
		this.message = message;
		this.data = data;
	}
	
	public ApiResponseDto(Boolean success, String message, Object data, Object error) {
		this.success = success;
		this.message = message;
		this.data = data;
		this.error = error;
	}
	
	public Boolean getSuccess() {
		return success;
	}
	
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public Object getError() {
		return error;
	}

	public void setError(Object error) {
		this.error = error;
	}
}
