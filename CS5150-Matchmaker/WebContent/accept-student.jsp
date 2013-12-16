<%-- This page is used for Accepting Students to the projects 
	 It is used to get the application and send email to the applicant
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
   Application a = ApplicationController.getApplicationById(em, request.getParameter("id"));
   
   ApplicationController.approveApplication(em, a);
   Email.sendAcceptingMessage(a.getStudentApplicant(),a);
   response.setStatus(response.SC_MOVED_TEMPORARILY);
   response.setHeader("Location", "project-applications.jsp"); 
  
	   
 
%>

</body>
</html>
