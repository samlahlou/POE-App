package com.reservation.dao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.reservation.entity.GroupeEntity;
import com.reservation.test.AbstractTest;

/**
 * Test sur la classe CompteDAO.
 */
@Transactional(rollbackFor = Exception.class)
@Rollback(true)
public class GroupeDAOTest extends AbstractTest {

	@Autowired
	private IGroupeDAO dao;

	@Test
	public void testInsertOk() {
		GroupeEntity entity = new GroupeEntity();
		entity.setIcon("un icon");
		entity.setName("Yooo");
		entity = dao.save(entity);
		Assert.assertNotNull("L'entite ne doit pas etre null", entity);
		Assert.assertNotNull("L'entite doit avoir une pk non null", entity.getIdGroupe());
	}

}
