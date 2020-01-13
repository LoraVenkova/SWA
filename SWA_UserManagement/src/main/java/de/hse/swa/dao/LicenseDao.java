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
	
	public License getLicensesByLicenseId(int id) {
		try {
		return (License) em.createQuery("SELECT l FROM License l WHERE l.licenseID=:licenseid")
                .setParameter("licenseid", id).getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}

	public List<License> getLicenses() {
		Query q = em.createQuery("select l from License l");
		List<License> licenses = q.getResultList();
		return licenses;
	}
	
	public void saveLicense(License license) {
		em.getTransaction().begin();
		em.persist(license);
		em.getTransaction().commit();
	}
	
	public void updateLicense(int id) {
		em.getTransaction().begin();
		em.refresh(LicenseDao.getInstance().getLicense(id));
		em.getTransaction().commit();
	}
	

	public void deleteLicense(Integer id) {

		License cm = em.find(License.class, id);
		if (cm != null) {
			em.getTransaction().begin();
			em.remove(cm);
			em.getTransaction().commit();
		}
	}
}
