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
				         %>
				         <div id="apply-form" class="hidden" title="Apply">
							  <form method="post" action="apply.jsp">
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
								<tr>
									<td>
										<p>Applied &nbsp; <a class="actionButton hide" href="#">Hide</a></p>
									</td>
									<td>Project Name</td>
									<td>Andrew Myers</td>
									<td><a href="#">Project Webpage</a></td>
									<td></td>
									<td>Programming Languages</td>
									<td>Java</td>
								</tr>
								<tr>
									<td>
										<p>Invited  &nbsp; <a class="actionButton hide" href="#">Hide</a></p>
									</td>
									<td>Project Name</td>
									<td>Tom Magrino</td>
									<td></td>
									<td title="This is a description of description of Project 2.  This is a description of Project 2.">
										Description of Proj 2...
									</td>
									<td>Systems</td>
									<td>Python</td>
								</tr>
								<tr>
									<td>
										<p>Applied &nbsp; <a class="actionButton hide" href="#">Hide</a></p>
									</td>
									<td>Project Name</td>
									<td>Tom Magrino</td>
									<td></td>
									<td title="This is a description of description of Project 3.  This is a description of Project 3.">
										Description of Proj 3...
									</td>
									<td>Systems</td>
									<td>Python</td>
								</tr>
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
							<tr>
								<td>
									<p>
										<a class="actionButton apply" href="#">Apply</a>&nbsp;
										<a class="actionButton hide" href="#">Hide</a>
									</p>
								</td>
								<td><%=p.getName() %></td>
								<td><%=p.getResearchersString() %></td>
								<td><a href="<%=p.getURL() %>"><%=p.getURL() %></a></td>
								<td></td>
								<td><%=p.getAreaString() %></td>
								<td>Java</td>
							</tr>
							<tr>
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