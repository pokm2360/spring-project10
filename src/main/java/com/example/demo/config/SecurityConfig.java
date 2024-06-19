package com.example.demo.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration // 스프링 설정 클래스
@EnableWebSecurity // 보안 설정
public class SecurityConfig {
	
//	사용자 정의 필터체인을 빈으로 등록하는 메소드
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
//		게시물 관리는 관리자 또는 일반사용자만 접근 가능
//		회원 관리는 관리자만 접근가능
		http.authorizeHttpRequests()
			.requestMatchers("/register").permitAll()
			.requestMatchers("/assets/*", "/css/*", "/js/*").permitAll()
			.requestMatchers("/").authenticated() // 권한에 상관없이 로그인 됬으면 ok
			.requestMatchers("/board/*").hasAnyRole("ADMIN","USER")
			.requestMatchers("/comment/*").hasAnyRole("ADMIN", "USER")
			.requestMatchers("/member/*").hasRole("ADMIN");
		
		http.formLogin(); // 사용자 인증은 폼로그인 방식을 사용
		http.csrf().disable(); // csrf 비활성화
		http.logout(); // 로그아웃 처리
		
		http.formLogin()
				.loginPage("/customlogin") //로그인 화면 주소
				.loginProcessingUrl("/login") // 로그인 처리 주소
				.successHandler(
						
						new AuthenticationSuccessHandler() {

							@Override
							public void onAuthenticationSuccess(HttpServletRequest request,
									HttpServletResponse response, Authentication authentication)
									throws IOException, ServletException {
								
								response.sendRedirect("/");
								
							}
							
						}
						
				).permitAll();
				
		return http.build();
	}
	
	// 패스워드 인코더 객체를 빈으로 등록하는 메소드
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
