package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class GetStudentInfo {
	 static List<Student> list = new ArrayList<Student>();
	 	 public static Student maxGPA(){return new Student();}
//		
//		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
//			EntityManager em = emf.createEntityManager();
//
//			String query = "select distinct o from STUDENT o where o.gpa = 2";
//					
//			@SuppressWarnings("unchecked")
//			Student s = (Student)em.createQuery(query).getResultList().get(0);
//			
//		 
//		 return s;
//		 
//		 
//	 }
	 public static List<Student> get() {
		 
	    for (int i = 0; i < 5; i++) {
	    	list.add(new Student("HI"));
	    }
	    return list;
	 }
}
	