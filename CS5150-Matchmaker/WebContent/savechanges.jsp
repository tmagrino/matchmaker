<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*,model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% StudentController.updateStudent(new Student(),request.getParameter("name"), "jb20",
		Double.parseDouble(request.getParameter("gpa")),request.getParameter("email"),
		YearController.getYear(request.getParameter("grad-year")), 
		CollegeController.parseCollege(request.getParameter("as_values_college")),
		MajorController.parseMajor(request.getParameter("as_values_major")),
		MinorController.parseMinor(request.getParameter("as_values_minor")),
		SkillController.parseSkill(request.getParameter("as_values_skills")),
		null, InterestController.parseInterest(request.getParameter("as_values_research")),
		null);
%>


<%
// int nCourses = Integer.parseInt(request.getParameter("nVals"));
// for(int i = 0; i<nCourses; i++){
// 	Course cobj = new Course();
// 	cobj.setCoursenum(request.getParameter("coursenum-"+i));
// 	cobj.setTitle(request.getParameter("title-"+i));
// 	cobj.setGrade(request.getParameter("grade-"+i));
// 	cobj.setSemester(request.getParameter("semester-"+i));
// 	courselist.add(cobj);
// }


%>

</body>
</html>
