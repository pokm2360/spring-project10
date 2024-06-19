package com.example.demo.dto;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUser extends User{

	public CustomUser(MemberDTO dto) {
//		MemberDTO -> USER
//		사용자 아이디, 패스워드, 권한을 꺼내서 User 객체 생성
		super(dto.getId(), dto.getPassword(), // 부모생성자 호출
				Arrays.asList(new SimpleGrantedAuthority(dto.getRole())));
	}

}
