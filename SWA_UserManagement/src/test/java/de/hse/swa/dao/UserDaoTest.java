package de.hse.swa.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.hse.swa.dbmodel.License;
import de.hse.swa.dbmodel.User;
import de.hse.swa.dao.UserDao;

public class UserDaoTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetInstance() {
		UserDao c = UserDao.getInstance();
		assertNotNull(c);
	}

	@Test
	public void testGetUser() {
		UserDao c = UserDao.getInstance();
		User user = new User();
		user.setName("John Doe");
		c.saveUser(user);
		User users = c.getUser(user.getUserID());
		assertNotNull(users);
	}
	
	
	@Test
	public void testGetUsers() {
		UserDao c = UserDao.getInstance();
		User user;
		for (long i=0; i < 10; ++i) {
			user = new User();
			user.setName("John Doe sen. "+i);
			c.saveUser(user) ;
		}
		List<User> users = c.getUsers();
		assertTrue(users.size()>=10);
	}
	
	@Test
	public void testSaveUser() {
		UserDao userDao = UserDao.getInstance();
		User user = new User();
		user.setName("Mary Save");
		userDao.saveUser(user) ;
		User users = userDao.getUser(user.getUserID());
		assertNotNull(users);
	}

	@Test
	public void testDeleteUser() {
		UserDao userDao = UserDao.getInstance();
		List<User> users = userDao.getUsers();
		for (User user: users) {
			userDao.deleteUser(user.getUserID());
		}
		users = userDao.getUsers();
		assertTrue(users.size()<1);
	}
	
	


}
