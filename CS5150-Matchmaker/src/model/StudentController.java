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
		if (user == null) {
			return null;
		}
		tx.begin();
		
		Student s = new Student(name, netID, gpa, email, year, colleges, majors,
				minors, skills, priorExperience, interests, transcript, user);
		StudentSettings set = new StudentSettings();
		s.setSettings(set);
		set.setStudent(s);
		user.setStudent(s);
		em.persist(s);
		em.persist(set);
		
		tx.commit();
		return s;
	}
	
	public static void removeStudent(EntityManager em, Student s) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		User u = null;
		if (s != null) {
			// Remove Applications
			List<Application> toDelete = s.removeApplications();
			tx.commit();
			for (Application a : toDelete) {
				ApplicationController.deleteApplication(em, a);
			}
			tx.begin();
			
			// Remove incoming pointers
			s.removeColleges();
			s.removeInterests();
			s.removeMajors();
			s.removeMinors();
			s.removeSkills();
			s.removeHiddenByResearchers();
			u = s.getUser();
			s.getUser().setStudent(null);
			s.setUser(null);
			s.getSettings().removeProjects();
			s.getSettings().setStudent(null);
			s.setSettings(null);
			
			// Remove entities from database
			em.remove(s.getSettings());
			em.remove(s);
		}
		tx.commit();
		// Remove user if it has no further roles
		if (u != null) {
			if (u.getResearcher() == null && !u.isAdmin) {
				UserController.deleteUser(em, u);
			}
		}
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
        
        String query = "select s from STUDENT s where s.name= \"" + name +"\"";
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
		if (s == null) {
			return;
		}
		tx.begin();
		
		s.setName(name);
		
		tx.commit();
	}
	
	public static void editEmail(EntityManager em, Student s, String email) {
		EntityTransaction tx = em.getTransaction();
		if (s == null) {
			return;
		}
		tx.begin();
		
		s.setEmail(email);
		
		tx.commit();
	}
	
	public static void editGPA(EntityManager em, Student s, double gpa) {
		EntityTransaction tx = em.getTransaction();
		if (s == null) {
			return;
		}
		tx.begin();
		
		if (gpa < 0) {
			s.setGpa(0);
		}
		else {
			s.setGpa(gpa);
		}
		
		tx.commit();
	}
	
	public static void editYear(EntityManager em, Student s, Year year) {
		EntityTransaction tx = em.getTransaction();
		if (s == null) {
			return;
		}
		tx.begin();
		
		s.setYear(year);
		
		tx.commit();
	}
	
	public static void addCollege(EntityManager em, Student s, College c) {
		EntityTransaction tx = em.getTransaction();
		if (s == null) {
			return;
		}
		tx.begin();
		
		s.addCollege(c);
		
		tx.commit();
	}
	
	public static void removeCollege(EntityManager em, Student s, College c) {
		EntityTransaction tx = em.getTransaction();
		if (s == null || c == null) {
			return;
		}
		tx.begin();
		
		s.removeCollege(c);
		
		tx.commit();
	}
	
	public static void addMajor(EntityManager em, Student s, Major m) {
		EntityTransaction tx = em.getTransaction();
		if (s == null || m == null) {
			return;
		}
		tx.begin();
		
		s.addMajor(m);
		
		tx.commit();
	}
	
	public static void removeMajor(EntityManager em, Student s, Major m) {
		EntityTransaction tx = em.getTransaction();
		if (s == null || m == null) {
			return;
		}
		tx.begin();
		
		s.removeMajor(m);
		
		tx.commit();
	}
	public static void update(EntityManager em, Student s, String ids, String type) 
			throws InstantiationException, IllegalAccessException {
		System.out.println("Updating "+type);
		if (s == null) {
			return;
		}
		String[] idList =ids.split(",");
		s.remove(type);
		for (String id : idList) {
			if (id.length()>0){
				try {
					StudentController.add(em, s, FieldValueController.getFieldValueById(em,Long.parseLong(id),type));
				}
				catch(NumberFormatException e){
					StudentController.add(em, s, FieldValueController.createFieldValue(em, id, type));
				}
			}
		}
	}
	
	private static void add(EntityManager em, Student s, FieldValue item) {
		if (item == null || s == null) {
			return;
		}
		
		if(item instanceof Major){
			addMajor(em, s, (Major)item);
		}
		if(item instanceof Minor){
			addMinor(em, s, (Minor) item);
		}
		if (item instanceof College){
			addCollege(em, s, (College) item );
		}
		if (item instanceof Skill){
			addSkill(em, s, (Skill) item );
		}
		if (item instanceof Interest){
			addInterest(em, s, (Interest) item );
		}
	}

	public static void addMinor(EntityManager em, Student s, Minor m) {
		EntityTransaction tx = em.getTransaction();
		if (s == null || m == null) {
			return;
		}
		tx.begin();
		
		s.addMinor(m);
		
		tx.commit();
	}
	public static void removeMinor(EntityManager em, Student s, Minor m) {
		EntityTransaction tx = em.getTransaction();
		if (s == null || m == null) {
			return;
		}
		tx.begin();
		
		s.removeMinor(m);
		
		tx.commit();
	}

	public static void addSkill(EntityManager em, Student s, Skill sk) {
		EntityTransaction tx = em.getTransaction();
		if (s == null || sk == null) {
			return;
		}
		tx.begin();
		
		s.addSkill(sk);
		
		tx.commit();
	}
	
	public static void removeSkill(EntityManager em, Student s, Skill sk) {
		EntityTransaction tx = em.getTransaction();
		if (s == null || sk == null) {
			return;
		}
		tx.begin();
		
		s.removeSkill(sk);
		
		tx.commit();
	}
	
	public static void addInterest(EntityManager em, Student s, Interest i) {
		EntityTransaction tx = em.getTransaction();
		if (s == null || i == null) {
			return;
		}
		tx.begin();
		
		s.addInterest(i);
		
		tx.commit();
	}
	
	public static void removeInterest(EntityManager em, Student s, Interest i) {
		EntityTransaction tx = em.getTransaction();
		if (s == null || i == null) {
			return;
		}
		tx.begin();
		
		s.removeInterest(i);
		
		tx.commit();
	}
	
	public static Experience createExperience(EntityManager em, Student s, Date startDate, Date endDate, String jobTitle, String location,
			String description) {
		EntityTransaction tx = em.getTransaction();
		if (s == null || startDate == null) {
			return null;
		}
		tx.begin();
		
		Experience exp = new Experience(startDate, endDate, jobTitle, location,
				description);
		s.addExperience(exp);
		
		tx.commit();
		return exp;
	}
	
	public void removeExperience(EntityManager em, Student s, Experience e) {
		EntityTransaction tx = em.getTransaction();
		if (s == null || e == null) {
			return;
		}
		tx.begin();
		
		s.removeExperience(e);
		
		tx.commit();
	}
	
	public static void editExperienceStartDate(EntityManager em, Experience e, 
			Date startDate) {
		EntityTransaction tx = em.getTransaction();
		if (e == null || startDate == null) {
			return;
		}
		tx.begin();
		
		e.setStartDate(startDate);
		
		tx.commit();
	}
	
	public static void editExperienceEndDate(EntityManager em, Experience e, Date endDate) {
		EntityTransaction tx = em.getTransaction();
		if (e == null || endDate == null) {
			return;
		}
		tx.begin();
		
		e.setStartDate(endDate);
		
		tx.commit();
	}
	
	public static void editExperienceJobTitle(EntityManager em, Experience e, String jobTitle) {
		EntityTransaction tx = em.getTransaction();
		if (e == null) {
			return;
		}
		tx.begin();
		
		e.setJobTitle(jobTitle);
		
		tx.commit();
	}
	
	public static void editExperienceLocation(EntityManager em, Experience e, String location) {
		EntityTransaction tx = em.getTransaction();
		if (e == null) {
			return;
		}
		tx.begin();
		
		e.setLocation(location);
		
		tx.commit();
	}
	
	public static void editExperienceDescription(EntityManager em, Experience e, String description) {
		EntityTransaction tx = em.getTransaction();
		if (e == null) {
			return;
		}
		tx.begin();
		
		e.setDescription(description);
		
		tx.commit();
	}
	
	public static void editCourseNum(EntityManager em, Course c, String coursenum) {
		EntityTransaction tx = em.getTransaction();
		if (c == null) {
			return;
		}
		tx.begin();
		
		c.setCoursenum(coursenum);
		
		tx.commit();
	}
	
	public static void editCourseTitle(EntityManager em, Course c, String title) {
		EntityTransaction tx = em.getTransaction();
		if (c == null) {
			return;
		}
		tx.begin();
		
		c.setTitle(title);
		
		tx.commit();
	}
	
	public static void editCourseGrade(EntityManager em, Course c, String grade) {
		EntityTransaction tx = em.getTransaction();
		if (c == null) {
			return;
		}
		tx.begin();
		
		c.setGrade(grade);
		
		tx.commit();
	}
	
	public static void editCourseSemester(EntityManager em, Course c, String semester) {
		EntityTransaction tx = em.getTransaction();
		if (c == null) {
			return;
		}
		tx.begin();
		
		c.setSemester(semester);
		
		tx.commit();
	}
	
	public static void addApplication(EntityManager em, Student s, Application a) {
		EntityTransaction tx = em.getTransaction();
		if (s == null || a == null) {
			return;
		}
		tx.begin();
		
		s.addApplication(a);
		
		tx.commit();
	}
	
	public static void removeApplication(EntityManager em, Student s, Application a) {
		EntityTransaction tx = em.getTransaction();
		if (s == null || a == null) {
			return;
		}
		tx.begin();
		
		s.removeApplication(a);
		
		tx.commit();
	}
	
	public static void addHiddenProject(EntityManager em, Student s, Project p) {
		EntityTransaction tx = em.getTransaction();
		if (s == null || p == null) {
			return;
		}
		tx.begin();
		
		s.getSettings().addProject(p);
		
		tx.commit();
	}
	
	public static void removeHiddenProject(EntityManager em, Student s, Project p) {
		EntityTransaction tx = em.getTransaction();
		if (s == null || p == null) {
			return;
		}
		tx.begin();
		
		s.getSettings().removeProject(p);
		
		tx.commit();
	}
	
	public static List<Long> getStudentProjects(EntityManager em, Student s){
		if (s == null) {
			return null;
		}
		try {
			
			String query = "select a.applicationProject from APPLICATION A where a.studentApplicant = " 
			+ s.getId();
			
			return (List<Long>) em.createQuery(query).getResultList();
		}
		catch (Exception e){
			
			return new ArrayList<Long>();
		}
	}
	
	public static List<Student> getAllStudents(EntityManager em) {
        
        try {
        String query = "select s from STUDENT s";
        return (List<Student>) em.createQuery(query).getResultList();
        }
        catch (Exception e){
        	return new ArrayList<Student>();
        } 
	}
}
