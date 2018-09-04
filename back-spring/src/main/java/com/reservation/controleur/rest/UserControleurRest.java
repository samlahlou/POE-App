package com.reservation.controleur.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reservation.controleur.model.GroupeModel;
import com.reservation.entity.UserEntity;
import com.reservation.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserControleurRest {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public ResponseEntity<List<UserEntity>> selectAll(){
			return new ResponseEntity<List<UserEntity>>(userService.selectAll(), HttpStatus.OK);
		}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserEntity> selectOne(@PathVariable("id") int id){
			return new ResponseEntity<UserEntity>(userService.selectOne(id), HttpStatus.OK);
		}
}
