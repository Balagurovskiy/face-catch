package com.my.app.face_catch;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.my.app.face_catch.db.dao.PersonnelDao;
import com.my.app.face_catch.db.dao.PersonnelDaoConfiguration;
import com.my.app.face_catch.db.dto.Personnel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersonnelDaoConfiguration.class})
public class PersonnelDaoTest {

	@Autowired
	PersonnelDao personnelDao;

	private Integer currentId = -1;

	@Test
	public void personnelTransactionsTest() {
		final String PERSONNEL_TEST_NAME = "TEST";
		
		currentId = personnelDao.savePersonnel(PERSONNEL_TEST_NAME, new byte[] {1}, 1);
		boolean conditionInsert = currentId > 0;
		assertTrue("Test transaction of personnel entity to DB ", conditionInsert);
		
		Optional<Personnel> optionalPersonnel = personnelDao.getPersonnelById(currentId);
		boolean conditionPersonnelExists = optionalPersonnel.isPresent();
		if (conditionPersonnelExists) {
			boolean conditionSelectById = optionalPersonnel.get().getName().equals(PERSONNEL_TEST_NAME);
			assertTrue("Selection of previously inserted test entity", conditionSelectById);
		} else {
			assertTrue("Failed to select test personnel", false);
		}
		
		boolean conditionDelete = personnelDao.deletePersonnel(currentId);
		assertTrue("Deletion of test entity", conditionDelete);
	}
}
