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
	System.out.println("HIII");
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
   EntityManager em = emf.createEntityManager();
   
   String category = request.getParameter("category");
   String type = request.getParameter("type");
   String description = request.getParameter("desc");
   System.out.println(category);
   System.out.println(type);
   System.out.println(description);
   MultipleItem item = ListController.getItemByDescription(em, description, type);
   ListController.removeItem(em, item);
   
   response.setStatus(response.SC_MOVED_TEMPORARILY); 
   
   if (category == null) {
	   response.setHeader("Location", "latestAdditions.jsp"); 
   }
   else {
	   response.setHeader("Location", ("latestAdditions.jsp?category="+category)); 
   }
   

	   
 
%>

</body>
</html>
