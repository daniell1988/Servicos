package com.rest.sociotorcedor.respositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rest.sociotorcedor.models.Campanha;
import com.rest.sociotorcedor.models.Socio;

@Service
public class SocioRepositoryImpl implements SocioRepositoryCustom{
	
	@Autowired private MongoOperations operations;
	private RestTemplate restTemplate = new RestTemplate();
	
	@Override
	public List<Campanha> findCampanhasAtivas() {
		
		final String ROOT_URI = "http://localhost:8080/campanha";
		
		List<Campanha> campanhasAtivas = restTemplate.exchange(ROOT_URI, HttpMethod.GET, null, new ParameterizedTypeReference<List<Campanha>>() {}).getBody();
		return campanhasAtivas;
		
	}

	@Override
	public Socio findByEMail(Socio socio) {
		
		Query query = new Query();
		
		query.addCriteria(new Criteria().where("email").is(socio.getEmail()));
		Socio usuario = operations.findOne(query, Socio.class);
		
		return usuario;
	}

	@Override
	public List<Campanha> findCampanhasAtivasByTime(Socio socio) {
		
		final String ROOT_URI = "http://localhost:8080/campanha/" + socio.getTimeCoracao();
		
		List<Campanha> campanhasAtivas = restTemplate.exchange(ROOT_URI, HttpMethod.GET, null, new ParameterizedTypeReference<List<Campanha>>() {}).getBody();
		return campanhasAtivas;
	}

}
