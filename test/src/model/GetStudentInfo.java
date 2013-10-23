package model;


import java.util.ArrayList;
import java.util.List;


public class GetStudentInfo {
	 static List<Student> list = new ArrayList<Student>();

	 public static List<Student> get() {

	    
	    list.add(new Student(15,"Ze", 3.5));
	    return list;
	 }
}
	