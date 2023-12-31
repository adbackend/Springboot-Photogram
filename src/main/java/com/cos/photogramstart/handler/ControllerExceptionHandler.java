package com.cos.photogramstart.handler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDto;

@ControllerAdvice 
@RestController // 응답은 data를 리턴할거라서 @RestController 사용
public class ControllerExceptionHandler {
	
	// CMResponseDto, Script 비교
	// 1.클라이언트에게 응답할 때는 Script 비교
	// 2.Ajax통신 - CMResponseDto
	// 3.Android통신 - CMResponseDt
	
	@ExceptionHandler(CustomValidationException.class)
	public String validationException(CustomValidationException e) {
		
		return Script.back(e.getErrorMap().toString());
		
	}
	
	@ExceptionHandler(CustomValidationApiException.class)
	public ResponseEntity<?> validationApiException(CustomValidationApiException e) {
		
		return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
	}
}
