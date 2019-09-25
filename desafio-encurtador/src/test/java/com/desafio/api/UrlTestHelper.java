package com.desafio.api;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.desafio.api.utils.ApplicationProperties;
import com.desafio.api.utils.DesafioUtils;

@Component
public class UrlTestHelper {
	
	@Autowired
	ApplicationProperties applicationProperties;

    private final String urlLong = "http://www.github.com";

    public UrlTestHelper() {
    }

    public Url buildUrl() {
    	
    	Date expirationDate = DesafioUtils.getDataExpiracao(
				new Date(), applicationProperties.getExpiresDays());
    	
    	Url novaUrl = new Url();
    	novaUrl.setUrlLong(urlLong);
    	novaUrl.setExpirationDate(expirationDate);
        return novaUrl;
    }
    
}
