package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CollegeController {

	public static String[] getColleges() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
      
        String query = "select c from COLLEGE c";
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
      
        String query = "select c from COLLEGE c";
		@SuppressWarnings("unchecked")
		List<College> cols = (List<College>) em.createQuery(query).getResultList();
		return cols;
	}
	 public static College getCollege(long id) {
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
         EntityManager em = emf.createEntityManager();

         String query = "select c from COLLEGE c where c.id = " + id;
         @SuppressWarnings("unchecked")
         List<College> cols = (List<College>) em.createQuery(query).getResultList();
         return cols.get(0);        
 }
	 public static List<College> parseCollege(String colleges){
			
			String [] collegeArray = colleges.split(",");
			List<College> collegeList = new ArrayList();
			for (String c : collegeArray){
				if (c != "") collegeList.add(new College(c));
			}
			return collegeList;
		}
}
