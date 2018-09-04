 package com.reservation.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.reservation.entity.GroupeEntity;

public interface IGroupeDAO extends PagingAndSortingRepository<GroupeEntity, Integer>{

	public GroupeEntity findOneByName (String name);
}
