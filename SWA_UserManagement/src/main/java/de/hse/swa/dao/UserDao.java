package de.hse.swa.dao;
import de.hse.swa.dbmodel.User;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;



public class UserDao {

	private static EntityManager em;
	private static UserDao singleton;

	private UserDao() {
		em = DaoManager.getInstance().getEntityManager();
	}

	public static UserDao getInstance() {
		if (singleton == null) {
			singleton = new UserDao();
		}
		return singleton;
	}

	public User getUser(Integer id) {
		return em.find(User.class, id);
	}
	
	public User getUserByUserId(int id) {
		try {
		return (User) em.createQuery("SELECT u FROM Users u WHERE u.ID=:id")
                .setParameter("id", id).getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}

	public List<User> getUsers() {
		Query q = em.createQuery("select c from Users c");
		List<User> Users = q.getResultList();
		return Users;
	}
	
	public void saveUser(User user) {
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
	}
	
	

	public void deleteUser(Integer id) {

		UserDao cm = em.find(UserDao.class, id);
		if (cm != null) {
			em.getTransaction().begin();
			em.remove(cm);
			em.getTransaction().commit();
		}
	}
}
