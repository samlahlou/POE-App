package com.reservation.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.reservation.entity.LocalisationEntity;
import com.reservation.entity.ReservationByUserEntity;
import com.reservation.entity.ReservationEntity;
import com.reservation.entity.SalleEntity;
import com.reservation.entity.UserEntity;
import com.reservation.test.AbstractTest;

/**
 * Test sur la classe CompteDAO.
 */
@Transactional(rollbackFor = Exception.class)
@Rollback(true)
public class ReservationByUserDAOTest extends AbstractTest {

	@Autowired
	private IReservationByUserDAO resadao;
	
	@Autowired
	private ILocalisationDAO locadao;
	
	@Autowired
	private ISalleDAO salledao;
	
	@Autowired
	private IUserDAO userdao;
	
	@Autowired
	private IReservtionDAO reservationdao;
	

	@Test
	public void testInsertExtOk() {
		ReservationByUserEntity entity = new ReservationByUserEntity();
		ReservationEntity reservation = new ReservationEntity();
		SalleEntity salle = new SalleEntity();
		LocalisationEntity localisation = new LocalisationEntity();
		UserEntity creator = new UserEntity();
		List<ReservationByUserEntity> list = new ArrayList<>();
		
		localisation.setLocalisation("Tour Bagnolet");
		localisation.setName("Harvest Paris");
		localisation.setIdlocal(0);
		
		locadao.save(localisation);
		
		salle.setLocalisation(localisation);
		salle.setName("Etage 2, salle 5");
		salle.setEquipement("videoprojecteur");
		salle.setIdsalle(0);
		
		salledao.save(salle);
		
		creator.setGroupCreator(1);
		creator.setAdmin(0);
		creator.setEmail("b@b.fr");
		creator.setIdLong("123456789");
		creator.setLocalisation(localisation);
		creator.setName("jhon");
		creator.setPassword("password");
		creator.setSurname("Doe");
		creator.setIdusers(0);
		
		userdao.save(creator);
		
		reservation.setSalle(salle);
		Date date = new Date();
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		reservation.setDateStart(calendar.getTime());
		calendar.add(calendar.HOUR_OF_DAY, 2);
		reservation.setDateEnd(calendar.getTime());
		reservation.setUser(creator);
		reservation.setInvite(list);
		reservation.setIdreservation(0);
		
		reservationdao.save(reservation);
		
		entity.setEmailExt("a@a.fr");
		entity.setReservation(reservation); 
		
		entity = resadao.save(entity);
		
		Assert.assertNotNull("L'entite ne doit pas etre null", entity);
		Assert.assertNotNull("L'entite doit avoir une pk non null", entity.getId());
	}

}
