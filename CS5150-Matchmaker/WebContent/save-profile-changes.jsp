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
   String netId = (String) session.getAttribute("currentUser");
   Student s = StudentController.getStudentByNetID(em,netId);
   User u = UserController.findUser(em, netId);
   if (request.getParameter("email").length() > 0 && 
		   s.getEmail() != request.getParameter("email")){
	   StudentController.editEmail(em, s, request.getParameter("email"));
   }
   if (request.getParameter("year").length()>0 && 
		   s.getYear() != YearController.getYear(request.getParameter("year"))){
	   
	   StudentController.editYear(em, s, YearController.getYear(request.getParameter("year")));
   }
   if (request.getParameter("gpa").length()>0){
	   double gpa = Double.parseDouble(request.getParameter("gpa").replace(",","."));
	   if (gpa <= 4.3 && gpa != s.getGpa())
	   StudentController.editGPA(em, s, gpa);
   }
   if (request.getParameter("as_values_major").length()>0){
	   StudentController.update(em,s,request.getParameter("as_values_major"),ItemFactory.MAJOR); 
   }
   if (request.getParameter("as_values_minor").length()>0){
	   StudentController.update(em,s,request.getParameter("as_values_minor"),ItemFactory.MINOR); 
   }
   if (request.getParameter("as_values_skills").length()>0){
	   StudentController.update(em,s,request.getParameter("as_values_skills"),ItemFactory.SKILL); 
   }
   if (request.getParameter("as_values_research_interests").length()>0){
	   StudentController.update(em,s,request.getParameter("as_values_research_interests"),ItemFactory.INTEREST); 
   }
   if (request.getParameter("as_values_college").length()>0){
	   StudentController.update(em,s,request.getParameter("as_values_college"),ItemFactory.COLLEGE); 
   }

   response.setStatus(response.SC_MOVED_TEMPORARILY);
   response.setHeader("Location", "profile.jsp"); 
%>


<%
// int nCourses = Integer.parseInt(request.getParameter("nVals"));
// for(int i = 0; i<nCourses; i++){
// 	Course cobj = new Course();
// 	cobj.setCoursenum(request.getParameter("coursenum-"+i));
// 	cobj.setTitle(request.getParameter("title-"+i));
// 	cobj.setGrade(request.getParameter("grade-"+i));
// 	cobj.setSemester(request.getParameter("semester-"+i));
// 	courselist.add(cobj);
// }


%>

</body>
</html>
