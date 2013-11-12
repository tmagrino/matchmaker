package model;

public class UserController {

	public User getUser(String netid) {
		//TODO:
		
		return null;
	}
	
	public void setStudent(User u, Student stud) {
		u.setStudent(stud);
		//TODO: update database
		
	}
	
	public void setResearcher(User u, Researcher r) {
		u.setResearcher(r);
		//TODO: update database
	}
	
}
