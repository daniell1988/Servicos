package com.rest.campanha.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.campanha.dao.CampanhaRepository;
import com.rest.campanha.models.Campanha;

@RestController
public class CampanhaController {

	@Autowired
	CampanhaRepository campanhaRepository;

	@RequestMapping(method = RequestMethod.GET, value = "/campanha")
	public Iterable<Campanha> campanha() {
		return campanhaRepository.findCampanhasAtivas();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/campanha")
	public String save(@RequestBody Campanha campanha) {
		somaDataCampanhasAtivas(campanha);
		campanhaRepository.save(campanha);
		return campanha.getId();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/campanha/{id}")
	public Iterable<Campanha> show(@PathVariable String id) {
		return campanhaRepository.findCampanhasAtivasByTime(id);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/campanha/{id}")
	public Campanha update(@PathVariable String id, @RequestBody Campanha campanha) {
		Campanha camp = campanhaRepository.findOne(id);
		if (campanha.getNomeCampanha() != null)
			camp.setNomeCampanha(campanha.getNomeCampanha());
		if (campanha.getIdTimeCoracao() != null)
			camp.setIdTimeCoracao(campanha.getIdTimeCoracao());
		if (campanha.getDataInicio() != null)
			camp.setDataInicio(campanha.getDataInicio());
		if (campanha.getDataFim() != null)
			camp.setDataFim(campanha.getDataFim());
		campanhaRepository.save(camp);
		return camp;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/campanha/{id}")
	public String delete(@PathVariable String id) {
		Campanha campanha = campanhaRepository.findOne(id);
		campanhaRepository.delete(campanha);

		return "campanha deleted";
	}

	public void somaDataCampanhasAtivas(Campanha novaCampanha) {

		List<Campanha> campanhaVigentesMesmaData = campanhaRepository.findCampanhasMesmoPeriodo(novaCampanha);

		campanhaVigentesMesmaData.forEach(campanhaAtual -> campanhaAtual.setDataFim(adicionaUmDiaDataFim(campanhaAtual)));
		
			campanhaVigentesMesmaData.stream().forEach(item ->
	
				campanhaVigentesMesmaData.forEach(campanhaAtual -> {
		
					campanhaVigentesMesmaData.forEach(outraCampanha -> {
		
						if (!campanhaAtual.getId().equals(outraCampanha.getId())) {
							if (campanhaAtual.getDataFim().equals(outraCampanha.getDataFim())) {
								campanhaAtual.setDataFim(adicionaUmDiaDataFim(campanhaAtual));
							}
						}
						if (campanhaAtual.getDataFim().equals(novaCampanha.getDataFim())) {
							campanhaAtual.setDataFim(adicionaUmDiaDataFim(campanhaAtual));
						}
					});
				})
			);
			
			campanhaRepository.save(campanhaVigentesMesmaData);
	}

	LocalDate adicionaUmDiaDataFim(Campanha campanha) {
		return campanha.getDataFim().plusDays(1);
	}
}