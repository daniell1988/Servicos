package com.rest.campanha.dao;

import org.springframework.data.repository.CrudRepository;

import com.rest.campanha.models.Campanha;

public interface CampanhaRepository extends CrudRepository<Campanha, String>, CampanhaRepositoryCustom {
	
	@Override
	Campanha findOne(String id);

    @Override
    void delete(Campanha deleted);

}
