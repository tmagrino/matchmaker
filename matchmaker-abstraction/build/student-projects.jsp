<jsp:include page="header.jsp">
	<jsp:param name="stud_or_prof" value="stud" />
	<jsp:param name="top_selected" value="project" />
</jsp:include>
<%@page
	import="java.util.*,model.Student, model.*, org.json.JSONObject,javax.persistence.*"%>
<div class="apply-form hidden" title="Apply">
	<form method="post" action="save-student-application.jsp">
     	<label for="cover-letter">Enter a short paragraph explaining why you would be a good fit for this project.</label>
         <textarea name="cover-letter" id="cover-letter"></textarea>
         <input type="hidden" name="id">
         <input type="hidden" name="app-id">
         <input type="submit" value="Apply">
     </form>
 </div>
<div class="content">
<%
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
	EntityManager em = emf.createEntityManager();

	EntityTransaction tx = em.getTransaction();
		tx.begin();

	JSONObject jsonMajor = FieldValueController.getItemJson(em,FieldFactory.MAJOR);
	JSONObject jsonSkills = FieldValueController.getItemJson(em,FieldFactory.SKILL);
	JSONObject jsonInterest = FieldValueController.getItemJson(em,FieldFactory.INTEREST);
	Student s = StudentController.getStudentByNetID(em,(String)session.getAttribute("currentUser"));
	List<Application> allApplications = s.getApplications();
	List<Project> hiddenProjects = s.getSettings().getHiddenProjects();
	String showhide = request.getParameter("showhidden");
	boolean showHidden = "yes".equals(showhide);
%>

<script type="text/javascript">
var majorData = <%=jsonMajor%>;
var skillsData = <%=jsonSkills%>;
var interestData = <%=jsonInterest%>;
</script>

<h1>My Applications</h1><br />
<table class="project-list searchable" data-empty="No applications found">
	<jsp:include page="proj-filters.jsp"/>
	<%
					for(Application a : allApplications) {
			            Project p = a.getApplicationProject();
			%>
	<tr>
		<td class="no-title">
			<%=a.getStatus()%>&nbsp;
			<%
				if (a.getStatus() == ApplicationStatus.Invited){
			%>
				<a id="a<%=a.getId()%>"class="actionButton apply" href = "#">Apply</a>
			<%
				}
			%>&nbsp;
			<a class="actionButton delete" href="delete-student-application.jsp?id=<%=a.getId()%>
				<%if (showHidden) {%>&amp;showhidden=yes<%}%>
			">
				<img class="delete" src="images/Delete.png" alt="delete" border="0"
				alt="Delete application" />
			</a>
		</td>
		<td><p><a href = "proj-profile-nonedit.jsp?pid=<%=p.getId()%>"><%=p.getName()%></a></p></td>
		<td>
		<%
			for (Researcher r : p.getResearchers()) {
		%>
			<p><a href = "researcher-profile-nonedit.jsp?id=<%=r.getNetID()%>"><%=r.getName()%></a></p>
		<%
			}
		%>
		</td>
		<td><p><a href="//<%=p.getURL()%>"><%=p.getURL()%></a></p></td>
		<td><p><%=p.getDescription()%></p></td>
		<td><p><%=p.getAreaString()%></p></td>
		<td><p><%=p.getSkillString()%></p></td>
		<%
			}
		%>
	</tr>
</table>

<h1>Search New Projects</h1>

<%
	if (showHidden) {
%>
<br />
<p>
	<span class="hidden-message">Displaying hidden projects</span> <a class="filter-button hide" href="student-projects.jsp?showhidden=no"> Hide Projects</a></font>
</p>
<%
	}
	else{
%>
	<br />
	<p>
		<a class="filter-button hide" href="student-projects.jsp?showhidden=yes"> Show hidden projects</a>
	</p>
	<%
		}
	%>

<table class="project-list searchable" data-empty="No available projects">
	<jsp:include page="proj-filters.jsp" />
	<%
		List<Project> allProjects = ProjectController.getProjectList(em);
	        List<Long> studProjs = StudentController.getStudentProjects(em,s);
	        boolean atLeastOne = false;
	        for (Project p : allProjects) {
	        	boolean applied = false;
	          	boolean hid = false;
	          	for (Application a : allApplications) {
	          		if (a.getApplicationProject() == p) {
	          			applied = true;
	          			break;
	          		}
	          	}
	          	if (applied) {
	          		continue;
	          	}
	          	if (hiddenProjects.contains(p)) {
	          		hid = true;
	          	}
	          	if (!showHidden && hid) {
	          		continue;
	          	}
	          	atLeastOne = true;
	%>
	<tr>
		<td class = "buttonTD no-title">
				<% 
					if (!ProjectController.meetsRequirements(p, s)) {
						%><p class="req-msg">Requirements not met</p><%
						
					}
				%>
						<a id="<%=p.getId()%>" class="actionButton apply"
								href="#">Apply</a>&nbsp;
						<%
					if (hid && showHidden) {
				%><a class="actionButton unhide"
					href="unhideProject.jsp?id=<%=p.getId()%>">Unhide</a>
				<%
					}
				    else {
				%><a class="actionButton hide"
					href="hideProject.jsp?id=<%=p.getId()%>
                      		<%if (showHidden) {%>&amp;showhidden=yes<%}%>
                      		">Hide</a>
				<%
					}
				%>
		</td>
		<td><a href = "proj-profile-nonedit.jsp?pid=<%=p.getId()%>"><%=p.getName()%></a></td>
		<td>
		<%
			for (Researcher r : p.getResearchers()) {
		%>
			<a href = "researcher-profile-nonedit.jsp?id=<%=r.getNetID()%>"><%=r.getName()%></a>
		<%
			}
		%>
		</td>
		<td><p><a href="//<%=p.getURL()%>"><%=p.getURL()%></a></p></td>
		<td><p><%=p.getDescription()%></p></td>
		<td><p><%=p.getAreaString()%></p></td>
		<td><p><%=p.getSkillString()%></p></td>
	</tr>

	<% }
	tx.commit(); %>
	    </tbody>
</table>
</div>
</div>
</div>
</div>
</div>
</body>
</html>
