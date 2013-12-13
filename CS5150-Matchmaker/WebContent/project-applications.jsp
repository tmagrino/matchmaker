<jsp:include page="header.jsp">
    <jsp:param name="stud_or_prof" value="researcher"/>
    <jsp:param name="top_selected" value="students"/>
</jsp:include>
<%@
	page import="java.util.*,model.Student, model.*, org.json.JSONObject,javax.persistence.*"
%>
<div class="content">
	<%
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
		 		EntityManager em = emf.createEntityManager();
		 		JSONObject jsonMajor = FieldValueController.getItemJson(em,FieldFactory.MAJOR);
		 	    JSONObject jsonSkills = FieldValueController.getItemJson(em,FieldFactory.SKILL);
		 	    JSONObject jsonInterest = FieldValueController.getItemJson(em,FieldFactory.INTEREST);
	%>
    
    <script type="text/javascript">
    	var majorData = <%= jsonMajor %>;
       	var skillsData = <%= jsonSkills %>;
       	var interestData = <%= jsonInterest %>;
    </script>
    <%
    	Researcher r = ResearcherController.getResearcherByNetID(em,(String) session.getAttribute("currentUser"));
        List<Project> projs = r.getProjects(); 
        boolean hasApplications = false;
        if (projs.size() == 0){
       %><td colspan="7"><h1>You have no projects! Would you like to 
       			<a href="proj-profile.jsp">add a new project?</a> </h1></td>
        
        <%}else{
			%>
		<a href="proj-profile.jsp">Add New Project</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="invite-students.jsp">Show all available Students</a>
		<%
		List<Application> apps;
		List<Application> declinedApps;
		Student s;
        for (Project p : projs) {
       		apps = p.getApplications();
       		//apps = 	ProjectController.removeDeclinedApplications(apps);
       		
       		if (apps.size() != 0){
       		hasApplications = true;
         %>
     
    <form name="filter-list" id="filter-list" class="clearfix">
    <h1><%=p.getName() %></h1>    
		<div class="search-container">
			<input class="search-text" type="text" placeholder="Search..."/>
			<input type="submit" value="Filter"/>
		</div>
	</form>
    <table class="project-list">
    

	
		<%	
			for (Application a : apps) {
				s = a.getStudentApplicant();
				String cssClasses = s.getName().replaceAll(" ", "_").toLowerCase() + " "
	                    + s.getString(s.getMajors()).replaceAll(" ", "_").toLowerCase() + " "
	                     + s.getString(s.getSkills()).replaceAll(" ", "_").toLowerCase() + " "
	                     + s.getString(s.getInterests()).replaceAll(" ", "_").toLowerCase() + " "
	                     + s.getNetID().replaceAll(" ", "_").toLowerCase();
		%>
		<tr class="<%= cssClasses %>">
			<td>
			<%	
				
				if (a.getStatus() == ApplicationStatus.Pending) {
			%>
				<% if(!ProjectController.meetsRequirements(p, s)) {
					%><p>Does not meet requirements</p><%
				}
			%>
				<a class="actionButton accept" href="accept-student.jsp?id=<%=a.getId()%>&studinvite=false">Accept</a>&nbsp;
				<a class="actionButton reject" href="reject-student.jsp?id=<%=a.getId()%>">Reject</a>
			<%
				}
				else {
			%>
			<%= 
				a.getStatus() 
			%>
			<%
				}
			%>
			</td>	
			<td><a href="profile-nonedit.jsp?studid=<%=s.getNetID() %>&appid=<%=a.getId() %>"> <%= s.getName() %>  </a></td> </td>
			<td><%=s.getGpa() %></td>
			<td><%=s.getString(s.getMajors()) %></td>
			<td><%=s.getYear() %></td>
			<td><%=s.getString(s.getSkills()) %></td>
			<td><%=s.getString(s.getInterests()) %></td>
		</tr>
		<%	} %>
		</tbody>

	</table>
	<br />
		<%  }
        }
        if (!hasApplications){
        	%>
        	<br />
        	<br />
        	<h1>No students have applied yet. Invite students <a href="invite-students.jsp">here</a>.</h1>
        	<%
        }
 
	}
        %>


	<br />
		<jsp:include page="pager.jsp"/>
	<br />

</div>

</body>
</html>
