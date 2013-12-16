<%--
	This page is the applications page for all the projects
 --%>
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
        	response.setStatus(response.SC_MOVED_TEMPORARILY);
        	response.setHeader("Location", "proj-profile.jsp");
        }else{
			%>
		<a class="actionButton add-new" href="proj-profile.jsp">Add New Project</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<a class="filter-button" href="invite-students.jsp">Show all available students</a>
		<%
		List<Application> apps;
		List<Application> declinedApps;
		Student s;
        for (Project p : projs) { %>
    <h1><%=p.getName() %></h1> 
    <table class="project-list searchable" data-empty="No students have applied yet.  Invite students <a href=&quot;invite-students.jsp&quot>here</a>">
    	<jsp:include page="stud-filters.jsp"/>
		<%	
			apps = p.getApplications();
			for (Application a : apps) {
				s = a.getStudentApplicant();
		%>
		<tr>
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
	<%	} 
     }%>
</div>

</body>
</html>
