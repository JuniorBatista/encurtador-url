package com.desafio.api.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Random;

public class UrlTransform {
	
	private String baseUrlHost = "http://localhost:8080";

	private static UrlTransform instance;

	private UrlTransform() {
	}

	public static synchronized UrlTransform getInstance() {
		if (instance == null) {
			instance = new UrlTransform();
		}
		return instance;
	}
	
	public String build(String baseUrlHostConfig) {
		int sizeHash = 23;
		return getBaseUrlHost(baseUrlHostConfig) + "/" + generateHashDatetime(sizeHash);
	}
	
	public String getBaseUrlHost(String baseUrlHostConfig) {
		if (baseUrlHostConfig == null) {
			baseUrlHostConfig = baseUrlHost;
		}
		return baseUrlHostConfig + "/" + Constants.CONTROLLER_NAME;
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

	private String generateHashDatetime(int sizeHash) {
		Calendar calAgora = Calendar.getInstance();
		String hashDateTime = stringHexa(gerarHash(calAgora.toString(), "MD5"), sizeHash);
		return hashDateTime;
	}

	private static String stringHexa(byte[] bytes, int sizeHash) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
			int parteBaixa = bytes[i] & 0xf;
			if (parteAlta == 0)
				s.append('0');
			s.append(Integer.toHexString(parteAlta | parteBaixa));
		}
		return s.toString().substring(0, sizeHash);
	}

	private static byte[] gerarHash(String frase, String algoritmo) {
		try {
			MessageDigest md = MessageDigest.getInstance(algoritmo);
			md.update(frase.getBytes());
			return md.digest();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

}
