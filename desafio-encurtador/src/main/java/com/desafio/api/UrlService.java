package com.desafio.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.desafio.api.responses.Response;
import com.desafio.api.utils.ApplicationProperties;
import com.desafio.api.utils.DesafioUtils;
import com.desafio.api.utils.UrlTransform;

@Service
public class UrlService {
	
	@Autowired
	private UrlRepository repository;
	
	@Autowired
	ApplicationProperties applicationProperties;
	
	public Url findById(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public Url findByUrlLong(String shortURL) {
		return repository.findByUrlLong(shortURL).orElse(null);
	}
	
	public Url findByUrlShort(String shortURL) {
		return repository.findByUrlShort(shortURL).orElse(null);
	}
	
	public Url insert(Url urlObjectPost) {

		String shortUrl = UrlTransform.getInstance()
				.build(applicationProperties.getUrlHostShortener());

		Date expirationDate = DesafioUtils.getDataExpiracao(
				new Date(), applicationProperties.getExpiresDays());

		Url url = new Url();
		url.setUrlLong(urlObjectPost.getUrlLong());
		url.setUrlShort(shortUrl);
		url.setExpirationDate(expirationDate);

		repository.save(url);

		return url;

	}
	
	public void validate(Url urlGet, BindingResult result, Response<Url> response) {

		List<String> listErrors = new ArrayList<String>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(
				error -> listErrors.add(error.getDefaultMessage())
			);
		}

        if (!DesafioUtils.isValid(urlGet.getUrlLong())) {
        	listErrors.add("URL inválida!");
		}

        Url urlFind = findByUrlLong(urlGet.getUrlLong());

		if (urlFind != null) {

			System.out.println("getExpirationDate: "+urlFind.getExpirationDate());
	        System.out.println("getCreationDate..: "+urlFind.getCreationDate());
	        System.out.println("compareTo........: "+urlFind.getExpirationDate().compareTo(new Date()));

			if (isExpired(urlFind)) {
				repository.delete(urlFind);
			} else {
	        	listErrors.add("URL já cadastrada!");
	        }

		}

		listErrors.forEach(
			error -> response.getErros().add(error)
		);

	}

	private boolean isExpired(Url urlFind) {
		return urlFind.getExpirationDate().compareTo(urlFind.getCreationDate()) < 0;
	}

}