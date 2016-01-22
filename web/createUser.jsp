<%--
	This page is used to check whether user is having any current role.  
	It is also used to redirect to the profile pages on the basis of the student, researcher. 
--%>
<%@page import="java.util.*,model.*, org.json.*,javax.persistence.*"%>
<jsp:include page="header.jsp">
  <jsp:param name="stud_or_prof" value="header" />
  <jsp:param name="sidebar_type" value="stud-profile" />
  <jsp:param name="sidebar_selected" value="view" />
  <jsp:param name="top_selected" value="profile" />
</jsp:include>
<%       
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
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
<jsp:include page="footer.jsp"></jsp:include>
