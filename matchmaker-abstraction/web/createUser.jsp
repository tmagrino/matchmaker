<%--
	This page is used to check whether user is having any current role.  
	It is also used to redirect to the profile pages on the basis of the student, researcher. 
--%>
<%@page import="java.util.*,model.*, org.json.*,javax.persistence.*" %>
<%       
	     EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
         EntityManager em = emf.createEntityManager();
         EntityTransaction tx = em.getTransaction();
         String role = request.getParameter("role");
         
         if (role == null || role == ""){
        	 response.sendRedirect("select-role.jsp");
         }
         else{
         	
	   	 	tx.begin();
         	String netId = (String) session.getAttribute("currentUser");
         	User u = UserController.createUser(em,"New User", "", netId);
         	em.lock(u, Locking.getWriteLockType());
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
         tx.commit();
%>        
