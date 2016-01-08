<%--
	This page allows you to remove the item from the vocabulary.
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

   EntityTransaction tx = em.getTransaction();
   tx.begin();
   
   String category = request.getParameter("category");
   String type = request.getParameter("type");
   String description = request.getParameter("desc");
   FieldValue item = FieldValueController.getItemByDescription(em, description, type);
   List<Student> students = FieldValueController.getStudents(item);
   List<Researcher> researchers = FieldValueController.getResearchers(item);
   if (students != null){
	   for (Student s : students){
		   Email.sendDeleteItemMessage(s,description,type);
	   }
   }
   if (researchers != null){
	   for (Researcher r : researchers){
      //todo - no write or read lock, mostly correct
		   Email.sendDeleteItemMessage(r,description,type);
	   }
   }
   FieldValueController.removeFieldValue(em, item);
   
   response.setStatus(response.SC_MOVED_TEMPORARILY); 
 
   if (category == null) {
	   response.setHeader("Location", "latestAdditions.jsp"); 
   }
   else {
	   response.setHeader("Location", ("latestAdditions.jsp?category="+category)); 
   }

   tx.commit();
%>

</body>
</html>
