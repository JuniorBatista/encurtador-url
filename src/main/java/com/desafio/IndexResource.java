package com.desafio;

import java.net.URISyntaxException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexResource {

	@GetMapping
	public ResponseEntity<String> testeRedirect() throws URISyntaxException {
		return ResponseEntity.ok("Acesse o README.md do projeto no GitHub (private).");
	}
	
}
