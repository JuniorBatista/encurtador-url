package com.desafio.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

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
import com.desafio.api.utils.ApplicationProperties;
import com.desafio.api.utils.UrlTransform;

@RestController
@RequestMapping("/urlshortener")
public class UrlResource {

	@Autowired
	private UrlService service;
	
	@Autowired
	ApplicationProperties applicationProperties;

	@PostMapping
	public ResponseEntity<Response<Url>> createShortenerUrl(@Valid @RequestBody Url urlGet, BindingResult result) {

		Response<Url> response = new Response<Url>();

		service.validate(urlGet, result, response);

		if (!response.getErros().isEmpty()) {
			return ResponseEntity.badRequest().body(response);
		}

		Url url = service.insert(urlGet);
		response.setData(url);

		return ResponseEntity.ok(response);

	}
	
	@GetMapping(value="/{hashCodeUrl}")
	public ResponseEntity<Object> openShortenerUrl(@PathVariable String hashCodeUrl) throws URISyntaxException {

		UrlTransform urlTransform = UrlTransform.getInstance();
		final String shortUrl = urlTransform.getBaseUrlHost(
				applicationProperties.getUrlHostShortener()) + "/" + hashCodeUrl;

		final Url urlShortenerOpen = service.findByUrlShort(shortUrl);
		if (urlShortenerOpen == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("<h2>URL não encontrada!</h2>");
		}

		if (new Date().after(urlShortenerOpen.getExpirationDate())) {
			return ResponseEntity.status(HttpStatus.GONE).body("<h2>URL expirada!</h2>");
		}

	    URI urlRedirect = new URI(urlShortenerOpen.getUrlLong());
	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setLocation(urlRedirect);

	    return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);

	}

	@GetMapping(value="/id/{id}")
	public ResponseEntity<Response<Url>> openShortenerUrlById(@PathVariable Long id) throws URISyntaxException {

		final Url url = service.findById(id);
		if (url == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		Response<Url> response = new Response<Url>();
		response.setData(url);

		return ResponseEntity.ok(response);

	}

	@GetMapping(value="/teste")
	public ResponseEntity<String> testeRedirect() throws URISyntaxException {
		return ResponseEntity.ok("Redirecionamento feito para endpoint teste!");
	}

}
