package com.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservation.dao.IUserDAO;
import com.reservation.entity.UserEntity;

@Service
@Transactional(rollbackFor=Exception.class)
public class UserService {
	
	@Autowired
	private IUserDAO dao;
	
	public UserEntity selectOneByNameAndSurname(String name, String surname)
	{
		return dao.findOneByName(name);
	}
	
	public List<UserEntity> selectAll()
	{
		return (List<UserEntity>) dao.findAll();
	}
	
	public UserEntity selectOne(int id)
	{
		return dao.findOne(id);
	}
}
