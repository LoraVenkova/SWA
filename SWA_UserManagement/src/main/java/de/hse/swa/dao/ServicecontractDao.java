package de.hse.swa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import de.hse.swa.dbmodel.License;
import de.hse.swa.dbmodel.Servicecontract;

public class ServicecontractDao {

	private static EntityManager em;
	private static ServicecontractDao singleton;

	private ServicecontractDao() {
		em = DaoManager.getInstance().getEntityManager();
	}

	public static ServicecontractDao getInstance() {
		if (singleton == null) {
			singleton = new ServicecontractDao();
		}
		return singleton;
	}

	public Servicecontract getServicecontract(Integer id) {
		return em.find(Servicecontract.class, id);
	}
	
	public Servicecontract getLicensesByLicenseId(int id) {
		try {
		return (Servicecontract) em.createQuery("SELECT s FROM servicecontract s WHERE s.serviceContractID=:id")
                .setParameter("licenseid", id).getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}

	public List<Servicecontract> getServicecontracts() {
		Query q = em.createQuery("select s from servicecontract s");
		List<Servicecontract> serviceContracts = q.getResultList();
		return serviceContracts;
	}
	
	public void saveServicecontract(Servicecontract serviceContract) {
		em.getTransaction().begin();
		em.persist(serviceContract);
		em.getTransaction().commit();
	}
	
	public void updateServicecontract(int id) {
		em.getTransaction().begin();
		em.refresh(ServicecontractDao.getInstance().getServicecontract(id));
		em.getTransaction().commit();
	}
	

	public void deleteServicecontract(Integer id) {

		Servicecontract cm = em.find(Servicecontract.class, id);
		if (cm != null) {
			em.getTransaction().begin();
			em.remove(cm);
			em.getTransaction().commit();
		}
	}
}
