<jsp:include page="header.jsp">
    <jsp:param name="stud_or_prof" value="prof"/>
    <jsp:param name="sidebar_type" value="prof-students"/>
    <jsp:param name="sidebar_selected" value="invite"/>
    <jsp:param name="top_selected" value="students"/>
</jsp:include>
<%@page import="java.util.*,model.Student, model.StudentController"%>
					<div class="content">
						<h1>Students</h1>
						<h2 class="subheading">Filters</h2>
						<div class="filters">
							<input type="checkbox" name="all-profile">All Profile Info
							<input type="checkbox" name="research-interest">Research Interest
							<input type="checkbox" name="research-interest">Skills
						</div>
						<ul class="project-list" id="project-list-pagination">
						<%List<Student> studentList = StudentController.getAllStudents(); 
							for(Student s: studentList)
							{
							%>
								<li class="clearfix">
									<div class="status">
										<p class="invite">Invite</p>
									</div>
									<div class="project-info">
										<div class="delete">Hide</div>
										<h3><%=s.getName() %></h3>
										<p>Major: <%=s.getMajorString() %>, College: <%=s.getCollegeString() %></p>
										<p>Year: <%=s.getYear() %></p>
										<p>Skills: <%=s.getSkillString() %></p>
										<p>Research Interests: <%=s.getInterestString() %></p>
									</div>							
								</li>
							<% } %>
						</ul>
						<ul class="holder"></ul>
					</div>				
				</div>
			</div>
		</div>
	</div>
</body>
</html>