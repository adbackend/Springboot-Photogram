package com.cos.photogramstart.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	private User user;
	
	public PrincipalDetails(User user) {
		this.user = user;
	}
	
	// 권한 Collection 이유 : 한개가 아닐수도 있다 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { // 계정이 갖고 있는 권한 목록
		
		Collection<GrantedAuthority> collector = new  ArrayList<>();
		collector.add(() -> {
			return user.getRole();
		});
		
		return null;
	}

	@Override
	public String getPassword() { // 계정 패스워드
		return user.getPassword();
	}

	@Override
	public String getUsername() { // 계정 이름
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() { // 계정이 만료되지 않았는지 true=만료되지 않음
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { // 계정이 잠겨있지 않은지, true=잠겨있지 않음
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { // 패스워드 만료되지 않았는지, true=만료되지 않음
		return true;
	}

	@Override
	public boolean isEnabled() { // 계정이 사용가능한 계정인지
		// TODO Auto-generated method stub
		return true;
	}

}
