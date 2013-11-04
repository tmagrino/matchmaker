<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*,model.Student,model.*, model.StudentController"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% Student s = new Student(); 
StudentController controller = new StudentController();
List<College> clist= new ArrayList<College>();
clist.add(new College(request.getParameter("school")));
List<Major> majorlist= new ArrayList<Major>();
majorlist.add(new model.Major(request.getParameter("major")));
List<Minor> minorlist= new ArrayList<Minor>();
minorlist.add(new model.Minor(request.getParameter("minor")));
List<Skill> slist= new ArrayList<Skill>();
slist.add(new Skill(request.getParameter("skill")));
controller.updateStudent(s, request.getParameter("name"), "jb20", 
		Double.parseDouble(request.getParameter("gpa")),
		request.getParameter("email"),model.Year.Sophomore,
		clist, majorlist,minorlist, slist,new ArrayList<model.Experience>(),
		new ArrayList<Interest>(),new ArrayList<Course>());
			%>

	School : <%= s.getColleges().get(0) %>
	Email : <%= request.getParameter("email")%>
	<% String redirectURL =  "profile.jsp";
		response.sendRedirect(redirectURL);
	%>
</body>
</html>