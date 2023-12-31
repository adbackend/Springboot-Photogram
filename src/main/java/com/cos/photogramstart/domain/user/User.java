package com.cos.photogramstart.domain.user;

import java.time.LocalDateTime;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// JPA - Java Persistence API (자바로 데이터를 영구적으로 저장(DB) 할 수 있는 API 제공)

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity // db에 테이블 생성
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 20, unique = true)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String name;
	
	private String website; //웹사이트
	private String bio; //자기소개
	
	@Column(nullable = false)
	private String email;
	private String phone;
	private String gender;
	
	private String profileImageUrl; //사진
	private String role; //권한
	private LocalDateTime createDate;
	
	@PrePersist // 디비에 insert되기 직전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
}
