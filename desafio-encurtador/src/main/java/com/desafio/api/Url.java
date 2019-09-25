package com.desafio.api;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "url")
public class Url {

	private Long id;
	private String urlLong;
	private String urlShort;
	private Date creationDate;
	private Date expirationDate;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "url_long", nullable = false)
	public String getUrlLong() {
		return urlLong;
	}

	public void setUrlLong(String urlLong) {
		this.urlLong = urlLong;
	}

	@Column(name = "url_short")
	public String getUrlShort() {
		return urlShort;
	}

	public void setUrlShort(String urlShort) {
		this.urlShort = urlShort;
	}

	@Column(name = "creation_date", nullable = false)
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name = "expiration_date", nullable = false)
	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	@PreUpdate
	public void preUpdate() {
	}
	
	@PrePersist
	public void prePersist() {
		final Date atual = new Date();
		creationDate = atual;
	}

	@Override
	public String toString() {
		return "Url [id=" + id + ", urlLong=" + urlLong + ", urlShort=" + urlShort + ", creationDate=" + creationDate
				+ ", expirationDate=" + expirationDate + "]";
	}

}
