<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
   //String projid = request.getParameter("applyBut");
   Project p = ProjectController.getProjectById(em, request.getParameter("applyBut"));
   /*Enumeration<String> e = request.getAttributeNames();
   while (e.hasMoreElements()) {
	   System.out.println(e.nextElement());
   }*/
   String text = request.getParameter("cover-letter");
   System.out.println("Creating application");
   Application a = ApplicationController.createApplication(em, s, p, text);
   System.out.println("Application created");
   ApplicationController.updateApplication(em, s, p, a);
   //System.out.println("Application updated");
   response.setStatus(response.SC_MOVED_TEMPORARILY);
   response.setHeader("Location", "student-projects.jsp"); 
%>
</body>
</html>
