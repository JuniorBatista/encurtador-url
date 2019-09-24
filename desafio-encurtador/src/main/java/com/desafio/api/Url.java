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
	private Integer expiresDays;
	private Date dataCriacao;
	private Date dataAtualizacao;
	
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

	@Column(name = "expires_days", nullable = false)
	public Integer getExpiresDays() {
		return expiresDays;
	}

	public void setExpiresDays(Integer expiresDays) {
		this.expiresDays = expiresDays;
	}

	@Column(name = "data_criacao", nullable = false)
	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@Column(name = "data_atualizacao", nullable = false)
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	@PreUpdate
	public void preUpdate() {
		dataAtualizacao = new Date();
	}
	
	@PrePersist
	public void prePersist() {
		final Date atual = new Date();
		dataCriacao = atual;
		dataAtualizacao = atual;
	}

	@Override
	public String toString() {
		return "Url [id=" + id + ", urlLong=" + urlLong + ", urlShort=" + urlShort + ", expiresDays=" + expiresDays
				+ ", dataCriacao=" + dataCriacao + ", dataAtualizacao=" + dataAtualizacao + "]";
	}

}
