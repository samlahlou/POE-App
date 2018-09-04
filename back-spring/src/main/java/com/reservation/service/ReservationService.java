package com.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservation.dao.IReservationDAO;
import com.reservation.entity.ReservationByUserEntity;
import com.reservation.entity.ReservationEntity;

@Service
@Transactional(rollbackFor=Exception.class)
public class ReservationService {
	
	@Autowired
	private IReservationDAO dao;
	
	@Autowired
	private ReservationByUserService service;
	
	
	public List<ReservationEntity> selectAll()
	{
		return (List<ReservationEntity>) dao.findAll();
	}
	
	public ReservationEntity insert(ReservationEntity reservationEntity) {
		
		//service.insert(reservationByUserEntity)
		return (ReservationEntity)  dao.save(reservationEntity);
	}
	
	public void delete(ReservationEntity reservationEntity,List<ReservationByUserEntity> reservationByUserEntity ) {
		
		service.delete(reservationByUserEntity);
		dao.delete(reservationEntity);
	}
	
	public ReservationEntity selectOne (int id)
	{
		return dao.findOne(id);
	}
	
	/*public void delete(int id) {
		// TODO ajouter verification
		resaByUserService.delByGroupeID(id);
		service.deleteGroup(id);
		dao.delete(id);
	}*/
	/*public void update(ReservationEntity reservationEntity) {
		
		dao.save()
	}*/

}
