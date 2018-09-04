package com.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservation.dao.IReservationByUserDAO;
import com.reservation.entity.ReservationByUserEntity;

@Service
@Transactional(rollbackFor=Exception.class)
public class ReservationByUserService {
	
	@Autowired
	private IReservationByUserDAO dao;
	
	public List<ReservationByUserEntity> insert(List <ReservationByUserEntity> reservationByUserEntity) {
	
		return (List<ReservationByUserEntity>) dao.save(reservationByUserEntity);
		
	}
	
	public void delete(List <ReservationByUserEntity> reservationByUserEntity) {
		
		 dao.delete(reservationByUserEntity);	
	}
	
	public void delByGroupeID(int id)
	{
		dao.delByGroupId(id);
	}
	

}
