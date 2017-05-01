package com.techelevator.security;

import java.util.Random;

public class CodeGenerator {

	
	public static String getCode() {
		Random random = new Random();
		String salt = "";
		for (int i = 0 ; i < 25; i++) {
			char letter = (char)(random.nextInt(26) + 'a'); 
			int number = (int)(10 * Math.random()); 
			int y = (int)(10 * Math.random()); 
			if (y <= 4) {
				salt = salt.concat(Character.toString(letter));				
			} else {
				salt = salt.concat(Integer.toString(number));					
			}
		}
		return salt;
	}	
}
