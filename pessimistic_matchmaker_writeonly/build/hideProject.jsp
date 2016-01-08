<%--
	This page allows you to hide projects and show hidden projects
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*,model.*,javax.persistence.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
	<body>
		<% EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
		   EntityManager em = emf.createEntityManager();
		   Student s = StudentController.getStudentByNetID(em,(String) session.getAttribute("currentUser"));
		   Project p = ProjectController.getProjectById(em, request.getParameter("id"));
		   String showhide = request.getParameter("showhidden");
		   boolean showHidden = "yes".equals(showhide);
		   StudentController.addHiddenProject(em, s, p);
		   response.setStatus(response.SC_MOVED_TEMPORARILY);
		   if (!showHidden) {
			   response.setHeader("Location", "student-projects.jsp"); 
		   }
		   else {
			   response.setHeader("Location", "student-projects.jsp?showhidden=yes"); 
		   }
		%>
	</body>
</html>
