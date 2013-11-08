<% 
	int id; 
	String proj_sidebar = null;
	if(request.getParameter("proj_id") != null){
		id = Integer.parseInt(request.getParameter("proj_id"));
		proj_sidebar = request.getParameter("proj_id");
	}
%>
<jsp:include page="header.jsp">
    <jsp:param name="stud_or_prof" value="researcher"/>
    <jsp:param name="sidebar_type" value="researcher-students"/>
    <jsp:param name="sidebar_selected" value="<%= proj_sidebar %>"/>
    <jsp:param name="top_selected" value="students"/>
</jsp:include>
<%@page import="java.util.*,model.Student, model.StudentController"%>
					<div class="content">
						<h1>Students</h1>
						<table class="project-list">
							<thead>
								<tr>
									<th>Name</th>
									<th>Major</th>
									<th>Minor</th>
									<th>Class Year</th>
									<th>Skills</th>
									<th>Research Area</th>
								</tr>
							</thead>
							<tbody>
							
								<% 
								List<Student> studentList = StudentController.getAllStudents();
								
								for(int i=1;i<=100;i+=2) { 
									for(Student s: studentList)
								{%>
									<tr class="odd">
										<td><%=s.getName() %></td>
										<td><%=s.getMajorString() %></td>
										<td><%=s.getMinorString() %></td>
										<td><%=s.getYear() %></td>
										<td><%=s.getTruncatedSkillString() %></td>
										<td><%=s.getTruncatedInterestString() %></td>
									</tr>
<!-- 									<tr class="even"> -->
<%-- 										<td>Student <%=i+1 %></td> --%>
<!-- 										<td>Art History</td> -->
<!-- 										<td>Music</td> -->
<!-- 										<td>4th Year</td> -->
<!-- 										<td>Java</td> -->
<!-- 										<td>Natural Language Processing</td> -->
<!-- 									</tr> -->
								<% }} %>
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
