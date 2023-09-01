package com.cos.photogramstart.web.dto.user;

import javax.validation.constraints.NotBlank;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data
public class UserUpdateDto {
	
	@NotBlank
	private String name;

	@NotBlank
	private String password;
	
	@NotBlank
	private String website;
	private String bio;
	private String phone;
	private String gender;
	
	// 위험한 코드, 코드수정이 필요할 예정
	public User toEntity() {
		return User.builder()
				.name(name)
				.password(password)
				.website(website)
				.bio(bio)
				.phone(phone)
				.gender(gender)
				.build();
	}

}
