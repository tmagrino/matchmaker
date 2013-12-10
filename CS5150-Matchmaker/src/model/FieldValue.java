package model;

public abstract class FieldValue implements Comparable<FieldValue>{
	public abstract	long getId();
	public abstract String getDescription();
	abstract void setDescription(String name);
	abstract void addStudent(Student s);
	abstract void addResearcher(Researcher r);
	abstract void addProject(Project p);
	abstract void removeStudent(Student s);
	abstract void removeResearcher(Researcher r);
	abstract void removeProject(Project p);
	abstract void removeElements();
}
