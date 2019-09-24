package com.desafio.api;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UrlTestHelper {
	
	@Autowired
	private final UrlRepository repository;

    private final String urlLong = "http://www.github.com";
    private final Integer expiresDays = 2;

    public UrlTestHelper(UrlRepository repository) {
        this.repository = repository;
    }

    public Url buildUrl() {
    	
    	Random random = new Random();
    	
    	Url novaUrl = new Url();
    	novaUrl.setUrlLong(urlLong+"/"+random.nextInt());
    	novaUrl.setExpiresDays(expiresDays);
        return novaUrl;
    }
   
}
