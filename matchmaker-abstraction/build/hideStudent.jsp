<%--
	This page allows you to hide and unhide students from the search results.
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
	   
	   EntityTransaction tx = em.getTransaction();
	   tx.begin();
	   
	   Researcher r = ResearcherController.getResearcherByNetID(em,(String) session.getAttribute("currentUser"));
	   Student s = StudentController.getStudentByNetID(em, request.getParameter("id"));
	   String showhide = request.getParameter("showhidden");
	   boolean showHidden = "yes".equals(showhide);
	   System.out.println("Showhide: "+showhide);;
	   System.out.println(showHidden);

	   em.lock(r, Locking.getWriteLockType());
	   ResearcherController.addHiddenStudent(em, r, s);
	   response.setStatus(response.SC_MOVED_TEMPORARILY);
	   if (!showHidden) {
		   response.setHeader("Location", "invite-students.jsp"); 
	   }
	   else {
		   response.setHeader("Location", "invite-students.jsp?showhidden=yes"); 
	   }

	   tx.commit();
	%>
	
	</body>
</html>
