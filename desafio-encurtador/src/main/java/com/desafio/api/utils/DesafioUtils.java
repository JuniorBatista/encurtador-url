package com.desafio.api.utils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.validation.BindingResult;

import com.desafio.api.Url;
import com.desafio.api.responses.Response;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class DesafioUtils {
	
	public static void trataExceptionResponse(Exception e, Response<Url> response) {
		List<String> erros = new ArrayList<String>();
		erros.add(e.getMessage());
		response.setErros(erros);
	}

	public static boolean isValid(String url) {
		try {
			new URL(url).toURI();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static void validateUrl(List<String> listErrors, Url urlGet, BindingResult result, Response<Url> response) {

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> listErrors.add(error.getDefaultMessage()));
		}

        if (!isValid(urlGet.getUrlLong())) {
        	listErrors.add("URL inválida!");
		}

	}
	
	public static Date getDataExpiracao(Url urlOpen) {
		Calendar calExpires = Calendar.getInstance();
		calExpires.setTime(urlOpen.getDataCriacao());
		calExpires.add(Calendar.DAY_OF_MONTH, urlOpen.getExpiresDays());
		return calExpires.getTime();
	}
	
	public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        JavaTimeModule module = new JavaTimeModule();
        mapper.registerModule(module);

        return mapper.writeValueAsBytes(object);
    }
	
	public static String convertObjectToJson(Url urlObject) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        JavaTimeModule module = new JavaTimeModule();
        mapper.registerModule(module);

        return mapper.writeValueAsString(urlObject);
    }
	
	public static Url convertJsonStringToObject(String jsonStr) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		JsonNode jsonNode = mapper.readTree(jsonStr);
		System.out.println("jsonNode.get(\"data\"): "+jsonNode.get("data"));
		System.out.println("jsonNode.get(\"data\").asText(): "+jsonNode.get("data").toString());
		Url urlConvert = mapper.readValue(jsonNode.get("data").toString(), Url.class);
        return urlConvert;
    }

}
