package com.reservation.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.reservation.entity.ReservationByUserEntity;

@Repository
public interface IReservationByUserDAO extends PagingAndSortingRepository<ReservationByUserEntity, Integer> {
	
	@Modifying
	@Query("DELETE FROM ReservationByUserEntity WHERE groupe.idGroupe = :varId")
	public void delByGroupId(@Param("varId")int id);
	
	
}