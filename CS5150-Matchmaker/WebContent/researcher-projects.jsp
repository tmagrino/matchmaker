<jsp:include page="header.jsp">
    <jsp:param name="stud_or_prof" value="researcher"/>
    <jsp:param name="top_selected" value="project"/>
</jsp:include>
<%@page import="java.util.*,model.Student, model.*, org.json.JSONObject,javax.persistence.*"%>
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
				        <%Researcher r = ResearcherController.getResearcherByNetID(em,(String) session.getAttribute("currentUser"));
				        List<Project> projs = r.getProjects();
				        

				        List<Application> students;
				        Student s;
				        
				        %><a href="proj-profile.jsp">Add New Project</a><br><%
				        for (Project p : projs) {
				        	
				        	students = p.getApplications();
				        	boolean hasApplicants = false;
				        %>
						<form name="filter-list" id="filter-list" class="clearfix">
							<h1><a href="edit-proj-profile.jsp?id=<%=p.getId()%>"><%=p.getName() %></a></h1>
							<div class="search-container">
								<input class="search-text" type="text" placeholder="Search..."/>
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
									if (a.getStatus() == ApplicationStatus.Accepted){
										hasApplicants = true;
										s = a.getStudentApplicant();
										String cssClasses = s.getName().replaceAll(" ", "_").toLowerCase() + " "
							                    + s.getString(s.getMajors()).replaceAll(" ", "_").toLowerCase() + " "
							                     + s.getString(s.getSkills()).replaceAll(" ", "_").toLowerCase() + " "
							                     + s.getString(s.getInterests()).replaceAll(" ", "_").toLowerCase() + " "
							                     + s.getNetID().replaceAll(" ", "_").toLowerCase();
										%>
										<tr class="<%= cssClasses %>">
											<td>
												<a class="actionButton remove" href="remove-student.jsp?id=<%=a.getId()%>">Remove</a>
											</td>
											<td><a href="profile-nonedit.jsp?studid=<%=s.getNetID()%>"><%=s.getName()%></a></td>
											<td><%=s.getGpa() %></a></td>
											<td><%=s.getString(s.getMajors()) %></td>
											<td><%=s.getYear() %></td>
											<td><%=s.getString(s.getSkills()) %></td>
											<td><%=s.getString(s.getInterests()) %></td>
										</tr>
								<%}
								}
								if (!hasApplicants) { %>
								<td colspan = "7"><i>No students have been accepted yet.</i></td>
								 <%
				        }
								%>
								
							</tbody>
						</table>
						
						<br>
						<%}%>
						<jsp:include page="pager.jsp"/>
						<br>
						
					</div>				
				</div>
			</div>
		</div>
	</div>
</body>
</html>
