package de.hse.swa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import de.hse.swa.dbmodel.Company;


public class CompanyDao {

	private static EntityManager em;
	private static CompanyDao singleton;

	private CompanyDao() {
		em = DaoManager.getInstance().getEntityManager();
	}

	public static CompanyDao getInstance() {
		if (singleton == null) {
			singleton = new CompanyDao();
		}
		return singleton;
	}

	public Company getCompany(Integer id) {
		return em.find(Company.class, id);
	}
	
	public Company getUserByUserId(int id) {
		try {
		return (Company) em.createQuery("SELECT c FROM Company c WHERE c.companyID=:companyid")
                .setParameter("companyid", id).getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}

	public List<Company> getUsers() {
		Query q = em.createQuery("select c from Company c");
		List<Company> company = q.getResultList();
		return company;
	}
	
	public void saveCompany(Company company) {
		em.getTransaction().begin();
		em.persist(company);
		em.getTransaction().commit();
	}
	
	

	public void deleteCompany(Integer id) {

		Company cm = em.find(Company.class, id);
		if (cm != null) {
			em.getTransaction().begin();
			em.remove(cm);
			em.getTransaction().commit();
		}
	}
}
