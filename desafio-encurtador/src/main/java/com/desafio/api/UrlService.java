package com.desafio.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.api.utils.ApplicationProperties;
import com.desafio.api.utils.UrlTransform;

@Service
public class UrlService {
	
	@Autowired
	private UrlRepository repository;
	
	@Autowired
	ApplicationProperties applicationProperties;
	
	public Url findByUrlLong(String shortURL) {
		return repository.findByUrlLong(shortURL).orElse(null);
	}
	
	public Url findByUrlShort(String shortURL) {
		return repository.findByUrlShort(shortURL).orElse(null);
	}
	
	public Url insert(Url urlGet) {

		UrlTransform urlTransform = UrlTransform.getInstance();

		String shortUrl = urlTransform.transform(applicationProperties.getUrlHostShortener());
		System.out.println("shortUrl: "+shortUrl);

		Url url = new Url();
		url.setUrlLong(urlGet.getUrlLong());
		url.setUrlShort(shortUrl);
		url.setExpiresDays(urlGet.getExpiresDays());
		repository.save(url);
		return url;

	}

}