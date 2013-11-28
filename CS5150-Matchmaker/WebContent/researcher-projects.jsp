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
						<form name="filter-list" id="filter-list" class="clearfix">
							<h1>My Project A</h1>
							<div class="search-container">
								<input type="text" placeholder="Search"/>
								<input type="submit" value="Filter"/>
							</div>
						</form>
						
						<form name="filter-list" id="filter-list" class="clearfix">
							<h1>My Project A</h1>
							<div class="search-container">
								<input type="text" placeholder="Search"/>
								<input type="submit" value="Filter"/>
							</div>
						</form>
						<table class="student-list">
							<thead>
								<tr>
									<th class="empty"></th>
									<th>Project Name</th>
									<th>Project URL</th>
									<th>Description</th>
									<th>Research Area</th>
									<th>Required Skills</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<a class="actionButton remove" href="#">Remove</a>
									</td>
									<td>Project Name</td>
									<td><a href="#">Link to Project Webpage</a></td>
									<td></td>
									<td>Programming Languages</td>
									<td>Java</td>
								</tr>
								<tr>
									<td>
										<a class="actionButton remove" href="#">Remove</a>
									</td>
									<td>Project Name</td>
									<td></td>
									<td title="This is a description of Project 2.  This is a description of Project 2.">
										This is a description of Project 2.  This is a...
									</td>
									<td>Systems</td>
									<td>Python</td>
								</tr>
							</tbody>
						</table>
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