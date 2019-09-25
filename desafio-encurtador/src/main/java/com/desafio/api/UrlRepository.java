package com.desafio.api;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long>{

	Optional<Url> findByUrlLong(String urlLong);

	Optional<Url> findByUrlShort(String urlShort);

}
