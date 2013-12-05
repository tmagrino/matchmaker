<jsp:include page="header.jsp">
    <jsp:param name="stud_or_prof" value="researcher"/>
    <jsp:param name="top_selected" value="project"/>
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
				         
				        <script type="text/javascript">
				        	var majorData = <%= jsonMajor %>;
				        	var skillsData = <%= jsonSkills %>;
				        	var interestData = <%= jsonInterest %>;
				        </script>
				        <%Researcher r = ResearcherController.getResearcherByNetID(em,(String) session.getAttribute("currentUser"));
				        List<Project> projs = r.getProjects(); 
				        List<Application> students;
				        Student s;
				        System.out.println(projs);
				        for (Project p : projs) {
				        	students = p.getApplications();
				        %>
						<form name="filter-list" id="filter-list" class="clearfix">
							<h1><%=p.getName() %></h1>
							<div class="search-container">
								<input type="text" placeholder="Search..."/>
								<input type="submit" value="Filter"/>
							</div>
						</form>
						<table class="project-list">
							<thead>
								<tr>
									<th class="empty"></th>
									<th>Name</th>
									<th>GPA</th>
									<th>Major</th>
									<th>Year</th>
									<th>Skills</th>
									<th>Research Interests</th>
								</tr>
							</thead>
							<tbody>
								<%for (Application a : students){
									s = a.getStudentApplicant();%>
								<tr>
									<td>
										<a class="actionButton remove" href="#">Remove</a>
									</td>
									<td><%=s.getName() %></td>
									<td><%=s.getGpa() %></a></td>
									<td><%=s.getString(s.getMajors()) %></td>
									<td><%=s.getYear() %></td>
									<td><%=s.getString(s.getSkills()) %></td>
									<td><%=s.getString(s.getInterests()) %></td>
								</tr>
								<%} %>

							</tbody>
						</table>
						
						<br>
						<%} %>
						<jsp:include page="pager.jsp"/>
						<br>
						<a href="proj-profile.jsp">Add New Project</a>
					</div>				
				</div>
			</div>
		</div>
	</div>
</body>
</html>
