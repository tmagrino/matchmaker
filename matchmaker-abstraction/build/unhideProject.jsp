<%--
	This page allows you to unhide the projects that are hidden
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

   em.lock(s, Locking.getWriteLockType());
   em.lock(p, Locking.getWriteLockType());

   StudentController.removeHiddenProject(em, s, p);
   response.setStatus(response.SC_MOVED_TEMPORARILY);
   response.setHeader("Location", "student-projects.jsp?showhidden=yes"); 

   tx.commit();
%>

</body>
</html>
