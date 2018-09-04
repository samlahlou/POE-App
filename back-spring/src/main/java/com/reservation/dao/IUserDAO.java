package com.reservation.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.reservation.entity.UserEntity;

@Repository
public interface IUserDAO extends PagingAndSortingRepository<UserEntity, Integer> {
	
	public UserEntity findOneByName(String name);
	
}