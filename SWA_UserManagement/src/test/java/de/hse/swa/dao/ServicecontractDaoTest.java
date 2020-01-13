package de.hse.swa.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.hse.swa.dbmodel.*;
import de.hse.swa.dao.*;

public class ServicecontractDaoTest {

	

		private static User user = null;
		
		@BeforeClass
		public static void setUpBeforeClass() throws Exception {
		/*	//PrepareTests.initDatabase();
			user = new User();
			user.setName("George Test");
			user.setEmailAddress("georg.test@test.com");
			UserDao.getInstance().saveUser(user);
			*/
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
			ServicecontractDao s = ServicecontractDao.getInstance();
			assertNotNull(s);
		}

		@Test
		public void testGetServicecontract() {
			ServicecontractDao s = ServicecontractDao.getInstance();
			Servicecontract serviceContract = new Servicecontract();
			serviceContract.setServiceContractID(1);
			s.saveServicecontract(serviceContract);
			Servicecontract serviceContracts = s.getServicecontract(serviceContract.getServiceContractID());
			assertNotNull(serviceContracts);
		}

		@Test
		public void testGetServicecontracts() {
			ServicecontractDao s = ServicecontractDao.getInstance();
			Servicecontract serviceContract;
			for (long i=0; i < 10; ++i) {
				serviceContract = new Servicecontract();
				serviceContract.setServiceContractID(1);
				s.saveServicecontract(serviceContract) ;
			}
			List<Servicecontract> serviceContracts = s.getServicecontracts();		
			assertTrue(serviceContracts.size()>=10);
		}

		@Test
		public void testSaveServicecontract() {
			ServicecontractDao serviceContractDao = ServicecontractDao.getInstance();
			Servicecontract serviceContract = new Servicecontract();
			serviceContract.setServiceContractID(1);
			serviceContractDao.saveServicecontract(serviceContract) ;
			Servicecontract serviceContracts = serviceContractDao.getServicecontract(serviceContract.getServiceContractID());
				assertNotNull(serviceContract);
		}
		
		
		@Test
		public void testDeleteServicecontract() {
			ServicecontractDao serviceContractDao = ServicecontractDao.getInstance();
			List<Servicecontract> serviceContracts = serviceContractDao.getServicecontracts();
			for (Servicecontract serviceContract: serviceContracts) {
				serviceContractDao.deleteServicecontract(serviceContract.getServiceContractID());
			}
			serviceContracts  = serviceContractDao.getServicecontracts();
			assertTrue(serviceContracts.size()<1);
		}
		
		@Test
		public void testGetServicecontractsById(){
			Servicecontract serviceContract = new Servicecontract();
			serviceContract.setServiceContractID(1);
			ServicecontractDao.getInstance().saveServicecontract(serviceContract);
			Servicecontract serviceContract2 = new Servicecontract();
			serviceContract2.setServiceContractID(1);
			ServicecontractDao.getInstance().saveServicecontract(serviceContract2);
		}

		
	}
