<%--
	This page allows you to update the roles of other users by Administrator.
 --%>
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
 	
 	EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
    EntityManager em = emf.createEntityManager();
 	
    String netID = (String) session.getAttribute("currentUser");
 	Student student = StudentController.getStudentByNetID(em, netID);
 	Researcher researcher = ResearcherController.getResearcherByNetID(em, netID);
 	User user = UserController.findUser(em, netID);
 	
 	boolean updatedRole = false;
 	boolean isStudent = (student != null);
 	boolean isResearcher = (researcher != null);
 	boolean isAdmin = (user != null && user.isAdmin());
 	
 	String searchDisplay = "";
	// Add roles
 	// Add a student role
 	if (!isStudent && request.getParameter("studentRole") != null) { 
 		updatedRole = true;
		Student stud = StudentController.createStudent(em, user.getName(), user.getNetid(), 0.0, user.getEmail(),  null, null, null, null, null, null, null, null, user);
		searchDisplay += "<br>Student profile has been added for "+user.getName();
 	}
 	
 	// Add a researcher role
  	if (!isResearcher && request.getParameter("researcherRole") != null){ 
  		updatedRole = true;
 	 	List<Interest> area = null;
    	 	Researcher researcherPL = ResearcherController.createResearcher(em, user.getName(), user.getNetid(), user.getEmail(), null, "", area, user);
    		searchDisplay += "<br>Project Leader profile has been added for "+user.getName();
  	}
 	
 	// Add an admin role
  	if(!isAdmin && request.getParameter("adminRole") != null) { 
 		updatedRole = true;
 		if(user!= null){
 			UserController.setAdmin(em, user, true);
 			searchDisplay += "<br>Administrator rights has been added for "+user.getName();
 		}
 	}
 	
 	// Remove roles
 	if (request.getParameter("studentRole") == null && 
 		request.getParameter("researcherRole") == null && 
 		request.getParameter("adminRole") == null) {
  		if (!isAdmin && (session.getAttribute("adminUser")!= null && 
  	 			!(user.getNetid().equalsIgnoreCase((String)session.getAttribute("adminUser"))))) {
  			UserController.deleteUser(em, user);
  		}
  		else { // Changing own user's role
  				StudentController.removeStudent(em, student);
  				ResearcherController.deleteResearcher(em, researcher);
  		}
  	}
	
    // Remove a student role
 	if (isStudent && request.getParameter("studentRole") == null) {
 		updatedRole = true;
 		StudentController.removeStudent(em, student);
 		searchDisplay += "<br>Student profile has been removed for "+user.getName();
 	}
 	
    // Remove a researcher role
 	if (isResearcher && request.getParameter("researcherRole") == null) {
 		updatedRole = true;
 		ResearcherController.deleteResearcher(em, researcher);
 		searchDisplay += "<br>Project Leader profile has been removed for "+user.getName();
 	}
 	
 	// Remove an admin role
 	if (isAdmin && request.getParameter("adminRole") == null) {
 		if (session.getAttribute("adminUser")!= null && 
 			user.getNetid().equalsIgnoreCase((String)session.getAttribute("adminUser"))) {
 			searchDisplay += "<br>Cannot Remove your own Administrator profile";
 		}
 		else {
 			UserController.setAdmin(em, user, false);
 			searchDisplay += "<br>Administrator rights are revoked for "+user.getName();
 		}
 	}
 	
 	if(!updatedRole){
 		searchDisplay+="";
 	}
 	response.sendRedirect("admin-searchUser.jsp?searchDisplay="+searchDisplay+"&netID="+request.getParameter("userNetID")+"&name="+request.getParameter("userName"));
%>
</body>
</html>