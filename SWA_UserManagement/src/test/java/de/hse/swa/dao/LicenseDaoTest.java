package de.hse.swa.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



import de.hse.swa.dao.*;
import de.hse.swa.dbmodel.*;


public class LicenseDaoTest {

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
		LicenseDao l = LicenseDao.getInstance();
		assertNotNull(l);
	}

	@Test
	public void testGetLicense() {
		LicenseDao l = LicenseDao.getInstance();
		License license = new License();
		license.setLicenseKey("1212");
		l.saveLicense(license);
		License licenses = l.getLicense(license.getLicenseID());
		assertNotNull(licenses);
	}

	@Test
	public void testGetLicenses() {
		LicenseDao l = LicenseDao.getInstance();
		License license;
		for (long i=0; i < 10; ++i) {
			license = new License();
			license.setLicenseKey("1212" +i);
			l.saveLicense(license) ;
		}
		List<License> licenses = l.getLicenses();		
		assertTrue(licenses.size()>=10);
	}

	@Test
	public void testSaveLicense() {
		LicenseDao licenseDao = LicenseDao.getInstance();
		License license = new License();
		license.setLicenseKey("1212");
		licenseDao.saveLicense(license) ;
	    License licenses = licenseDao.getLicense(license.getLicenseID());
		assertNotNull(licenses);
	}
	
	
	@Test
	public void testDeleteLicense() {
		LicenseDao licenseDao = LicenseDao.getInstance();
		List<License> licenses = licenseDao.getLicenses();
		for (License license: licenses) {
			licenseDao.deleteLicense(license.getLicenseID());
		}
		licenses = licenseDao.getLicenses();
		assertTrue(licenses.size()<1);
	}
	
	@Test
	public void testGetLincensesById(){
		License license = new License();
		license.setLicenseKey("1212");
		LicenseDao.getInstance().saveLicense(license);
		License license2 = new License();
		license2.setLicenseKey("1212");
		LicenseDao.getInstance().saveLicense(license2);
	}

	
}
