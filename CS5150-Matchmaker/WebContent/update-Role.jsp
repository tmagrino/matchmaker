<jsp:include page="header.jsp">
	<jsp:param name="stud_or_prof" value="admin" />
	<jsp:param name="sidebar_type" value="stud-profile" />
	<jsp:param name="sidebar_selected" value="view" />
	<jsp:param name="top_selected" value="profile" />
</jsp:include>
<%@
	page import="java.util.*,model.Student, model.*, org.json.JSONObject,javax.persistence.*"
%>
 	<%
 	
 	Boolean updatedRole = false;
 	EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
    EntityManager em = emf.createEntityManager();
 	
    String netID = (String) session.getAttribute("currentUser");
 	Student s = StudentController.getStudentByNetID(em, netID);
 	Researcher r = ResearcherController.getResearcherByNetID(em, netID);
 	User u = UserController.findUser(em, netID);
 	
 	Boolean isStudent = false;
 	if(s!= null){
 		isStudent = true;
 	}
 	
 	Boolean isResearcher = false;
 	if(r!= null){
 		isResearcher = true;
 	}
 	
 	Boolean isAdmin = false;
 	if(u!= null && u.isAdmin()){
 		isAdmin = true;
 	}
 	
 	if(!isStudent && request.getParameter("studentRole") != null){
 		updatedRole = true;
		Student student = StudentController.createStudent(em, u.getName(), u.getNetid(), 0.0, u.getEmail(),  null, null, null, null, null, null, null, null, u);
		%><h3>New Student role has been added.</h3><%
 	} %>
 	<%
 	if(!isResearcher && request.getParameter("researcherRole") != null){
 		updatedRole = true;
	 	List<Interest> area = null;
   	 	Researcher researcher = ResearcherController.createResearcher(em, u.getName(), u.getNetid(), u.getEmail(), null, "", area, u);
   	 %><h3>New Researcher role has been added.</h3><%
 		
 	}%>
 	<% 
 	if(!isAdmin && request.getParameter("adminRole") != null){
 		updatedRole = true;
 		UserController.setAdmin(em, u, true);
 		%><h3>New Administrator has been added.</h3><% 
 	}
 	
 	if(!updatedRole){%>
 		<h3>New new roles have been added.</h3><%}
 	%>
</body>
</html>