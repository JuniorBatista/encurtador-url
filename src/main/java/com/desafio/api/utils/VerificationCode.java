package com.desafio.api.utils;

import java.util.Random;

public class VerificationCode {
	
	public static void main2(String[] args) {
//		VerificationCode verificationCode = new VerificationCode();
//		String urlEncode = verificationCode.getVerificationCode();
//		System.out.println("urlEncode: "+urlEncode);
	}
	
	public static void main(String[] args) {
		System.out.println(generateRandomChars("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", 17)); 
	} 
	
	public static String generateRandomChars(String candidateChars, int length) {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(candidateChars.charAt(random.nextInt(candidateChars .length())));
		}
		return sb.toString();
	} 
	
	private String verificationCode;

	/*
	 * This define the default length of verification code
	 */
	private int length = 10;

	/*
	 * Here is the allowed characters in the verification code
	 */
	private char[] allowedCharacters = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 
			'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 
			'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 
			'W', 'X', 'Y', 'Z'};

	public VerificationCode() {
		this.setVerificationCode(this.generateVerificationCode());
	}

	/*
	 * This constructor set the length of the verification code
	 */
	public VerificationCode(int length) {
		this.setLength(length);
		this.setVerificationCode(this.generateVerificationCode());
	}

	/*
	 * This core method generate the verification code and return him
	 */
	private String generateVerificationCode() {

		String tmp = "";
		
		for (int i = 0; i < this.getLength(); i++) {
			
			int c = (int) Math.floor(Math.random()*this.getAllowedCharacters().length);
			
			tmp += this.getAllowedCharacters()[c];
			
		}
		
		return tmp;
		
	}

	public String getVerificationCode() {
		
		return verificationCode;
		
	}

	private void setVerificationCode(String verificationCode) {
		
		this.verificationCode = verificationCode;
		
	}

	private int getLength() {
		
		return length;
		
	}

	private void setLength(int length) {
		
		this.length = length;
		
	}

	public String toString() {
		
		return this.getVerificationCode();
		
	}

	private char[] getAllowedCharacters() {
		
		return allowedCharacters;
		
	}
	
}
