package model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CollegeController {

	public static String[] getColleges() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
      
        String query = "select * from college";
		@SuppressWarnings("unchecked")
		List<College> cols = (List<College>) em.createQuery(query).getResultList();
		List<String> colleges = new LinkedList<String>();
		for (College c : cols) {
			colleges.add(c.getDescription());
		}
		
		return (String[]) colleges.toArray();
	}
	public static List<College> getCollegeList() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
      
        String query = "select * from college";
		@SuppressWarnings("unchecked")
		List<College> cols = (List<College>) em.createQuery(query).getResultList();
		return cols;
	}
}
