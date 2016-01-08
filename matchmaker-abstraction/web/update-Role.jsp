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

    EntityTransaction tx = em.getTransaction();
    tx.begin();
 	
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
		searchDisplay += "<br>New Student role has been added for the user.";
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
 			searchDisplay += "<br>New Administrator role has been added for the user.";
 		}
 	}

 	// Remove all roles
 	if (request.getParameter("studentRole") == null && 
 		request.getParameter("researcherRole") == null && 
 		request.getParameter("adminRole") == null) {
 		// if is yourself
 		if (session.getAttribute("adminUser") != null &&
 			user.getNetid().equalsIgnoreCase((String)session.getAttribute("adminUser"))) {
      em.lock(student, Locking.getWriteLockType());
 			StudentController.removeStudent(em, student);
      em.lock(researcher, Locking.getWriteLockType());
			ResearcherController.deleteResearcher(em, researcher);
			Student test = StudentController.getStudentByNetID(em, netID);
			Researcher test2 = ResearcherController.getResearcherByNetID(em, netID);
	  		if (test == null) {
	  			searchDisplay += "<br>Cannot Remove your own Administrator rights";
				searchDisplay += "<br>Your Student and Project Leader profile has been removed";
	  		}
	  		else {
	  			searchDisplay += "<br>Error removing your profiles";
	  		}
 		}
 		else {
 			searchDisplay += "<br>Attempting to delete all of "+name+"'s roles";
 			UserController.deleteUser(em, user);
 			User test = UserController.findUser(em, netID);
 			Student test1 = StudentController.getStudentByNetID(em, netID);
 			Researcher test2 = ResearcherController.getResearcherByNetID(em, netID);
  			if ((test == null) && (test1 == null) && (test2 == null)) {
  				searchDisplay += "<br>"+name+"'s User has been successfully deleted";
  			}
  			else {
  				if (test != null) {
  					searchDisplay += "<br>User account exists";
  				}
  				if (test1 != null) {
  					searchDisplay += "<br>Student profile exists";
  				}
  				if (test2 != null) {
  					searchDisplay += "<br>Project Leader profile exists";
  				}
  			}
 		}
  	}
    else { 
    	// Remove a student role
    	if (isStudent && request.getParameter("studentRole") == null) {
      em.lock(student, Locking.getWriteLockType());
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
          em.lock(researcher, Locking.getWriteLockType());

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
                em.lock(user, Locking.getWriteLockType());

 				UserController.setAdmin(em, user, false);
 				searchDisplay += "<br>Administrator rights are revoked for "+user.getName();
 			}
 		}
    }
 
 	response.sendRedirect("admin-searchUser.jsp?searchDisplay="+searchDisplay+"&netID="+request.getParameter("userNetID")+"&name="+request.getParameter("userName"));

  tx.commit();
%>
</body>
</html>
