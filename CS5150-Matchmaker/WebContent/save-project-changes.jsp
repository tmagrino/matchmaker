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
   Researcher r = ResearcherController.getResearcherByNetID(em,(String) session.getAttribute("currentUser"));
   String ids = request.getParameter("as_values_research_area");
   String [] idList = ids.split(",");
   List<Interest> area = new ArrayList<Interest>();
	for (String id : idList)
		if (id.length()>0){
		area.add((Interest)ListController.getItemById(em, Long.parseLong(id), ItemFactory.INTEREST));
		}
   ProjectController.createProject(em, request.getParameter("title"), request.getParameter("project_description"), 
			request.getParameter("project_url"), r,area);

   response.setStatus(response.SC_MOVED_TEMPORARILY);
   response.setHeader("Location", "researcher-profile.jsp"); 
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
