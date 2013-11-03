<% 
	int id; 
	String proj_sidebar = null;
	if(request.getParameter("proj_id") != null){
		id = Integer.parseInt(request.getParameter("proj_id"));
		proj_sidebar = request.getParameter("proj_id");
	}
%>
<jsp:include page="header.jsp">
    <jsp:param name="stud_or_prof" value="prof"/>
    <jsp:param name="sidebar_type" value="prof-students"/>
    <jsp:param name="sidebar_selected" value="<%= proj_sidebar %>"/>
    <jsp:param name="top_selected" value="students"/>
</jsp:include>
					<div class="content">
						<h1>Students</h1>
						<h2 class="subheading">Filters</h2>
						<div class="filters">
							<input type="checkbox" name="all-profile">All Profile Info
							<input type="checkbox" name="research-interest">Research Interest
							<input type="checkbox" name="research-interest">Skills
						</div>
						<ul class="project-list" id="project-list-pagination">
							<% for(int i=1;i<=500;i++)
							{
							%>
								<li class="clearfix">
									<div class="status">
										<p class="invite">Invite</p>
									</div>
									<div class="project-info">
										<div class="delete">Hide</div>
										<h3>Student Name <%=i %></h3>
										<p>Student's Major, Student's College</p>
										<p>Graduation Year</p>
										<p>Skills</p>
										<p>Research Area</p>
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