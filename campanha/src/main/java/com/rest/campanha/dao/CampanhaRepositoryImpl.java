package com.rest.campanha.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.rest.campanha.models.Campanha;

public class CampanhaRepositoryImpl implements CampanhaRepositoryCustom {
	
	@Autowired private MongoOperations operations;

	public List<Campanha> findCampanhasMesmoPeriodo(Campanha campanha) {

				Query query = new Query();
				
				query.addCriteria(new Criteria().orOperator(
						Criteria.where("dataInicio").lte(campanha.getDataFim()).gte(campanha.getDataInicio()),
						Criteria.where("dataFim").lte(campanha.getDataFim()).gte(campanha.getDataInicio())
						));

		return operations.find(query, Campanha.class);
	}

	@Override
	public List<Campanha> findCampanhasAtivas() {

		Query query = new Query();
		
		query.addCriteria(new Criteria().where("dataFim").gte(LocalDate.now()));
		
		return operations.find(query, Campanha.class);
	}

	@Override
	public List<Campanha> findCampanhasAtivasByTime(String timeCoracao) {
		Query query = new Query();
		
		query.addCriteria(new Criteria().where("dataFim").gte(LocalDate.now()).and("idTimeCoracao").is(timeCoracao));
		
		return operations.find(query, Campanha.class);
	}

}
