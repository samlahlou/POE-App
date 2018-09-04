package com.reservation.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.reservation.entity.GroupeEntity;
import com.reservation.entity.UsersGroupEntity;


@Repository
public interface IUserGroupDAO extends PagingAndSortingRepository<UsersGroupEntity, Integer> {
	
	public List<UsersGroupEntity> findBygroupeId(GroupeEntity groupeId);
	
}