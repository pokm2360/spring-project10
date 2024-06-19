package com.example.demo.etc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordTest {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Test
	public void 암호화테스트() {
		
		String password = "1234";
//		비밀번호 암호화
		String enPw = passwordEncoder.encode(password);
		
		System.out.println("enPw" + enPw);
//		비밀번호가 1234가 맞는지 확인
		boolean matchResult = passwordEncoder.matches(password, enPw);
		
		System.out.println("확인 결과: " + matchResult);
	}
}
