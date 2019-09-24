package com.desafio.api.utils;

import java.security.MessageDigest;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;

import com.thoughtworks.xstream.core.util.Base64Encoder;

public class UrlTransform {

	@Value("${url.host.shortener}")
	private static String baseUrlHost;
	
	public static String baseUrlShortener = baseUrlHost + Constants.CONTROLLER_NAME+"/";
	private static UrlTransform instance;
	private transient static MessageDigest crypt;

	private UrlTransform() {
	}

	public static synchronized UrlTransform getInstance() {
		if (instance == null) {
			instance = new UrlTransform();
		}
		return instance;
	}

	public String transform(String url) {
		int size = 5;
		return baseUrlShortener + generateRandomChars(size);
	}
	
	public static String generateRandomChars(int length) {
		String candidateChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder stringRandom = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			if (i % 2 == 0) {
				candidateChars = candidateChars.toLowerCase();
			} else {
				candidateChars = candidateChars.toUpperCase();
			}
			stringRandom.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
		}
		return stringRandom.toString();
	}

	private String generateHash(final String url, final int tam) {
		
		try {
			if (crypt == null) {
				crypt = MessageDigest.getInstance("MD5");
			}
			crypt.update(url.getBytes("UTF-8"));
		} catch (final Exception e) {
			return null;
		}
		
		final byte raw[] = crypt.digest();
		
		String urlEncode = new Base64Encoder().encode(raw);
		if (tam > 0) {
			urlEncode = urlEncode.substring(0, tam);
		}
		
		return urlEncode;
	}

}
