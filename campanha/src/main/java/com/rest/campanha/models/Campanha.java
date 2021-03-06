package com.rest.campanha.models;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Document(collection = "campanha")
public class Campanha {
    @Id
    String id;
    String nomeCampanha;
    String idTimeCoracao;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize
    LocalDate dataInicio;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize
    LocalDate dataFim;
    
    public Campanha() {
    }

    public Campanha(String nomeCampanha, String idTimeCoracao, LocalDate dataInico, LocalDate dataFim) {
        this.nomeCampanha = nomeCampanha;
        this.idTimeCoracao = idTimeCoracao;
        this.dataInicio = dataInico;
        this.dataFim = dataFim;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNomeCampanha() {
		return nomeCampanha;
	}

	public void setNomeCampanha(String nomeCampanha) {
		this.nomeCampanha = nomeCampanha;
	}

	public String getIdTimeCoracao() {
		return idTimeCoracao;
	}

	public void setIdTimeCoracao(String idTimeCoracao) {
		this.idTimeCoracao = idTimeCoracao;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}
    
}