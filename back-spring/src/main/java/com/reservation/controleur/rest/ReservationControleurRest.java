package com.reservation.controleur.rest;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.weaver.ResolvedPointcutDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reservation.controleur.model.GroupModelUser;
import com.reservation.controleur.model.GroupeModel;
import com.reservation.controleur.model.LocalisationModel;
import com.reservation.controleur.model.SalleModel;
import com.reservation.controleur.model.meetingModel;
import com.reservation.entity.GroupeEntity;
import com.reservation.entity.ReservationByUserEntity;
import com.reservation.entity.ReservationEntity;
import com.reservation.entity.SalleEntity;
import com.reservation.entity.UserEntity;
import com.reservation.entity.UsersGroupEntity;
import com.reservation.service.GroupeService;
import com.reservation.service.ReservationService;
import com.reservation.service.SalleService;
import com.reservation.service.UserGroupService;
import com.reservation.service.UserService;

@RestController
@RequestMapping("/meeting")
@CrossOrigin
public class ReservationControleurRest {
	private static final Logger LOG = LogManager.getLogger();
	@Autowired
	private ReservationService service;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserGroupService userGroupService;
	
	@Autowired
	private GroupeService groupService;
	
	@Autowired
	private SalleService salleService;
	
	
	@GetMapping("/")
	public ResponseEntity<List<meetingModel>> selectAll()
	{
		LOG.info("> Reservation : Select ALL <");
		List<ReservationEntity> reservation = new ArrayList<>();
		List<meetingModel> modelList = new ArrayList<>();
		reservation = service.selectAll();
		for(ReservationEntity item : reservation)
		{
			meetingModel model = new meetingModel();
			//model.setDuree(); //TODO
			model.setHoraire(item.getDateStart());
			model.setId(item.getIdreservation());
			SalleModel salle = new SalleModel();
			salle.setEquipement(item.getSalle().getEquipement());
			salle.setId(item.getSalle().getIdsalle());
			salle.setName(item.getSalle().getName());
			LocalisationModel localisation = new LocalisationModel();
			localisation.setAdresse(item.getSalle().getLocalisation().getLocalisation());
			localisation.setId(item.getSalle().getLocalisation().getIdlocal());
			localisation.setName(item.getSalle().getLocalisation().getName());
			salle.setLocalisation(localisation);
			model.setSalle(salle);
			List<GroupModelUser> users = new ArrayList<>();
			GroupModelUser user = new GroupModelUser();
			UserEntity saveU = new UserEntity();
			GroupeEntity saveG = new GroupeEntity();
			saveU = item.getUser();
			user.setId(saveU.getIdusers());
			user.setIsAdmin(1);
			user.setName(saveU.getName());
			user.setFirstname(saveU.getSurname());
			user.setType(1);
			users.add(user);
			for (ReservationByUserEntity obj : item.getInvite())
			{
				user = new GroupModelUser();
				if (obj.getUser() != null)
				{
					saveU = obj.getUser();
					user.setId(saveU.getIdusers());
					user.setIsAdmin(0);
					user.setName(saveU.getName());
					user.setFirstname(saveU.getSurname());
					user.setType(1);
				}
				else if (obj.getGroupe() != null)
				{
					saveG = obj.getGroupe();
					user.setId(saveG.getIdGroupe());
					user.setIsAdmin(0);
					user.setName(saveG.getName());
					user.setFirstname(saveG.getIcon());
					user.setType(2);
				}
				else if (obj.getEmailExt() != null)
				{
					user.setIsAdmin(0);
					user.setName(obj.getEmailExt());
					user.setType(3);
				}
				users.add(user);
			}
			model.setUsers(users);
			modelList.add(model);
		}
		ResponseEntity<List<meetingModel>> respond =  new ResponseEntity<List<meetingModel>>(modelList, HttpStatus.OK);
		return respond;
	}
	
	@PostMapping("/create")
	public ResponseEntity<ReservationEntity> insert(@RequestBody meetingModel model) {
		LOG.info("> Reservation : Create Reservation <");
		LOG.info("> Reservation : {} <", model);
	List<ReservationByUserEntity> reservationByUserList=new ArrayList<>();
	ReservationEntity reservation=new ReservationEntity();
	
	reservation.setDateStart(model.getHoraire());
	//TODO reservation.setDateEnd(model.getDuree());
	//TODO reservation salleID
	SalleEntity salle = salleService.selectOne(model.salle.getId());
	reservation.setSalle(salle);
	for(GroupModelUser users:model.getUsers())
	{
		ReservationByUserEntity userGroup = new ReservationByUserEntity();
		if(users.getType() == 1)
		{
			UserEntity user = new UserEntity();
			user = userService.selectOneByNameAndSurname(users.getName(), users.getFirstname());
			if (users.getIsAdmin() == 0)
				userGroup.setUser(user);
			else if (users.getIsAdmin() == 1)
				reservation.setUser(user);
		}
		else if(users.getType() == 2)
		{
			GroupeEntity groupUser = new GroupeEntity();
			groupUser = groupService.selectOneByName(users.getName());
			userGroup.setGroupe(groupUser);
		}
		else if(users.getType()==3) {
			userGroup.setEmailExt(users.getName());
		}
		userGroup.setReservation(reservation);
		reservationByUserList.add(userGroup);
	}	
	reservation.setInvite(reservationByUserList);
	service.insert(reservation);
    return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	/*@DeleteMapping("/delete/{id}")
	public ResponseEntity<ReservationEntity> delete(@PathVariable("id") int id)
	{
		LOG.info("> Reservation : Delete One : id = {} <", id);
		ResponseEntity<ReservationEntity> respond;
		if (service.selectOne(id) != null)
		{
			service.delete(id);
			respond = new ResponseEntity<ReservationEntity>(HttpStatus.OK);
		}
		else
			respond = new ResponseEntity<ReservationEntity>(HttpStatus.NOT_FOUND);
		return respond;
	}*/
	
	
}
