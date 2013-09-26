package com.roytrack.spring.security;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;

public class EncodePassWordByLdap {

	
	public static void main(String[] args) {//sc_bchykj
		PasswordEncoder passwordEncoder=new Md5PasswordEncoder();
		String newPassword = passwordEncoder.encodePassword("123456","sc_bchykj");
		System.out.println(newPassword);
	}
}
