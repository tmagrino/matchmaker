<!-- Code for adding new users -->
<%@page
        import="java.util.*,model.*, org.json.*,javax.persistence.*"%>
<%         EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
         EntityManager em = emf.createEntityManager();
         String role = request.getParameter("role");
         
         if (role == null || role == ""){
        	 response.sendRedirect("select-role.jsp");
         }
         else{
         
         String netId = (String) session.getAttribute("currentUser");
         User u = UserController.createUser(em,"New User", "", netId);
         // Changes for new user
         if(role.equalsIgnoreCase("student")){
        	 Student s = StudentController.createStudent(em, "New User", netId, 0.0, "", null, null, null, null, null, null, null, null, u);
        	 response.sendRedirect("profile.jsp");
        	
         }
         else{
        	 List<Interest> area = null;
        	 Researcher r = ResearcherController.createResearcher(em, "New User", netId, "", null, "", area, u);
        	 response.sendRedirect("researcher-profile.jsp");
         }
         }
%>        