<jsp:include page="header.jsp">
    <jsp:param name="stud_or_prof" value="stud"/>
    <jsp:param name="sidebar_type" value="stud-project"/>
    <jsp:param name="sidebar_selected" value="new"/>
    <jsp:param name="top_selected" value="project"/>
</jsp:include>
					<div class="content">
						<%@page import="java.util.*,model.Student, model.*, org.json.JSONObject,javax.persistence.*"%>
						<%
						EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
					 	EntityManager em = emf.createEntityManager();
					 	JSONObject jsonMajor = ListController.getItemJson(em,ItemFactory.MAJOR);
				        JSONObject jsonSkills = ListController.getItemJson(em,ItemFactory.SKILL);
				        JSONObject jsonInterest = ListController.getItemJson(em,ItemFactory.INTEREST);
				        Student s = StudentController.getStudentByNetID(em,request.getParameter("netId"));
				        List<Project> allProjects = ProjectController.getProjectList(em);
				         %>
				        <script type="text/javascript">
				        	var majorData = <%= jsonMajor %>;
				        	var skillsData = <%= jsonSkills %>;
				        	var interestData = <%= jsonInterest %>;
				        </script>
						<h1>All Projects</h1>
						<form name="filter-list" id="filter-list">
							<input type="submit" value="Filter"/>
							<table class="project-list">
							<jsp:include page="proj-filters.jsp"/>
							<% for( Project p : allProjects)
							{
							%>
								<tr>
									<td><a href="#">Apply</a></td>
									<td><%= p.getName() %></td>
									<td><%=p.getResearchersString() %></td>
									<td><a href="#"><%=p.getURL()%></a></td>
									<td><%= p.getDescription() %></td>
									<td><%= p.getAreaString() %></td>
									<td>TODO: backend</td>
								</tr>
							<%} %>
							</tbody>
							</table>
						</form>
						<jsp:include page="pager.jsp"/>
					</div>				
				</div>
			</div>
		</div>
	</div>
</body>
</html>