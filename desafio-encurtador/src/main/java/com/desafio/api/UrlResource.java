package com.desafio.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.api.responses.Response;
import com.desafio.api.utils.Constants;
import com.desafio.api.utils.DesafioUtils;
import com.desafio.api.utils.UrlTransform;

@RestController
@RequestMapping("/"+Constants.CONTROLLER_NAME)
public class UrlResource {

	@Autowired
	private UrlService service;

	@PostMapping
	public ResponseEntity<Response<Url>> createShortenerUrl(@Valid @RequestBody Url urlGet, BindingResult result) {

		Response<Url> response = new Response<Url>();
		
		validate(urlGet, result, response);

		if (!response.getErros().isEmpty()) {
			return ResponseEntity.badRequest().body(response);
		}
		 
		Url url = service.insert(urlGet);

		response.setData(url);
		return ResponseEntity.ok(response);

	}
	
	@GetMapping(value="/{hashCodeUrl}")
	public ResponseEntity<Url> openShortenerUrl(@PathVariable String hashCodeUrl) throws URISyntaxException {

		UrlTransform urlTransform = UrlTransform.getInstance();
		final String shortUrl = urlTransform.getBaseUrl() + hashCodeUrl;
		
		final Url urlOpen = service.findByUrlShort(shortUrl);
		if (urlOpen == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		Date dataExpiracao = DesafioUtils.getDataExpiracao(urlOpen);
		Date dataHoje = new Date();
		
		if (dataHoje.after(dataExpiracao)) {
			return ResponseEntity.status(HttpStatus.GONE).body(null);
		}

		String redirectTo = urlOpen.getUrlLong();
		URI uri = new URI(redirectTo);
		HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setLocation(uri);

	    return new ResponseEntity<Url>(httpHeaders, HttpStatus.OK);

	}


	private void validate(Url urlGet, BindingResult result, Response<Url> response) {
		List<String> listErrors = new ArrayList<String>();
		DesafioUtils.validateUrl(listErrors, urlGet, result, response);
		
		if (service.findByUrlLong(urlGet.getUrlLong()) != null) {
			listErrors.add("URL já cadastrada!");
		}

		listErrors.forEach(error -> response.getErros().add(error));
	}
	
}
