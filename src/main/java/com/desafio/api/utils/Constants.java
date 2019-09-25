package com.desafio.api.utils;

import java.nio.charset.StandardCharsets;

import org.springframework.http.MediaType;

public class Constants {
	
	public final static String CONTROLLER_NAME = "urlshortener";
	public static final String APPLICATION_JSON = "application/json";
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);
	
}
