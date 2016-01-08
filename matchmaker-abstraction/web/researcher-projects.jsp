<%--
	This is projects which are displayed for the researcher.
 --%>
<jsp:include page="header.jsp">
    <jsp:param name="stud_or_prof" value="researcher"/>
    <jsp:param name="top_selected" value="project"/>
</jsp:include>
<%@page import="java.util.*,model.Student, model.*, org.json.JSONObject,javax.persistence.*"%>
<div class="content">
	<%
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

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
       	if(projs.size() > 0){
	        List<Application> students;
	        Student s;
     %>
     	<a class="actionButton add-new" href="proj-profile.jsp">Add New Project</a><br>
     	<%
	        for (Project p : projs) {
	        	students = p.getApplications();
	        	boolean hasApplicants = false;
        %>
				<h1>
					<a href="edit-proj-profile.jsp?id=<%=p.getId()%>"><%=p.getName() %></a>
					<a class="actionButton delete" href="remove-project.jsp?id=<%=p.getId()%>"><img class="delete" src="images/Delete.png" alt="delete" border="0"
					alt="Delete application" /></a>
				</h1>
				<table class="project-list searchable researcher-projects" data-empty="No students have been accepted yet">
					<jsp:include page="stud-filters.jsp"/>
					<%for (Application a : students){
						if (a.getStatus() == ApplicationStatus.Accepted){
							hasApplicants = true;
							s = a.getStudentApplicant();
					%>
					<tr>
						<td class="no-title">
							<a class="actionButton remove" href="remove-student.jsp?id=<%=a.getId()%>">Remove</a>
						</td>
						<td><p><a href="profile-nonedit.jsp?studid=<%=s.getNetID()%>"><%=s.getName()%></a></p></td>
						<td><p><%=s.getGpa() %></p></td>
						<td><p><%=s.getString(s.getMajors()) %></p></td>
						<td><p><%=s.getYear() %></p></td>
						<td><p><%=s.getString(s.getSkills()) %></p></td>
						<td><p><%=s.getString(s.getInterests()) %></p></td>
					</tr>
					<%}  // End of for loop through student applications
				}  // End of for loop through projects projs
				%>
			</tbody>
		</table>
		
		<br>
		<%}%>
		<br>
	<% } else{
		response.setStatus(response.SC_MOVED_TEMPORARILY);
		response.setHeader("Location", "proj-profile.jsp");
		
	} 
	tx.commit(); %>
</div>	 <!--  Close up content div -->			
<jsp:include page="footer.jsp"></jsp:include>
