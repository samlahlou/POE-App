package com.reservation.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.reservation.entity.LocalisationEntity;

@Repository
public interface ILocalisationDAO extends PagingAndSortingRepository<LocalisationEntity, Integer> {
	
	public List<LocalisationEntity> findByName(String name);
	
}
