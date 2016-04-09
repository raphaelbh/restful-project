package com.demo;

import org.junit.Assert;
import org.junit.Test;

public class PasswordEncryptorTest {
	
	@Test
	public void passwordEncryptorMatchTest() {
		
		String text = "passwordTest";
		String encryptedText = AppEncryptor.encrypt(text);
		Boolean match = AppEncryptor.matches(text, encryptedText);
		
		Assert.assertEquals(match, true);
	}

}
