package com.reservation.controleur.rest;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.UsesJava7;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reservation.controleur.model.GroupeModel;
import com.reservation.controleur.model.LocalisationModel;
import com.reservation.controleur.model.GroupModelUser;
import com.reservation.entity.GroupeEntity;
import com.reservation.entity.LocalisationEntity;
import com.reservation.entity.UserEntity;
import com.reservation.entity.UsersGroupEntity;
import com.reservation.service.GroupeService;
import com.reservation.service.LocalisationService;
import com.reservation.service.UserGroupService;
import com.reservation.service.UserService;

@RestController
@RequestMapping("/groupe")
@CrossOrigin
public class GroupeControleurRest {
	private static final Logger LOG = LogManager.getLogger();
	
	@Autowired
	private GroupeService groupService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserGroupService userGroupService;
	
	private List<GroupModelUser> getUserOfGroup(GroupeEntity item)
	{
		List<GroupModelUser> userlist = new ArrayList<GroupModelUser>();
		item.getusersInGroup().forEach(users->{
			GroupModelUser user = new GroupModelUser();
			if(users.getUser() != null)
			{
				UserEntity save = new UserEntity();
				save = users.getUser();
				user.setFirstname(save.getName());
				user.setName(save.getSurname());
				user.setIsAdmin(users.getIsAdmin());
				user.setId(users.getId());
				user.setType(1);
			}
			else if(users.getgroupeId() != null)
			{
				GroupeEntity save = new GroupeEntity();
				save = users.getgroupeId();
				user.setFirstname(save.getIcon());
				user.setId(users.getId());
				user.setIsAdmin(users.getIsAdmin());
				user.setName(save.getName());
				user.setType(2);
			}
			userlist.add(user);
		});
		return userlist;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<GroupeModel>> selectAll(){
		LOG.info("> Goupe : Select ALL <");
		List<GroupeEntity> groupe = new ArrayList<>();
		List<GroupeModel> groupemodel = new ArrayList<>();
		groupe = groupService.selectAll();
		groupe.forEach(item->{
			GroupeModel model = new GroupeModel();
			model.setId(item.getIdGroupe());
			model.setName(item.getName());
			model.setIcon(item.getIcon());
			model.setUsers(getUserOfGroup(item));
			groupemodel.add(model);	
		});
		ResponseEntity<List<GroupeModel>> respond = new ResponseEntity<>(groupemodel, HttpStatus.OK);
		return respond;
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<GroupeModel> selectOne(@PathVariable("id") int id){
		LOG.info("> Goupe : Select One : id = {} <", id);
		GroupeEntity group = new GroupeEntity();
		group = groupService.selectOne(id);
		GroupeModel groupe = new GroupeModel();
		ResponseEntity<GroupeModel> respond;
		// TODO groupe.setDate(group.ge);
		
		if(group != null) {
			groupe.setId(group.getIdGroupe());
			groupe.setName(group.getName());
			groupe.setIcon(group.getIcon());
			groupe.setUsers(getUserOfGroup(group));
			respond = new ResponseEntity<>(groupe, HttpStatus.OK);
		}
		else
		{
			respond = new ResponseEntity<>(groupe, HttpStatus.NOT_FOUND);
		}	
		
		return respond;
	}
	
	@PostMapping("/create")
	public ResponseEntity<GroupeEntity> insert(@RequestBody GroupeModel model){
		LOG.info("> Goupe : Create Groupe <");
		LOG.info("> Goupe : {} <", model);
		GroupeEntity group = new GroupeEntity();
		List<UsersGroupEntity> userGroupList = new ArrayList<>();
		group.setName(model.getName());
		ResponseEntity<GroupeEntity> respond;
		if (groupService.selectOneByName(model.getName()) != null)
		{
			respond = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return respond;
		}
		group.setIcon(model.getIcon());
		//group = groupService.insert(group);
		for (GroupModelUser users : model.getUsers())
		{
			UsersGroupEntity userGroup = new UsersGroupEntity();
			if(users.getType() == 1)
			{
				UserEntity user = new UserEntity();
				user = userService.selectOneByNameAndSurname(users.getName(), users.getFirstname());
				userGroup.setUser(user);
			}
			else if(users.getType() == 2)
			{
				GroupeEntity groupUser = new GroupeEntity();
				groupUser = groupService.selectOneByName(users.getName());
				userGroup.setgroupeMemberId(groupUser);
			}
			userGroup.setIsAdmin(users.getIsAdmin());
			userGroup.setgroupeId(group);
			userGroupList.add(userGroup);
			//userGroupService.insert(userGroup);
		}
		return new ResponseEntity<>(groupService.insert(group, userGroupList), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<GroupeEntity> delete(@PathVariable("id") int id)
	{
		LOG.info("> Goupe : Delete One : id = {} <", id);
		ResponseEntity<GroupeEntity> respond;
		if (groupService.selectOne(id) != null)
		{
			groupService.delete(id);
			respond = new ResponseEntity<GroupeEntity>(HttpStatus.OK);
		}
		else
			respond = new ResponseEntity<GroupeEntity>(HttpStatus.NOT_FOUND);
		return respond;
	}
	
	@PostMapping("/update/{id}")
	public ResponseEntity<GroupeEntity> update(@PathVariable("id") int id, @RequestBody GroupeModel body)
	{
		LOG.info("> Goupe : Update One : id = {} <", id);
		LOG.info("> Goupe : {} <", body);
		ResponseEntity<GroupeEntity> respond;
		GroupeEntity groupe = new GroupeEntity();
		groupe = groupService.selectOne(id);
		if (groupe == null || body.getId() != id)
		{
			respond = new ResponseEntity<GroupeEntity>(groupe, HttpStatus.NOT_FOUND);
			return respond;
		}
		groupe.setIcon(body.getIcon());
		groupe.setIdGroupe(body.getId());
		groupe.setName(body.getName());
		List<UsersGroupEntity> usersInGroup = new ArrayList<>();
		for (GroupModelUser item : body.getUsers()){
			UsersGroupEntity user = new UsersGroupEntity();
			if (item.getType() == 1)
			{
				user.setUser(userService.selectOneByNameAndSurname(item.getName(), item.getFirstname()));
			}
			else if (item.getType() == 2)
			{
				user.setgroupeMemberId(groupService.selectOneByName(item.getName()));
			}
			user.setIsAdmin(item.getIsAdmin());
			user.setgroupeId(groupe);
			usersInGroup.add(user);
		}
		groupe.setusersInGroup(usersInGroup);
		groupe = groupService.insert(groupe, usersInGroup);
		respond = new ResponseEntity<GroupeEntity>(groupe, HttpStatus.OK);
		return respond;
	}

}
