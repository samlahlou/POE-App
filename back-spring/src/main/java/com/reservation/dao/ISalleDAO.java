package com.reservation.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.reservation.entity.SalleEntity;

@Repository
public interface ISalleDAO extends PagingAndSortingRepository<SalleEntity, Integer> {
	
}