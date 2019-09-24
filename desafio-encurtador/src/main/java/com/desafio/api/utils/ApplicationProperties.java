package com.desafio.api.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.config")
public class ApplicationProperties {

	private String urlHostShortener;

	public String getUrlHostShortener() {
		return urlHostShortener;
	}

	public void setUrlHostShortener(String urlHostShortener) {
		this.urlHostShortener = urlHostShortener;
	}
	
}
