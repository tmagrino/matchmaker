<jsp:include page="header.jsp">
    <jsp:param name="stud_or_prof" value="researcher"/>
    <jsp:param name="sidebar_type" value="researcher-students"/>
    <jsp:param name="sidebar_selected" value="invite"/>
    <jsp:param name="top_selected" value="students"/>
</jsp:include>
<%@page import="java.util.*,model.Student, model.StudentController"%>
					<div class="content">
						<h1>Students</h1>
						<table class="project-list">
							<thead>
								<tr>
									<th>Name</th>
									<th>GPA</th>
									<th>Major</th>
									<th>Minor</th>
									<th>College</th>
									<th>Class Year</th>
									<th>Skills</th>
									<th>Research Area</th>
								</tr>
							</thead>
							<tbody>
						<%List<Student> studentList = StudentController.getAllStudents(); 
							for(Student s: studentList)
							{
							%>
								
									<tr>
										<td><%=s.getName() %></td>
										<td><%=s.getGpa() %></td>
										<td><%=s.getMajorString() %></td>
										<td><%=s.getMinorString() %></td>
										<td><%=s.getCollegeString() %></td>
										<td><%=s.getYear() %></td>
										<td><%=s.getSkillString() %></td>
										<td><%=s.getInterestString() %></td>
									</tr>
							<% } %>
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