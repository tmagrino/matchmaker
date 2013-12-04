<jsp:include page="header.jsp">
	<jsp:param name="stud_or_prof" value="stud" />
	<jsp:param name="top_selected" value="project" />
</jsp:include>
<%@page import="java.util.*,model.Student, model.*, org.json.JSONObject,javax.persistence.*"%>
					<div class="content">
						<%
						EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
					 	EntityManager em = emf.createEntityManager();
					 	JSONObject jsonMajor = ListController.getItemJson(em,ItemFactory.MAJOR);
				        JSONObject jsonSkills = ListController.getItemJson(em,ItemFactory.SKILL);
				        JSONObject jsonInterest = ListController.getItemJson(em,ItemFactory.INTEREST);
				        Student s = StudentController.getStudentByNetID(em,(String)session.getAttribute("currentUser"));
				        List<Application> allApplications = s.getApplications();
				         %>
				         <div id="apply-form" class="hidden" title="Apply">
							  <form method="post" action="save-student-application.jsp">
								    <label for="cover-letter">Enter a short paragraph explaining why you would be a good fit for this project.</label>
								    <textarea name="cover-letter" id="cover-letter"></textarea>
								    <input type="submit" value="Apply">
							  </form>
						</div>
				        <script type="text/javascript">
				        	var majorData = <%= jsonMajor %>;
				        	var skillsData = <%= jsonSkills %>;
				        	var interestData = <%= jsonInterest %>;
				        </script>
						<h1>My Projects</h1>
							<table class="project-list">
								<jsp:include page="proj-filters.jsp"/>
								<% for(Application a : allApplications)
								{
								%>
								<tr>
									<td><a href="#">Apply</a></td>
									<td><%= a.getApplicationProject().getName() %></td>
									<td><%=a.getApplicationProject().getResearchersString() %></td>
									<td><a href="#"><%=a.getApplicationProject().getURL()%></a></td>
									<td><%= a.getApplicationProject().getDescription() %></td>
									<td><%= a.getApplicationProject().getAreaString() %></td>
									<td>TODO: backend</td>
								</tr>
							<%} %>
								</tbody>
							</table>
							
						<form name="filter-list" id="filter-list" class="clearfix">
							<h1>Search New Projects</h1>
							<div class="search-container">
								<input type="text" placeholder="Search"/>
								<input type="submit" value="Filter"/>
							</div>
						</form>
						<table class="project-list">
							<jsp:include page="proj-filters.jsp"/>
							<%List<Project> allProjects = ProjectController.getProjectList(em);
							for (Project p : allProjects){%>
							<% 
							String cssClasses = p.getName().replaceAll(" ", "_").toLowerCase() + " "
							+ p.getResearchersString().replaceAll(" ", "_").toLowerCase() + " "
						 	+ p.getDescription().replaceAll(" ", "_").toLowerCase() + " "
						  	+ p.getAreaString().replaceAll(" ", "_").toLowerCase();
							%>
							<tr class="<%= cssClasses %>">
								<td>
									<p>
										<a class="actionButton apply" name = "applyBut" 
										value = "<%= p.getId() %>"href="save-student-application.jsp">
										Apply</a>&nbsp;
										<a class="actionButton hide" href="#">Hide</a>
									</p>
								</td>
								<td><%=p.getName() %></td>
								<td><%=p.getResearchersString() %></td>
								<td><a href="<%=p.getURL() %>"><%=p.getURL() %></a></td>
								<td><%=p.getDescription() %></td>
								<td><%=p.getAreaString() %></td>
								<td>Java</td>
							</tr>
							<%} %>
 								
							</tbody>
						</table>
						<jsp:include page="pager.jsp"/>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
