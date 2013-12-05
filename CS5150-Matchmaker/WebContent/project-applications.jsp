<jsp:include page="header.jsp">
    <jsp:param name="stud_or_prof" value="researcher"/>
    <jsp:param name="top_selected" value="students"/>
</jsp:include>
<%@
	page import="java.util.*,model.Student, model.*, org.json.JSONObject,javax.persistence.*"
%>
<div class="content">
	<%	EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
 		EntityManager em = emf.createEntityManager();
 		JSONObject jsonMajor = ListController.getItemJson(em,ItemFactory.MAJOR);
 	    JSONObject jsonSkills = ListController.getItemJson(em,ItemFactory.SKILL);
 	    JSONObject jsonInterest = ListController.getItemJson(em,ItemFactory.INTEREST);
	%>
    
    <script type="text/javascript">
    	var majorData = <%= jsonMajor %>;
       	var skillsData = <%= jsonSkills %>;
       	var interestData = <%= jsonInterest %>;
    </script>
    <%
    	Researcher r = ResearcherController.getResearcherByNetID(em,(String) session.getAttribute("currentUser"));
        List<Project> projs = r.getProjects(); 
        if (projs.size() == 0){
       %><td colspan="7"><h1>You have no projects! Would you like to 
       			<a href="proj-profile.jsp">add a new project?</a> </h1></td>
        
        <%}else{
			%><a href="proj-profile.jsp">Add New Project</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="invite-students.jsp">Show all available Students</a>
		<%
		List<Application> apps;
		Student s;
        for (Project p : projs) {
       		 apps = p.getApplications();
       		
       		if (apps.size() != 0){
       			
         %>
     
    <form name="filter-list" id="filter-list" class="clearfix">
    <h1><%=p.getName() %></h1>    
		<div class="search-container">
			<input type="text" placeholder="Search..."/>
			<input type="submit" value="Filter"/>
		</div>
	</form>
    <table class="project-list">
    

	
		<%	
			for (Application a : apps) {
				s = a.getStudentApplicant();
		%>
		<tr>
			<td>
			<%
				if (a.getStatus() == ApplicationStatus.Pending) {
			%>
				<a class="actionButton accept" href="accept-student.jsp?id=<%=a.getId()%>">Accept</a>&nbsp;
				<a class="actionButton reject" href="#">Reject</a>
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
			<td><a href="profile-nonedit.jsp?studid=<%=s.getNetID() %>"> <%= s.getName() %>  </a></td> </td>
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
 
	}
        %>


	

</div>
<br />
<jsp:include page="pager.jsp"/>
<br />
</body>
</html>