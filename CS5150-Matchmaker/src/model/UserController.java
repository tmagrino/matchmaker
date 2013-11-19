package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class UserController {

	public static User createUser(EntityManager em, String name, String email, String netid) {
	    EntityTransaction tx = em.getTransaction();
	    tx.begin();
		User u = new User(name, email, netid);
		em.persist(u);
		tx.commit();
		return u;
	}
	
	public static void deleteUser(EntityManager em, User u) {
	    EntityTransaction tx = em.getTransaction();
	    tx.begin();
		if (u != null) {
			u.setStudent(null);
			u.setResearcher(null);
			em.remove(u);
		}
		tx.commit();
	}
	
	@SuppressWarnings("unchecked")
	public static User findUser(EntityManager em, String netid) {
	    String query = "select s from USER s where s.netID = \"" + netid +"\"";
        List<User> mylist = (List<User>) em.createQuery(query).getResultList();
        try {
        	return mylist.get(0);
        }
        catch (Exception e) {
        	return null;
        }
	}
	
	public static void setName(EntityManager em, User u, String name) {
	    EntityTransaction tx = em.getTransaction();
	    tx.begin();
		u.setName(name);
		tx.commit();
	}
	
	public static void setEmail(EntityManager em, User u, String email) {
	    EntityTransaction tx = em.getTransaction();
	    tx.begin();
		u.setEmail(email);
		tx.commit();
	}
	
	public static void setAdmin(EntityManager em, User u, boolean makeAdmin) {
	    EntityTransaction tx = em.getTransaction();
	    tx.begin();
		u.setAdmin(makeAdmin);
		tx.commit();
	}
	
	public static void setStudent(EntityManager em, User u, Student stud) {
	    EntityTransaction tx = em.getTransaction();
	    tx.begin();
		u.setStudent(stud);
		tx.commit();
	}
	
	public static void setResearcher(EntityManager em, User u, Researcher r) {
	    EntityTransaction tx = em.getTransaction();
	    tx.begin();
		u.setResearcher(r);
		tx.commit();
	}
	
	public static void removeStudent(EntityManager em, User u) {
	    EntityTransaction tx = em.getTransaction();
	    tx.begin();
		Student s = u.setStudent(null);
		em.remove(s);
		tx.commit();
	}
	
	public static void removeResearcher(EntityManager em, User u) {
	    EntityTransaction tx = em.getTransaction();
	    tx.begin();
		Researcher r = u.setResearcher(null);
		em.remove(r);
		tx.commit();
	}
}
