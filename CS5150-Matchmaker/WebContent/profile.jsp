<jsp:include page="header.jsp">
    <jsp:param name="stud_or_prof" value="stud"/>
    <jsp:param name="sidebar_type" value="stud-profile"/>
    <jsp:param name="sidebar_selected" value="view"/>
    <jsp:param name="top_selected" value="profile"/>
</jsp:include>
<%@page import="java.util.*,model.Student, model.GetStudentInfo"%>
<%Student s = GetStudentInfo.maxGPA(); %>
					<div class="content">
						<h1>My Profile</h1>
						<h2 class="subheading">General Information</h2>
						<div class="photo-info clearfix">
							<img class="avatar" src="avatar-female.jpg" alt="avatar"/>
							<div class="info">
								<h2><%=s.getName() %></h2>
								<p>Email: jd322@cornell.edu</p>
								<p>Major: Computer Science</p>
								<p>Minor: Music</p>
								<p>Year: <%=s.getYear() %></p>
								<p>School: College of Engineering</p>
							</div>
						</div>
						<h2 class="subheading">Application Information</h2>	
						<div class="application-info">
							<p>GPA: <%=s.getGpa() %></p>
							<p>Skills: Java, C++, Python</p>
							<p>Research Interests: Machine Learning</p>
						</div>
					</div>				
				</div>
			</div>
		</div>
	</div>
</body>
</html>