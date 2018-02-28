package com.rest.campanha.dao;

import java.util.List;

import com.rest.campanha.models.Campanha;

public interface CampanhaRepositoryCustom {
	
	List<Campanha> findCampanhasMesmoPeriodo(Campanha campanha);
	
	List<Campanha> findCampanhasAtivas();
	
	List<Campanha> findCampanhasAtivasByTime(String timeCoracao);
}
