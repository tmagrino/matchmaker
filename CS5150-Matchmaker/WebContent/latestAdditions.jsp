<jsp:include page="header.jsp">
	<jsp:param name="stud_or_prof" value="stud" />
	<jsp:param name="top_selected" value="project" />
</jsp:include>
<%@page
	import="java.util.*,model.Student, model.*, org.json.JSONObject,javax.persistence.*"%>

<div class="content">
<%
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
	EntityManager em = emf.createEntityManager();
	JSONObject jsonMajor = ListController.getItemJson(em,ItemFactory.MAJOR);
	JSONObject jsonSkills = ListController.getItemJson(em,ItemFactory.SKILL);
	JSONObject jsonInterest = ListController.getItemJson(em,ItemFactory.INTEREST);
	Student s = StudentController.getStudentByNetID(em,(String)session.getAttribute("currentUser"));
	List<Application> allApplications = s.getApplications();
	List<Project> hiddenProjects = s.getSettings().getHiddenProjects();
	String showhide = request.getParameter("showhidden");
	boolean showHidden = "yes".equals(showhide);
%>

<script type="text/javascript">
var majorData = <%= jsonMajor %>;
var skillsData = <%= jsonSkills %>;
var interestData = <%= jsonInterest %>;
</script>

<h1>Latest Additions</h1><br />
<table class="project-list">
	<%
		if (allApplications.size() == 0) {
		%>
			<tr class="no-results"><td colspan="5"><i>No Latest Additions found</i></td></tr>	
		<%
		}
		for(Application a : allApplications) {
            Project p = a.getApplicationProject();
    %>
	<tr>
		<td><%= 
			a.getStatus()%> 
			<a class"actionButton delete" href="delete-student-application.jsp?id=<%=a.getId()%>
				<%
					if (showHidden) {
						%>&amp;showhidden=yes<%
					}
				%>
			">
				<img class="delete" src="images/Delete.png" alt="delete" border="0"
				alt="Delete application" />
			</a>
		</td>
		<td><%= p.getName() %></td>
		<td><%=p.getResearchersString() %></td>
		<td><a href="//<%=p.getURL()%>"><%=ItemFactory.shortenString(p.getURL())%></a></td>
		<td><%= ItemFactory.shortenString(p.getDescription())%></td>
		<%
        }
         %>
	</tr>
</table>
<br />

<table class="project-list">
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
          	String cssClasses = p.getName().replaceAll(" ", "_").toLowerCase() + " "
                    + p.getResearchersString().replaceAll(" ", "_").toLowerCase() + " "
                     + p.getDescription().replaceAll(" ", "_").toLowerCase() + " "
                     + p.getAreaString().replaceAll(" ", "_").toLowerCase() + " "
                     + p.getSkillString().replaceAll(" ", "_").toLowerCase();
    %>
	<tr class="hidden">
                <td colspan="7">
                    <div id="apply-form-<%=p.getId()%>" % class="apply-form" class="hidden" title="Apply">
                   <form method="post" action="save-student-application.jsp?id=<%=p.getId()%>">
                           <label for="cover-letter">Enter a short paragraph explaining why you would be a good fit for this project.</label>
                           <textarea name="cover-letter" id="cover-letter"></textarea>
                           <input type="submit" value="Apply">
                   </form>
              </div>
            </td>
          </tr>
	<tr class="<%= cssClasses %>">
		<td>
			<p>
				<a class="actionButton apply"
					href="save-student-application.jsp?id=<%=p.getId()%>">Apply</a>&nbsp;
				<%
                      	if (hid && showHidden) {
                      		%><a class="actionButton unhide"
					href="unhideProject.jsp?id=<%=p.getId()%>">Unhide</a>
				<%
                      	}
                      	else {
                      		%><a class="actionButton hide"
					href="hideProject.jsp?id=<%=p.getId()%>
                      		<% 
                      		if (showHidden) {
                      			%>&amp;showhidden=yes<%
                      		}
                      		%>
                      		">Hide</a>
				<%
                      	}
                      	%>
			</p>
		</td>
		<td><%=p.getName() %></td>
		<td><%=p.getResearchersString() %></td>
		<td><a href="//<%=p.getURL() %>"><%=ItemFactory.shortenString(p.getURL())%></a></td>
		<td><%=ItemFactory.shortenString(p.getDescription())%></td>
		<td><%= ItemFactory.shortenString(p.getAreaString())%></td>
		<td><%= ItemFactory.shortenString(p.getSkillString())%></td>
	</tr>

	<%
		} 
        if (!atLeastOne) {
    %>
	<td colspan="7"><i>No latest additions. </i> 
	</td>
	<%
        }
    %>
</table>
<jsp:include page="pager.jsp" />
</div>
<div></div>
<div></div>
<div></div>
<div></div>
<body></body>
<html></html>
