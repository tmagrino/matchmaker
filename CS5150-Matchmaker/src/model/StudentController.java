package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class StudentController {
	
	public static Student createStudent(EntityManager em, String name, String netID, double gpa, String email,
			Year year, List<College> colleges, List<Major> majors,
			List<Minor> minors, List<Skill> skills,
			List<Experience> priorExperience, List<Interest> interests,
			List<Course> transcript, User user) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Student s = new Student(name, netID, gpa, email, year, colleges, majors,
				minors, skills, priorExperience, interests, transcript, user);
		user.setStudent(s);
		em.persist(s);
		
		tx.commit();
		return s;
	}
	
	public static void removeStudent(EntityManager em, Student s) {
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
	}
	
	public static Student getStudentByNetID(EntityManager em, String netid) {
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
	
	public static Student getStudentByName(EntityManager em, String name) {
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
	
	public static void editName(EntityManager em, Student s, String name) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		s.setName(name);
		
		tx.commit();
	}
	
	public static void editEmail(EntityManager em, Student s, String email) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		s.setEmail(email);
		
		tx.commit();
	}
	
	public static void editGPA(EntityManager em, Student s, double gpa) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		s.setGpa(gpa);
		
		tx.commit();
	}
	
	public static void editYear(EntityManager em, Student s, Year year) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		s.setYear(year);
		
		tx.commit();
	}
	
	public static void addCollege(EntityManager em, Student s, College c) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		s.addCollege(c);
		
		tx.commit();
	}
	
	public static void removeCollege(EntityManager em, Student s, College c) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		s.removeCollege(c);
		
		tx.commit();
	}
	
	public static void addMajor(EntityManager em, Student s, Major m) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		s.addMajor(m);
		
		tx.commit();
	}
	
	public static void removeMajor(EntityManager em, Student s, Major m) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		s.removeMajor(m);
		
		tx.commit();
	}
	public static void update(EntityManager em, Student s, String ids, String type) 
			throws NumberFormatException, InstantiationException, IllegalAccessException {
		String[] idList =ids.split(",");
		s.remove(type);
		for (String id : idList)
			if (id.length()>0)
			StudentController.add(em, s, ListController.getItemById(em,Long.parseLong(id),type));
	}
	
	private static void add(EntityManager em, Student s, MultipleItem item) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		if(item instanceof Major){
			s.addMajor((Major) item);
		}
		if(item instanceof Minor){
			s.addMinor((Minor) item);
		}
		if (item instanceof College){
			s.addCollege((College) item );
		}
		if (item instanceof Skill){
			s.addSkill((Skill) item );
		}
		if (item instanceof Interest){
			s.addInterest((Interest) item );
		}
		tx.commit();
		
	}

	public static void addMinor(EntityManager em, Student s, Minor m) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		s.addMinor(m);
		
		tx.commit();
	}
	public static void removeMinor(EntityManager em, Student s, Minor m) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		s.removeMinor(m);
		
		tx.commit();
	}

	public static void addSkill(EntityManager em, Student s, Skill sk) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		s.addSkill(sk);
		
		tx.commit();
	}
	
	public static void removeSkill(EntityManager em, Student s, Skill sk) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		s.removeSkill(sk);
		
		tx.commit();
	}
	
	public static void addInterest(EntityManager em, Student s, Interest i) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		s.addInterest(i);
		
		tx.commit();
	}
	
	public static void removeInterest(EntityManager em, Student s, Interest i) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		s.removeInterest(i);
		
		tx.commit();
	}
	
	public static Experience createExperience(EntityManager em, Student s, Date startDate, Date endDate, String jobTitle, String location,
			String description) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Experience exp = new Experience(startDate, endDate, jobTitle, location,
				description);
		s.addExperience(exp);
		
		tx.commit();
		return exp;
	}
	
	public void removeExperience(EntityManager em, Student s, Experience e) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		s.removeExperience(e);
		
		tx.commit();
	}
	
	public static void editExperienceStartDate(EntityManager em, Experience e, Date startDate) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		e.setStartDate(startDate);
		
		tx.commit();
	}
	
	public static void editExperienceEndDate(EntityManager em, Experience e, Date endDate) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		e.setStartDate(endDate);
		
		tx.commit();
	}
	
	public static void editExperienceJobTitle(EntityManager em, Experience e, String jobTitle) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		e.setJobTitle(jobTitle);
		
		tx.commit();
	}
	
	public static void editExperienceLocation(EntityManager em, Experience e, String location) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		e.setLocation(location);
		
		tx.commit();
	}
	
	public static void editExperienceDescription(EntityManager em, Experience e, String description) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		e.setDescription(description);
		
		tx.commit();
	}
	
	public static void editCourseNum(EntityManager em, Course c, String coursenum) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		c.setCoursenum(coursenum);
		
		tx.commit();
	}
	
	public static void editCourseTitle(EntityManager em, Course c, String title) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		c.setTitle(title);
		
		tx.commit();
	}
	
	public static void editCourseGrade(EntityManager em, Course c, String grade) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		c.setGrade(grade);
		
		tx.commit();
	}
	
	public static void editCourseSemester(EntityManager em, Course c, String semester) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		c.setSemester(semester);
		
		tx.commit();
	}
	
	public static void addApplication(EntityManager em, Student s, Application a) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		s.addApplication(a);
		
		tx.commit();
	}
	
	public static void removeApplication(EntityManager em, Student s, Application a) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		s.removeApplication(a);
		
		tx.commit();
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
	
	public static List<Student> getStudentByFilter(EntityManager em, String name, String gpa, String year,
			String major, String skill, String interest){
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
	
	
	public static List<Student> getAllStudents(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        try {
        String query = "select s from STUDENT s";
        return (List<Student>) em.createQuery(query).getResultList();
        }
        catch (Exception e){
        	return new ArrayList<Student>();
        } 
	}
}
