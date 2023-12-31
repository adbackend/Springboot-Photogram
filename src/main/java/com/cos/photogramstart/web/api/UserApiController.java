package com.cos.photogramstart.web.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResultUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.CMRespDto;
import com.cos.photogramstart.web.dto.user.UserUpdateDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController
@Slf4j
public class UserApiController {
	
	private final UserService userService;
	
	@PutMapping("/api/user/{id}")
	public CMRespDto<?> update(@PathVariable int id, 
							   @Valid UserUpdateDto userUpdateDto,
							   BindingResult bindingResult, // BindingResult 객체는 @Valid의 어노테이션 다음에 적어야된다.
							   @AuthenticationPrincipal PrincipalDetails principalDetails) {

		log.info("수정할 userUpdateDto 데이터 : ", userUpdateDto);
		log.info("bindingResult : ", bindingResult);
		
		if(bindingResult.hasErrors()) {
			
			Map<String, String> errorMap = new HashMap<>();
			
			for(FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
				
				System.out.println("===============================");
				System.out.println(error.getDefaultMessage());
				System.out.println("===============================");
			}
			
			throw new CustomValidationApiException("유효성 검사 실패", errorMap);
		}else {
			
			User userEntity = userService.회원수정(id, userUpdateDto.toEntity());
			
			principalDetails.setUser(userEntity); // 세션정보 변경
			
			return new CMRespDto<>(1, "회원 수정완료", userEntity);
		}
		
	}

}
