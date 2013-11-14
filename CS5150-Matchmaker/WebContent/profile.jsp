<jsp:include page="header.jsp">
    <jsp:param name="stud_or_prof" value="stud"/>
    <jsp:param name="sidebar_type" value="stud-profile"/>
    <jsp:param name="sidebar_selected" value="view"/>
    <jsp:param name="top_selected" value="profile"/>
</jsp:include>
<%@page import="java.util.*,model.Student, model.StudentController"%>
<%
	 Student s = StudentController.getStudentByNetID("lr437"); %>
					<div class="content">
						<h1>My Profile</h1>
						<h2 class="subheading">General Information</h2>
						<div class="photo-info clearfix">
							<img class="avatar" src="images/avatar-male.jpg" alt="avatar"/>
							<div class="info">
							
								<h2><%=s.getName() %></h2>
								<p>Email: <%=s.getEmail() %></p>
								<p>Major: <%=s.getMajorString() %></p>
								<p>Minor: <%=s.getMinorString() %></p>
								<p>Year: <%=s.getYear().toString() %></p>
								<p>College: <%=s.getCollegeString() %></p>
							</div>
						</div>
						<h2 class="subheading">Application Information</h2>	
						<div class="application-info">
							<p>GPA: <%=s.getGpa() %></p>
							<p>Skills: <%=s.getSkillString() %></p>
							<p>Research Interests: <%=s.getInterestString() %></p>
						</div>
					</div>				
				</div>
			</div>
		</div>
	</div>
</body>
</html>