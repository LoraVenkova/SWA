package de.hse.swa.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.hse.swa.dbmodel.Company;
import de.hse.swa.dbmodel.License;
import de.hse.swa.dbmodel.User;

public class CompanyDaoTest {

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
		CompanyDao c = CompanyDao.getInstance();
		assertNotNull(c);
	}

	@Test
	public void testGetCompany() {
		CompanyDao c = CompanyDao.getInstance();
		Company company = new Company();
		company.setName("Company 1");
		c.saveCompany(company);
		Company companies = c.getCompany(company.getCompanyID());
		assertNotNull(companies);
	}

	@Test
	public void testGetCompanies() {
		CompanyDao c = CompanyDao.getInstance();
		Company company;
		for (long i=0; i < 10; ++i) {
			company = new Company();
			company.setName("Company 1" +i);
			c.saveCompany(company) ;
		}
		List<Company> companies = c.getCompanies();		
		assertTrue(companies.size()>=10);
	} 
	
	
	@Test
	public void testSaveCompany() {
		CompanyDao companyDao = CompanyDao.getInstance();
		Company company = new Company();
		company.setName("Company 1");
		companyDao.saveCompany(company) ;
	    Company companies = companyDao.getCompany(company.getCompanyID());
		assertNotNull(companies);
	}
	
	
	@Test
	public void testDeleteCompany() {
		CompanyDao companyDao = CompanyDao.getInstance();
		List<Company> companies = companyDao.getCompanies();
		for (Company company: companies) {
			companyDao.deleteCompany(company.getCompanyID());
		}
		companies = companyDao.getCompanies();
		assertTrue(companies.size()<1);
	}
	

	

	
}
