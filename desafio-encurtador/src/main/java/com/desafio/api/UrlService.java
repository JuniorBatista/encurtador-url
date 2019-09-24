package com.desafio.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.api.utils.UrlTransform;

@Service
public class UrlService {
	
	@Autowired
	private UrlRepository repository;
	
	public Url findByUrlLong(String shortURL) {
		return repository.findByUrlLong(shortURL).orElse(null);
	}
	
	public Url findByUrlShort(String shortURL) {
		return repository.findByUrlShort(shortURL).orElse(null);
	}
	
	public Url insert(Url urlGet) {

		UrlTransform urlTransform = UrlTransform.getInstance();

		String shortUrl = urlTransform.transform(urlGet.getUrlLong());
		while (!repository.findByUrlShort(shortUrl).isEmpty()) {
			shortUrl = urlTransform.transform(urlGet.getUrlLong());
		}

		Url url = new Url();
		url.setUrlLong(urlGet.getUrlLong());
		url.setUrlShort(shortUrl);
		url.setExpiresDays(urlGet.getExpiresDays());
		repository.save(url);
		return url;

	}

}