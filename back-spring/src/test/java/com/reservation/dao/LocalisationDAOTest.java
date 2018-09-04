package com.reservation.dao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.reservation.entity.LocalisationEntity;
import com.reservation.test.AbstractTest;

/**
 * Test sur la classe CompteDAO.
 */
@Transactional(rollbackFor = Exception.class)
@Rollback(true)
public class LocalisationDAOTest extends AbstractTest {

	@Autowired
	private ILocalisationDAO dao;

	@Test
	public void testInsertOk() {
		LocalisationEntity entity = new LocalisationEntity();
		entity.setLocalisation("qq part");
		entity.setName("Yooo");
		entity = dao.save(entity);
		Assert.assertNotNull("L'entite ne doit pas etre null", entity);
		Assert.assertNotNull("L'entite doit avoir une pk non null", entity.getIdlocal());
	}

}
