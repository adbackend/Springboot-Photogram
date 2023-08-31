package com.cos.photogramstart.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository를 상속받으면 어노테이션이 없이도 IoC 자동등록 
public interface UserRepository extends JpaRepository<User, Integer>{
	
	// JPA query methods
	
	User findByUsername(String username);
}
