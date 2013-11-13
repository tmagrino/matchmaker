package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class StudentController {
	
	public static Student createStudent(String name, String netID, double gpa, String email,
			Year year, List<College> colleges, List<Major> majors,
			List<Minor> minors, List<Skill> skills,
			List<Experience> priorExperience, List<Interest> interests,
			List<Course> transcript, User user) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Student s = new Student(name, netID, gpa, email, year, colleges, majors,
				minors, skills, priorExperience, interests, transcript, user);
		user.setStudent(s);
		em.persist(s);
		
		tx.commit();
		em.close();
		emf.close();
		return s;
	}
	
	public static void removeStudent(Student s) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		if (s != null) {
			s.removeColleges();
			s.removeInterests();
			s.removeMajors();
			s.removeMinors();
			s.removeSkills();
			for (Experience e : s.getPriorExperience()) {
				em.remove(e);
			}
			s.getPriorExperience().clear();
			for (Course c : s.getTranscript()) {
				em.remove(c);
			}
			s.getTranscript().clear();
			s.removeApplications();
			s.getUser().setStudent(null);
			em.remove(s);
		}
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public static Student getStudentByNetID(String netid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentController");
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
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentController");
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
	
	public static void editName(Student s, String name) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		s.setName(name);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public static void editEmail(Student s, String email) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		s.setEmail(email);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public static void editGPA(Student s, double gpa) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		s.setGpa(gpa);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public static void editYear(Student s, Year year) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		s.setYear(year);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public static void addCollege(Student s, College c) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		s.addCollege(c);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public static void removeCollege(Student s, College c) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		s.removeCollege(c);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public static void addMajor(Student s, Major m) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		s.addMajor(m);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public static void removeMajor(Student s, Major m) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		s.removeMajor(m);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public static void addMinor(Student s, Minor m) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		s.addMinor(m);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public static void removeMinor(Student s, Minor m) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		s.removeMinor(m);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public static void addSkill(Student s, Skill sk) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		s.addSkill(sk);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public static void removeSkill(Student s, Skill sk) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		s.removeSkill(sk);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public static void addInterest(Student s, Interest i) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		s.addInterest(i);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public static void removeInterest(Student s, Interest i) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		s.removeInterest(i);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public static Experience createExperience(Student s, Date startDate, Date endDate, String jobTitle, String location,
			String description) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Experience exp = new Experience(startDate, endDate, jobTitle, location,
				description);
		s.addExperience(exp);
		
		tx.commit();
		em.close();
		emf.close();
		return exp;
	}
	
	public void removeExperience(Student s, Experience e) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		s.removeExperience(e);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public static void editExperienceStartDate(Experience e, Date startDate) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		e.setStartDate(startDate);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public static void editExperienceEndDate(Experience e, Date endDate) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		e.setStartDate(endDate);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public static void editExperienceJobTitle(Experience e, String jobTitle) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		e.setJobTitle(jobTitle);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public static void editExperienceLocation(Experience e, String location) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		e.setLocation(location);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public static void editExperienceDescription(Experience e, String description) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		e.setDescription(description);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public static void editCourseNum(Course c, String coursenum) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		c.setCoursenum(coursenum);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public static void editCourseTitle(Course c, String title) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		c.setTitle(title);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public static void editCourseGrade(Course c, String grade) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		c.setGrade(grade);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public static void editCourseSemester(Course c, String semester) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		c.setSemester(semester);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public static void addApplication(Student s, Application a) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		s.addApplication(a);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public static void removeApplication(Student s, Application a) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		s.removeApplication(a);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
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
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentController");
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
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentController");
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
	
	public static void updaStudentControllerudent(Student stud, String name, String netID, double gpa, String email,
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
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentController");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        tx.begin();
        em.merge(stud);
        tx.commit();
	}
}
