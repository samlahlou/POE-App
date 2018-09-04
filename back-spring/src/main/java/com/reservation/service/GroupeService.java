package com.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservation.dao.IGroupeDAO;
import com.reservation.entity.GroupeEntity;
import com.reservation.entity.UsersGroupEntity;

@Service
@Transactional(rollbackFor=Exception.class)
public class GroupeService {
	
	@Autowired
	private IGroupeDAO dao;
	
	@Autowired
	private UserGroupService service;
	
	@Autowired
	private ReservationByUserService resaByUserService;
	
	
	public List<GroupeEntity> selectAll(){
		//TODO verificaton de sécurité
		return (List<GroupeEntity>) dao.findAll();
	}
	
	public GroupeEntity selectOne(int id) {
		return dao.findOne(id);
	}
	
	public GroupeEntity selectOneByName(String name) {
		return dao.findOneByName(name);
	}
	
	public GroupeEntity insert(GroupeEntity entity, List<UsersGroupEntity> userList)
	{
		 //service.insert(userList);
		 entity.setUsersgroups(userList);
		 entity = dao.save(entity);
		 return entity;
	}
	
	public void delete(int id) {
		// TODO ajouter verification
		resaByUserService.delByGroupeID(id);
		service.deleteGroup(id);
		dao.delete(id);
	}

}
