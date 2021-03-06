package com.rest.sociotorcedor.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.sociotorcedor.models.Campanha;
import com.rest.sociotorcedor.models.Socio;
import com.rest.sociotorcedor.respositories.SocioRepository;

@RestController
public class SocioController {

    @Autowired
    SocioRepository socioRepository;

    @RequestMapping(method=RequestMethod.GET, value = "/socio")
    public Iterable<Socio> socio() {
    	socioRepository.findCampanhasAtivas();
        return socioRepository.findAll();
    }

    @RequestMapping(method=RequestMethod.POST, value = "/socio")
    public String save(@RequestBody Socio socio) {
    	if(isCadastrado(socio)) {
    		associarCampanhasJaCadastrado(socio);
    		return "e-mail j� cadastrado";
    	}
    	else {
    		socioRepository.save(socio);
            return socio.getId();
    	}
    	
    }

	@RequestMapping(method=RequestMethod.GET, value="/socio/{id}")
    public Socio show(@PathVariable String id) {
        return socioRepository.findOne(id);
    }


    @RequestMapping(method=RequestMethod.DELETE, value="/socio/{id}")
    public String delete(@PathVariable String id) {
        Socio socio = socioRepository.findOne(id);
        socioRepository.delete(socio);

        return "campanha deleted";
    }
    
    private boolean isCadastrado(Socio cadastrado) {
    	
		Socio socio = socioRepository.findByEMail(cadastrado);
		return null == socio ? false : true;
	}
    

	private void associarCampanhasJaCadastrado(Socio socio) {
		List<Campanha> campanhasAtivas = socioRepository.findCampanhasAtivasByTime(socio);
		if(socio.getCampanhas() != null) {
			if(null != campanhasAtivas) {
				socio.setCampanhas(
				Stream.concat(socio.getCampanhas().stream(), campanhasAtivas.stream())
	            .collect(Collectors.toList())
	            );
			}
		} else {
			if(null != campanhasAtivas)
				socio.setCampanhas(campanhasAtivas);
		}
		socioRepository.save(socio);
	}
}