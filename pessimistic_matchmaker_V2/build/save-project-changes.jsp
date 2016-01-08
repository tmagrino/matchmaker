<%--
	This page allows you to save the profile changes for the project.
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
   Project p = ProjectController.getProjectById(em, request.getParameter("projid"));
   Researcher r = ResearcherController.getResearcherByNetID(em,(String) session.getAttribute("currentUser"));
   String ids = request.getParameter("as_values_research_area");
   String skIds = request.getParameter("as_values_required_skills");
   String [] idList = ids.split(",");
   String [] skillIdList = skIds.split(",");
   List<Interest> area = new ArrayList<Interest>();
   List<Skill> reqSkills = new ArrayList<Skill>();
	for (String id : idList)
		if (id.length()>0){
	try{
		area.add((Interest)FieldValueController.getFieldValueById(em, Long.parseLong(id), FieldFactory.INTEREST));
	}
	catch(NumberFormatException e){
		area.add((Interest)FieldValueController.createFieldValue(em, id, FieldFactory.INTEREST));
	}
		}
	for (String id : skillIdList){
		if (id.length()>0){
	try{
		reqSkills.add((Skill) FieldValueController.getFieldValueById(em, Long.parseLong(id), FieldFactory.SKILL));
	}
	catch(NumberFormatException e){
		reqSkills.add((Skill)FieldValueController.createFieldValue(em, id, FieldFactory.SKILL));
	}
		}
	}
   if (p == null){
   em.lock(r, LockModeType.PESSIMISTIC_WRITE);
   ProjectController.createProject(em, request.getParameter("title"), request.getParameter("project_description"), 
	request.getParameter("project_url"), r,area,reqSkills);
   }
   else{
      em.lock(r, LockModeType.PESSIMISTIC_WRITE);
      em.lock(p, LockModeType.PESSIMISTIC_WRITE);

	   ProjectController.updateProject(em, p, request.getParameter("title"), request.getParameter("project_description"), 
		request.getParameter("project_url"), r,area,reqSkills);
   } 
   response.setStatus(response.SC_MOVED_TEMPORARILY);
   response.setHeader("Location", "researcher-projects.jsp");

   tx.commit();
%>




</body>
</html>
