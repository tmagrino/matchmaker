<%--	
	This page is used to hide/unhide the student project pages
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
<% EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
   EntityManager em = emf.createEntityManager();

   EntityTransaction tx = em.getTransaction();
   tx.begin();

   Student s = StudentController.getStudentByNetID(em,(String) session.getAttribute("currentUser"));
   Project p = ProjectController.getProjectById(em, request.getParameter("id"));
   String showhide = request.getParameter("showhidden");
   boolean showHidden = "yes".equals(showhide);
   System.out.println("Showhide: " +showhide);

   Application a = ApplicationController.getApplication(em, s, p);
   em.lock(a, LockModeType.OPTIMISTIC);
   ApplicationController.deleteApplication(em, a);
   
   response.setStatus(response.SC_MOVED_TEMPORARILY);
   if (!showHidden) {
	   response.setHeader("Location", "student-projects.jsp"); 
   }
   else {
	   response.setHeader("Location", "student-projects.jsp?showhidden=yes"); 
   }

   tx.commit();
%>
</body>
</html>
