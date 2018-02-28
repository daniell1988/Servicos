package com.rest.sociotorcedor.respositories;

import java.util.List;

import com.rest.sociotorcedor.models.Campanha;
import com.rest.sociotorcedor.models.Socio;

public interface SocioRepositoryCustom {
	
	public List<Campanha> findCampanhasAtivas();
	public List<Campanha> findCampanhasAtivasByTime(Socio socio);
	public Socio findByEMail(Socio socio);
	
}
