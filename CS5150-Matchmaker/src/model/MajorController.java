package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MajorController {

	public static String[] getMajors() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
      
        String query = "select m from MAJOR m";
		@SuppressWarnings("unchecked")
		List<Major> majs = (List<Major>) em.createQuery(query).getResultList();
		List<String> majors = new LinkedList<String>();
		for (Major m : majs) {
			majors.add(m.getDescription());
		}
		
		return (String[]) majors.toArray();
	}
	
	public static List<Major> getMajorList() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
      
        String query = "select m from MAJOR m";
		@SuppressWarnings("unchecked")
		List<Major> majs = (List<Major>) em.createQuery(query).getResultList();
		
		return majs;
	}
	public static List<Major> parseMajor(String majors){
		
		String [] majorArray = majors.split(",");
		List<Major> majorList = new ArrayList();
		for (String m : majorArray){
			if (m != "") majorList.add(new Major(m));
		}
		return majorList;
	}
	
	public static Major getMajor(long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();

        String query = "select m from MAJOR m where m.id = " + id;
        @SuppressWarnings("unchecked")
        List<Major> majs = (List<Major>) em.createQuery(query).getResultList();
        
        return majs.get(0);
}
}
