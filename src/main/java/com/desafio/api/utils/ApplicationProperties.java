package com.desafio.api.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.config")
public class ApplicationProperties {

	private String urlHostShortener;
	private Integer expiresDays;

	public String getUrlHostShortener() {
		return urlHostShortener;
	}

	public void setUrlHostShortener(String urlHostShortener) {
		this.urlHostShortener = urlHostShortener;
	}

	public Integer getExpiresDays() {
		return expiresDays;
	}

	public void setExpiresDays(Integer expiresDays) {
		this.expiresDays = expiresDays;
	}
	
}
