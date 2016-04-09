package com.demo;

import org.springframework.security.crypto.password.StandardPasswordEncoder;

public class AppEncryptor {
	
	public static final String ENCRYPTION_KEY = "kZg6$tLf!uMxY9GJ@pGW_p5F^PUv22FF6RvMVVUUxH9^#ggjk5";
	
	public static String encrypt(String text) {
		
		StandardPasswordEncoder encoder = new StandardPasswordEncoder(ENCRYPTION_KEY);
		return encoder.encode(text);
		
	}
	
	public static Boolean matches(String text, String encryptedText) {
		
		StandardPasswordEncoder encoder = new StandardPasswordEncoder(ENCRYPTION_KEY);
		return encoder.matches(text, encryptedText);
		
	}

}
