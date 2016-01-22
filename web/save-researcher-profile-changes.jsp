<%--
  This page allows you save profile changes by the researcher
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
  <%
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
    EntityManager em = emf.createEntityManager();
    String netId = (String) session.getAttribute("currentUser");
    Researcher r = ResearcherController.getResearcherByNetID(em, netId);
    User u = UserController.findUser(em, netId);
    if (request.getParameter("NewUser")!=null && 
        request.getParameter("NewUser").length() > 0 && 
        r.getName() != request.getParameter("NewUser")){
      ResearcherController.editName(em, r, request.getParameter("NewUser"));
    }
    if (request.getParameter("email").length() > 0 && 
        r.getEmail() != request.getParameter("email")){
      ResearcherController.editEmail(em, r, request.getParameter("email"));
    }
    if (request.getParameter("url").length()>0 && 
        r.getWebpage() != request.getParameter("url")){
      ResearcherController.editWebpage(em, r, request.getParameter("url"));
    }

    System.out.println("Research areas");
    System.out.println(request.getParameter("as_values_research_area"));
    System.out.println("Departments");
    System.out.println(request.getParameter("as_values_department"));
    if (request.getParameter("as_values_research_area").length()>0){
      ResearcherController.editArea(em, r, request.getParameter("as_values_research_area")  ); 
    }
    if (request.getParameter("as_values_department").length()>0){
      ResearcherController.editDepartments(em, r,request.getParameter("as_values_department")); 
    }

    response.setStatus(response.SC_MOVED_TEMPORARILY);
    response.setHeader("Location", "researcher-profile.jsp"); 
  %>


  <%
// int nCourses = Integer.parseInt(request.getParameter("nVals"));
// for(int i = 0; i<nCourses; i++){
//   Course cobj = new Course();
//   cobj.setCoursenum(request.getParameter("coursenum-"+i));
//   cobj.setTitle(request.getParameter("title-"+i));
//   cobj.setGrade(request.getParameter("grade-"+i));
//   cobj.setSemester(request.getParameter("semester-"+i));
//   courselist.add(cobj);
// }
%>

</body>
</html>
