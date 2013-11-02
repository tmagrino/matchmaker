package model;

import java.util.ArrayList;
import java.util.List;


public class GetStudentInfo {
	 static List<Student> list = new ArrayList<Student>();

	 public static List<Student> get() {

	    for (int i = 0; i < 5; i++) {
	    	list.add(new Student("HI"));
	    }
	    return list;
	 }
}
	