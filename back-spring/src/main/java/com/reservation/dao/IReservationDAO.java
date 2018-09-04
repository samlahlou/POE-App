package com.reservation.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.reservation.entity.ReservationEntity;

@Repository
public interface IReservationDAO extends PagingAndSortingRepository<ReservationEntity, Integer> {
	
}