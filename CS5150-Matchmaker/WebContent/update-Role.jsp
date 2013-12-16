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
 	String name = user.getName();
 	
 	boolean isStudent = (student != null);
 	boolean isResearcher = (researcher != null);
 	boolean isAdmin = (user != null && user.isAdmin());
 	
 	String searchDisplay = "";
	// Add roles
 	// Add a student role
 	if (!isStudent && request.getParameter("studentRole") != null) { 
		Student stud = StudentController.createStudent(em, user.getName(), user.getNetid(), 0.0, user.getEmail(),  null, null, null, null, null, null, null, null, user);
		searchDisplay += "<br>Student profile has been added for "+user.getName();
 	}
 	
 	// Add a researcher role
  	if (!isResearcher && request.getParameter("researcherRole") != null){ 
 	 	List<Interest> area = null;
    	 	Researcher researcherPL = ResearcherController.createResearcher(em, user.getName(), user.getNetid(), user.getEmail(), null, "", area, user);
    		searchDisplay += "<br>Project Leader profile has been added for "+user.getName();
  	}
 	
 	// Add an admin role
  	if(!isAdmin && request.getParameter("adminRole") != null) { 
 		if(user!= null){
 			UserController.setAdmin(em, user, true);
 			searchDisplay += "<br>Administrator rights has been added for "+user.getName();
 		}
 	}

 	// Remove all roles
 	if (request.getParameter("studentRole") == null && 
 		request.getParameter("researcherRole") == null && 
 		request.getParameter("adminRole") == null) {
  		if (!isAdmin && (session.getAttribute("adminUser") != null && 
  	 			!(user.getNetid().equalsIgnoreCase((String)session.getAttribute("adminUser"))))) {
  			UserController.deleteUser(em, user);
  			User test = UserController.findUser(em, netID);
  			if (test == null) {
  				searchDisplay += "<br>"+name+"'s User has been deleted";
  			}
  			else {
  				searchDisplay += "<br>Error removing all "+name+"'s profiles";
  			}
  		}
  		else { // Changing own user's role
  				StudentController.removeStudent(em, student);
  				ResearcherController.deleteResearcher(em, researcher);
  				User test = UserController.findUser(em, netID);
  	  			if (test == null) {
  	  			searchDisplay += "<br>Cannot Remove your own Administrator rights";
  				searchDisplay += "<br>Your Student and Project Leader profile has been removed";
  	  			}
  	  			else {
  	  				searchDisplay += "<br>Error removing your profiles";
  	  			}
  				
  		}
  	}
    else { 
    	// Remove a student role
    	if (isStudent && request.getParameter("studentRole") == null) {
 			StudentController.removeStudent(em, student);
 			Student test = StudentController.getStudentByNetID(em, netID);
 			if (test == null) {
 				searchDisplay += "<br>Student profile has been removed for "+name;
 			}
 			else {
 				searchDisplay += "<br>Error removing "+name+"'s Student profile";	
 			}
 		}
 	
 	   // Remove a researcher role
		if (isResearcher && request.getParameter("researcherRole") == null) {
 			ResearcherController.deleteResearcher(em, researcher);
 			Researcher test = ResearcherController.getResearcherByNetID(em, netID);
 			if (test == null) {
 				searchDisplay += "<br>Project Leader profile has been removed for "+name;
 			}
 			else {
 				searchDisplay += "<br>Error removing "+name+"'s Project Leader profile";	
 			}
 		}
 	
 		// Remove an admin role
 		if (isAdmin && request.getParameter("adminRole") == null) {
 			if (session.getAttribute("adminUser")!= null && 
 				user.getNetid().equalsIgnoreCase((String)session.getAttribute("adminUser"))) {
 				searchDisplay += "<br>Cannot Remove your own Administrator rights";
 			}
 			else {
 				UserController.setAdmin(em, user, false);
 				searchDisplay += "<br>Administrator rights are revoked for "+user.getName();
 			}
 		}
    }
 	
 	response.sendRedirect("admin-searchUser.jsp?searchDisplay="+searchDisplay+"&netID="+request.getParameter("userNetID")+"&name="+request.getParameter("userName"));
%>
</body>
</html>