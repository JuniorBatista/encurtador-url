package com.desafio.api.utils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
	
	public static Date getDataExpiracao(Date creationDate, Integer expiresDays) {
		Calendar calExpires = Calendar.getInstance();
		calExpires.setTime(creationDate);
		calExpires.add(Calendar.DAY_OF_MONTH, expiresDays);
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
		Url urlConvert = mapper.readValue(jsonNode.get("data").toString(), Url.class);
        return urlConvert;
    }

}
