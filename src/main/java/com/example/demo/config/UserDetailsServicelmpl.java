package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CustomUser;
import com.example.demo.dto.MemberDTO;
import com.example.demo.service.MemberService;

@Service
public class UserDetailsServicelmpl implements UserDetailsService {

	@Autowired
	MemberService service;
	
//	사용자 아이디를 기반으로 인증객체를 생성하는 메소드
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("login id : " + username);
//		아이디로 회원 정보 조회
		MemberDTO dto = service.read(username);
		
		if(dto == null) {
			throw new UsernameNotFoundException(""); // 사용자 정보가 없다면 에러를 발생시킴
		} else {
			return new CustomUser(dto); //dto를 인증객체로 변환하여 반환			
		}
	}
	
}
