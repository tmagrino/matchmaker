<%@page
        import="java.util.*,model.*, org.json.*,javax.persistence.*"%>
<%         EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
         EntityManager em = emf.createEntityManager();
         String role = request.getParameter("role");
         
         %>
         <%=role%>
         <%
         String netId = (String) session.getAttribute("currentUser");
         User u = UserController.createUser(em, "", "", netId);
         if(role.equalsIgnoreCase("student")){
        	 Student s = StudentController.createStudent(em, " ", netId, 0.0, " ", null, null, null, null, null, null, null, null, u);
        	 response.sendRedirect("profile.jsp");
         }
         else{
        	 Researcher r = ResearcherController.createResearcher(em, " ", netId, " ", null, " ", " ", u);
        	 response.sendRedirect("researcher-profile.jsp");
         }
         
%>        