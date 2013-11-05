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
<% Student s = new Student(); 
	
	List<Skill> skillList = SkillController.parseSkill(request.getParameter("as_values_skills"));
	List<Interest> interestList = InterestController.parseInterest(request.getParameter("as_values_research"));
	List<Major> majorList = MajorController.parseMajor(request.getParameter("as_values_major"));
	List<Minor> minorList = MinorController.parseMinor(request.getParameter("as_values_minor"));
// StudentController.updateStudent(s, request.getParameter("name"), "jb20", 
// 		Double.parseDouble(request.getParameter("gpa")),
// 		request.getParameter("email"),YearController.getYear(request.getParameter("grad-year")),
// 		clist, majorlist,minorlist, slist,new ArrayList<model.Experience>(),
// 		new ArrayList<Interest>(),new ArrayList<Course>());
	for (Interest i : interestList){	%>
	<li><%=i.getDescription() %></li>
	<%} %>


</body>
</html>