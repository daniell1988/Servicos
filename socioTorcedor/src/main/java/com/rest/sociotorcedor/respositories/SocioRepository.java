package com.rest.sociotorcedor.respositories;

import org.springframework.data.repository.CrudRepository;

import com.rest.sociotorcedor.models.Socio;

public interface SocioRepository extends CrudRepository<Socio, String>, SocioRepositoryCustom {
	
	@Override
	Socio findOne(String id);

    @Override
    void delete(Socio deleted);

}
