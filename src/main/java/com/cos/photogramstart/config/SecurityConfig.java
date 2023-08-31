package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity //해당 파일로 시큐리티를 활성화
@Configuration //IoC
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	
	//시큐리티 설정
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable(); //비활성화
		
		http.authorizeRequests()
			.antMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**").authenticated() //인증된 사용자만 접근 가능
			.anyRequest().permitAll() // 그 외 모든 접근은 허용
			.and()
			.formLogin()
			.loginPage("/auth/signin") // GET방식
			.loginProcessingUrl("/auth/signin") // POST방식 -> 스프링 시큐리티가 로그인 프로세스 진행
			.defaultSuccessUrl("/"); //로그인 성공
	}

}
