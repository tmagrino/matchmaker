<%--
	This page allows you to send invitations to students by the researcher.
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"
%>
<%@page import="java.util.*,model.*,javax.persistence.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
  <% EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
   EntityManager em = emf.createEntityManager();
   Student s = StudentController.getStudentByNetID(em, request.getParameter("stud-id"));
   Researcher r = ResearcherController.getResearcherByNetID(em,(String) session.getAttribute("currentUser"));
   Project p = ProjectController.getProjectById(em, request.getParameter("proj-id"));
   if (ApplicationController.getApplication(em, s, p) == null){
 
	   Application a = ApplicationController.createApplication(em, s, p, "Invited Student");
	   ApplicationController.inviteApplication(em, a);   
	   
	   Email.sendInvitationMessage(a.getStudentApplicant(),a);
   }
   response.setStatus(response.SC_MOVED_TEMPORARILY);
  
   response.setHeader("Location", "invite-students.jsp"); 

	   
 
%>

</body>
</html>
