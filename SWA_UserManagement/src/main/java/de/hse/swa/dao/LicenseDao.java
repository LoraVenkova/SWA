package de.hse.swa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import de.hse.swa.dbmodel.License;

public class LicenseDao {

	private static EntityManager em;
	private static LicenseDao singleton;

	private LicenseDao() {
		em = DaoManager.getInstance().getEntityManager();
	}

	public static LicenseDao getInstance() {
		if (singleton == null) {
			singleton = new LicenseDao();
		}
		return singleton;
	}

	public License getLicense(Integer id) {
		return em.find(License.class, id);
	}
	
	public License getLicenseByLicenseId(int id) {
		try {
		return (License) em.createQuery("SELECT l FROM License l WHERE l.licenseID=:licenseid")
                .setParameter("licenseid", id).getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}

	public List<License> getUsers() {
		Query q = em.createQuery("select c from users c");
		List<License> users = q.getResultList();
		return users;
	}
	
	public void saveLicense(License license) {
		em.getTransaction().begin();
		em.persist(license);
		em.getTransaction().commit();
	}
	
	

	public void deleLicense(Integer id) {

		License cm = em.find(License.class, id);
		if (cm != null) {
			em.getTransaction().begin();
			em.remove(cm);
			em.getTransaction().commit();
		}
	}
}
