package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class UserController {

	public User createUser(String name, String email, String netid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
	    EntityManager em = emf.createEntityManager();
	    EntityTransaction tx = em.getTransaction();
	    tx.begin();
		User u = new User(name, email, netid);
		em.persist(u);
		tx.commit();
		em.close();
		emf.close();
		return u;
	}
	
	public void removeUser(User u) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
	    EntityManager em = emf.createEntityManager();
	    EntityTransaction tx = em.getTransaction();
	    tx.begin();
		if (u != null) {
			em.remove(u);
		}
		tx.commit();
		em.close();
		emf.close();
	}
	
	@SuppressWarnings("unchecked")
	public User findUser(String netid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
	    EntityManager em = emf.createEntityManager();
	    String query = "select s from USER s where s.netID = \"" + netid +"\"";
        List<User> mylist = (List<User>) em.createQuery(query).getResultList();
        em.close();
        emf.close();
        try {
        	return mylist.get(0);
        }
        catch (Exception e) {
        	return null;
        }
	}
	
	public void setName(User u, String name) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
	    EntityManager em = emf.createEntityManager();
	    EntityTransaction tx = em.getTransaction();
	    tx.begin();
		u.setName(name);
		tx.commit();
		em.close();
		emf.close();
	}
	
	public void setEmail(User u, String email) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
	    EntityManager em = emf.createEntityManager();
	    EntityTransaction tx = em.getTransaction();
	    tx.begin();
		u.setEmail(email);
		tx.commit();
		em.close();
		emf.close();
	}
	
	public void setAdmin(User u, boolean makeAdmin) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
	    EntityManager em = emf.createEntityManager();
	    EntityTransaction tx = em.getTransaction();
	    tx.begin();
		u.setAdmin(makeAdmin);
		tx.commit();
		em.close();
		emf.close();
	}
	
	public void setStudent(User u, Student stud) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
	    EntityManager em = emf.createEntityManager();
	    EntityTransaction tx = em.getTransaction();
	    tx.begin();
		u.setStudent(stud);
		tx.commit();
		em.close();
		emf.close();
	}
	
	public void setResearcher(User u, Researcher r) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
	    EntityManager em = emf.createEntityManager();
	    EntityTransaction tx = em.getTransaction();
	    tx.begin();
		u.setResearcher(r);
		tx.commit();
		em.close();
		emf.close();
	}
	
	public void removeStudent(User u) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
	    EntityManager em = emf.createEntityManager();
	    EntityTransaction tx = em.getTransaction();
	    tx.begin();
		Student s = u.setStudent(null);
		em.remove(s);
		tx.commit();
		em.close();
		emf.close();
	}
	
	public void removeResearcher(User u) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
	    EntityManager em = emf.createEntityManager();
	    EntityTransaction tx = em.getTransaction();
	    tx.begin();
		Researcher r = u.setResearcher(null);
		em.remove(r);
		tx.commit();
		em.close();
		emf.close();
	}
	
}
