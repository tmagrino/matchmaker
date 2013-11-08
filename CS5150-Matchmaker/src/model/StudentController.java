package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class StudentController {
		
	public static String[] calculateGpaRange(String gpa){
		switch(gpa){
		case "gt4":
			return new String[]{"4.0","4.5"};
		case "35to4":
			return new String[]{"3.5","4.0"};
		case "3to35":
			return new String[]{"3.0","3.5"};
		case "25to3":
			return new String[]{"2.5","3.0"};
		case "lt25":
			return new String[]{"0.0","2.5"};
		default:
			return new String[]{"0.0","4.5"};
		
		}
	}
	
	public static List<Student> getStudentByFilter(String name, String gpa, String year,
			String major, String skill, String interest){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        String [] gpaRange = calculateGpaRange(gpa);
        
        try{	StringBuilder query = new StringBuilder();  
        	query.append("select s from STUDENT s");
        		if (major != "") query.append(" join s.majors m");
        		if (skill != "") query.append(" join s.skills sk");
        		if (interest != "") query.append(" join s.interest i");
        		query.append(" where s.name like '"+ name + "%' and "+
        				"s.gpa between "+ gpaRange[0]+ " and " +gpaRange[1]);
        		if (year != "") query.append(" and s.year = :year");
        				
                		
        		if (major != "") query.append(" m.description like '%"+major+"'");
        		if (skill != "") query.append(" sk.description like '%"+skill+"'");
        		if (interest != "") query.append(" i.description like '%"+interest+"'");
//        		Query q = em.createQuery(query.toString());
//        		q.setParameter("name", "%'"+name+"'");
//        		q.setParameter("firstgpa", Double.parseDouble(gpaRange[0]));
//        		q.setParameter("secondgpa",Double.parseDouble(gpaRange[1]));
//        		if (year != "") q.setParameter("year",YearController.getYear(year));
        		
        		return (List<Student>) em.createQuery(query.toString()).getResultList();
        		
        }
        catch (Exception e){
        	System.out.println(e.getMessage());
        	
        }
		return null;
		
	}
	
	
	public static List<Student> getAllStudents() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try{
        String query = "select s from STUDENT s";
        return (List<Student>) em.createQuery(query).getResultList();
        }
        catch (Exception e){
        	return new ArrayList<Student>();
        }
      
	}
	public static Student getStudentByNetID(String netid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        String query = "select s from STUDENT s where s.netID = \"" + netid +"\"";
        List<Student> mylist = (List<Student>) em.createQuery(query).getResultList();
        try {
        	return mylist.get(0);
        }
        catch (Exception e) {
        	return null;
        }
	}
	
	public static Student getStudentByName(String name) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        String query = "select s from student s where s.netid = \"" + name +"\"";
        List<Student> mylist = (List<Student>) em.createQuery(query).getResultList();
        try {
        	return mylist.get(0);
        }
        catch (Exception e) {
        	return null;
        }
	}
	
	public static void updateStudent(Student stud, String name, String netID, double gpa, String email,
			Year year, List<College> colleges, List<Major> majors,
			List<Minor> minors, List<Skill> skills,
			List<Experience> priorExperience, List<Interest> interests,
			List<Course> transcript) {
		stud.setName(name);
		stud.setNetID(netID);
		stud.setGpa(gpa);
		stud.setEmail(email);
		stud.setYear(year);
		stud.setColleges(colleges);
		stud.setMajors(majors);
		stud.setMinors(minors);
		stud.setSkills(skills);
		//stud.setPriorExperience(priorExperience);
		stud.setInterests(interests);
		//stud.setTranscript(transcript);
		stud.setVersion(stud.getVersion() + 1);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        tx.begin();
        em.merge(stud);
        tx.commit();
	}
}
