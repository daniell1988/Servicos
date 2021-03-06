package com.rest.sociotorcedor.models;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Document(collection = "campanha")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Socio {
    @Id
    String id;
    String nomeCompleto;
    String email;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize
    LocalDate dataNascimento;
    String timeCoracao;
    List<Campanha> campanhas;

    public Socio() {
    }

    public Socio(String nomeCompleto, String email, LocalDate dataNascimento, String timeCoracao) {
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.timeCoracao = timeCoracao;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTimeCoracao() {
		return timeCoracao;
	}

	public void setTimeCoracao(String timeCoracao) {
		this.timeCoracao = timeCoracao;
	}

	public List<Campanha> getCampanhas() {
		return campanhas;
	}

	public void setCampanhas(List<Campanha> campanhas) {
		this.campanhas = campanhas;
	}
    
}