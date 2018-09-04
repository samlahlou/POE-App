package com.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservation.dao.IGroupeDAO;
import com.reservation.dao.IUserGroupDAO;
import com.reservation.entity.GroupeEntity;
import com.reservation.entity.UsersGroupEntity;

@Service
@Transactional(rollbackFor=Exception.class)
public class UserGroupService {
	
	@Autowired
	private IUserGroupDAO dao;
	
	@Autowired
	private IGroupeDAO groupedao;

	public List<UsersGroupEntity> insert(List<UsersGroupEntity> entity)
	{
		return (List<UsersGroupEntity>) dao.save(entity);
	}
	
	public void deleteGroup(int id)
	{
		GroupeEntity groupe = groupedao.findOne(id);
		List<UsersGroupEntity> listuser = groupe.getusersInGroup();
		int i = 0;
		while (i < listuser.size())
		{
			dao.delete(listuser.get(i).getId());
			i++;
		}
	}
}
