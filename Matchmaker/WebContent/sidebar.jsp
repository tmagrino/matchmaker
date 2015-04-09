<%--
	This page is a sidebar navigation page for all users.
 --%>
<% if(request.getParameter("sidebar_type").equals("researcher-profile")){ %>
	<li class="first">
		<a <% if(request.getParameter("sidebar_selected").equals("view")){ %>class="selected"<% } %> href="researcher-profile.jsp">View Profile</a>
	</li>
	<li>
		<a <% if(request.getParameter("sidebar_selected").equals("edit")){ %>class="selected"<% } %> href="edit-researcher-profile.jsp">Edit Profile</a>
	</li>
<% } %>
<% if(request.getParameter("sidebar_type").equals("stud-profile")){ %>
	<li class="first">
		<a <% if(request.getParameter("sidebar_selected").equals("view")){ %>class="selected"<% } %> href="profile.jsp">View Profile</a>
	</li>
	<li>
		<a <% if(request.getParameter("sidebar_selected").equals("edit")){ %>class="selected"<% } %> href="edit-profile.jsp">Edit Profile</a>
	</li>
<% } %>
<% if(request.getParameter("sidebar_type").equals("researcher-project")){ %>
	<li class="first">
		<a <% if(request.getParameter("sidebar_selected").equals("current")){ %>class="selected"<% } %> href="researcher-projects.jsp">Current Projects</a>
	</li>
	<li><a <% if(request.getParameter("sidebar_selected").equals("0")){ %>class="selected"<% } %> href="edit-project.jsp?proj_id=0">Project A</a></li>
	<li><a <% if(request.getParameter("sidebar_selected").equals("1")){ %>class="selected"<% } %> href="edit-project.jsp?proj_id=1">Project B</a></li>
	<li>
		<a <% if(request.getParameter("sidebar_selected").equals("new")){ %>class="selected"<% } %> href="edit-project.jsp">Create New Projects</a>
	</li>
<% } %>
<% if(request.getParameter("sidebar_type").equals("stud-project")){ %>
	<li class="first">
		<a <% if(request.getParameter("sidebar_selected").equals("current")){ %>class="selected"<% } %> href="student-projects.jsp">Current Projects</a>
	</li>
	<li>
		<a <% if(request.getParameter("sidebar_selected").equals("invitation")){ %>class="selected"<% } %>href="project-invitations.jsp">Project Invitations</a>
	</li>
	<li>
		<a <% if(request.getParameter("sidebar_selected").equals("new")){ %>class="selected"<% } %>href="new-projects.jsp">Select New Projects</a>
	</li>
	<li class="related">Related</li>
	<li>
		<a href="#">How to make the most of your research experience!</a>
	</li>

	
<% } %>
<% if(request.getParameter("sidebar_type").equals("researcher-students")){ %>
	<li class="first">
		<a <% if(request.getParameter("sidebar_selected").equals("invite")){ %>class="selected"<% } %> href="invite-students.jsp">Invite Students</a>
	</li>
	<li class="related">Project Applicants</li>
	<li><a <% if(request.getParameter("sidebar_selected").equals("0")){ %>class="selected"<% } %> href="project-applicants.jsp?proj_id=0">Project A</a></li>
	<li><a <% if(request.getParameter("sidebar_selected").equals("1")){ %>class="selected"<% } %> href="project-applicants.jsp?proj_id=1">Project B</a></li>
	
	
<% } %>
