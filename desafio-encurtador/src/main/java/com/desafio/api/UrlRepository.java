package com.desafio.api;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long>{

	Optional<Url> findByUrlLong(String url);

	Optional<Url> findByUrlShort(String urldecode);

}
