package com.reservation.controleur.rest;

import java.awt.Dialog.ModalExclusionType;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reservation.controleur.model.LocalisationModel;
import com.reservation.entity.LocalisationEntity;
import com.reservation.service.LocalisationService;

@RestController
@RequestMapping("/place")
@CrossOrigin
public class LocalisationControleurRest {
	private static final Logger LOG = LogManager.getLogger();
	
	@Autowired
	private LocalisationService service;
	
	@PostMapping("/create")
	public ResponseEntity<LocalisationEntity> insert(@RequestBody LocalisationModel model){
		LOG.info("> Localisation : Create Localisation <");
		LOG.info("> Localisation : {} <", model);
		
		LocalisationEntity localisation = new LocalisationEntity();
		localisation.setLocalisation(model.getAdresse());
		localisation.setName(model.getName());
		// TODO gestion des erreurs
		localisation = service.insert(localisation);
		
		ResponseEntity<LocalisationEntity> respond = new ResponseEntity<LocalisationEntity>(localisation, HttpStatus.OK);
		return respond;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<LocalisationModel>> selectAll(){
		LOG.info("> Localistion : Select ALL <");
		List<LocalisationEntity> localisation = new ArrayList<LocalisationEntity>();
		List<LocalisationModel> modelLocalisation = new ArrayList<LocalisationModel>();
		localisation = service.selectall();
		localisation.forEach(item->{
			LocalisationModel model = new LocalisationModel();
			model.setAdresse(item.getLocalisation());
			model.setName(item.getName());
			model.setId(item.getIdlocal());
			modelLocalisation.add(model);	
		});
		ResponseEntity<List<LocalisationModel>> respond = new ResponseEntity<List<LocalisationModel>>(modelLocalisation, HttpStatus.OK);
		return respond;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LocalisationModel> select(@PathVariable("id") int id){
		LocalisationEntity localisation = new LocalisationEntity();
		localisation = service.select(id);
		LocalisationModel model = new LocalisationModel();
		ResponseEntity<LocalisationModel> respond;
		if (localisation != null)
		{
			model.setAdresse(localisation.getLocalisation());
			model.setName(localisation.getName());
			model.setId(localisation.getIdlocal());
			respond = new ResponseEntity<LocalisationModel>(model, HttpStatus.OK);
		}
		else
		{
			model.setId(id);
			respond = new ResponseEntity<LocalisationModel>(model, HttpStatus.NOT_FOUND);
		}
		return respond;
	}
	
	@PostMapping("/update/{id}")
	public ResponseEntity<LocalisationModel> update(@PathVariable("id") int id, @RequestBody LocalisationModel body)
	{
		ResponseEntity<LocalisationModel> respond;
		LocalisationEntity localisation = new LocalisationEntity();
		localisation = service.select(id);
		LocalisationModel model = new LocalisationModel();
		if (localisation == null || body.getId() != id)
		{
			respond = new ResponseEntity<LocalisationModel>(model, HttpStatus.NOT_FOUND);
			return respond;
		}
		localisation.setLocalisation(body.getAdresse());
		localisation.setName(body.getName());
		localisation = service.insert(localisation);
		model.setAdresse(localisation.getLocalisation());
		model.setName(localisation.getName());
		model.setId(localisation.getIdlocal());
		respond = new ResponseEntity<LocalisationModel>(model, HttpStatus.OK);
		return respond;
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<LocalisationModel> delete(@PathVariable("id") int id)
	{
		ResponseEntity<LocalisationModel> respond;
		if (service.select(id) != null)
		{
			service.delete(id);
			respond = new ResponseEntity<LocalisationModel>(HttpStatus.OK);
		}
		else
			respond = new ResponseEntity<LocalisationModel>(HttpStatus.NOT_FOUND);
		return respond;
	}
}
