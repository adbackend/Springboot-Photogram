package com.cos.photogramstart.handler.ex;

import java.util.Map;

public class CustomValidationApiException extends RuntimeException{

	// 객체를 구분할 때 사용 jvm
	private static final long serialVersionUID = -807520916259076805L;
	
	private Map<String, String> errorMap;
	
	public CustomValidationApiException(String message, Map<String, String> errorMap) {
		super(message);
		this.errorMap= errorMap;
	}
	
	public Map<String, String> getErrorMap(){
		return errorMap;
	}
	
}