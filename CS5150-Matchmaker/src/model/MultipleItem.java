package model;

import java.util.Date;

public abstract class MultipleItem implements Comparable<MultipleItem> {
	public abstract	long getId();
	public abstract String getDescription();
	abstract void setDescription(String name);
	abstract void removeStudents();
	abstract void addStudent(Student s);
	abstract void removeStudent(Student s);
}
