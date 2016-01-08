<%--
	This page allows you to save the profile changes of the Student
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*,model.*,javax.persistence.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
   EntityManager em = emf.createEntityManager();
   String netId = (String) session.getAttribute("currentUser");
   Student s = StudentController.getStudentByNetID(em,netId);
   User u = UserController.findUser(em, netId);
   if (request.getParameter("NewUser")!=null && 
		   request.getParameter("NewUser").length() > 0 && 
		   s.getName() != request.getParameter("NewUser")){
	   StudentController.editName(em, s, request.getParameter("NewUser"));
   }
   if (request.getParameter("email").length() > 0 && 
		   s.getEmail() != request.getParameter("email")){
	   StudentController.editEmail(em, s, request.getParameter("email"));
   }
   System.out.println(s.getYear());
   System.out.println(YearController.getYear(request.getParameter("year")));
   if (request.getParameter("year").length()>0 && 
		   s.getYear() != YearController.getYear(request.getParameter("year"))){
	   
	   StudentController.editYear(em, s, YearController.getYear(request.getParameter("year")));
   }
   if (request.getParameter("gpa").length()>0) {
	   try {
	   	double gpa = Double.parseDouble(request.getParameter("gpa").replace(",","."));
	   	if (gpa <= 4.3 && gpa != s.getGpa())
	 	   StudentController.editGPA(em, s, gpa);
	   }
	   catch (Exception e) {
		   
	   }
   }
   System.out.println("Updating student profile");			
   System.out.println(request.getParameter("as_values_major"));
   if (request.getParameter("as_values_major").length()>0){
	   StudentController.update(em,s,request.getParameter("as_values_major"),FieldFactory.MAJOR); 
   }
   if (request.getParameter("as_values_minor").length()>0){
	   StudentController.update(em,s,request.getParameter("as_values_minor"),FieldFactory.MINOR); 
   }
   if (request.getParameter("as_values_skills").length()>0){
	   StudentController.update(em,s,request.getParameter("as_values_skills"),FieldFactory.SKILL); 
   }
   if (request.getParameter("as_values_research_interests").length()>0){
	   StudentController.update(em,s,request.getParameter("as_values_research_interests"),FieldFactory.INTEREST); 
   }
   if (request.getParameter("as_values_college").length()>0){
	   StudentController.update(em,s,request.getParameter("as_values_college"),FieldFactory.COLLEGE); 
   }

   response.setStatus(response.SC_MOVED_TEMPORARILY);
   response.setHeader("Location", "profile.jsp");
%>




</body>
</html>
