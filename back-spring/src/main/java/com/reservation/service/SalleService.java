package com.reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservation.dao.ISalleDAO;
import com.reservation.entity.SalleEntity;

@Service
@Transactional(rollbackFor=Exception.class)
public class SalleService {

	@Autowired
	private ISalleDAO dao;
	
	public SalleEntity selectOne(int id)
	{
		return dao.findOne(id);
	}
}
