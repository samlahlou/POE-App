package com.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservation.dao.ILocalisationDAO;
import com.reservation.entity.LocalisationEntity;

@Service
@Transactional(rollbackFor=Exception.class)
public class LocalisationService {

	@Autowired
	private ILocalisationDAO dao;
	
	
	
	
	public LocalisationEntity insert(LocalisationEntity localisation) {
		// TODO ajouter verification
		return dao.save(localisation);
	}
	
	public List<LocalisationEntity> selectall() {
		// TODO ajouter verification
		
		return (List<LocalisationEntity>) dao.findAll();
	}
	
	public LocalisationEntity select(int id) {
		// TODO ajouter verification
		
		return dao.findOne(id);
	}
	
	public void delete(int id) {
		// TODO ajouter verification
		
		dao.delete(id);
	}
}
