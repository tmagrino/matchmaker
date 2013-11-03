<% if(request.getParameter("sidebar_type").equals("prof-profile")){ %>
	<li class="first">
		<a <% if(request.getParameter("sidebar_selected").equals("view")){ %>class="selected"<% } %> href="prof-profile.jsp">View Profile</a>
	</li>
	<li>
		<a <% if(request.getParameter("sidebar_selected").equals("edit")){ %>class="selected"<% } %> href="edit-prof-profile.jsp">Edit Profile</a>
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

<% if(request.getParameter("sidebar_type").equals("prof-profile")){ %>
	<li class="first">
		<a <% if(request.getParameter("sidebar_selected").equals("view")){ %>class="selected"<% } %> href="prof-profile.jsp">View Profile</a>
	</li>
	<li>
		<a <% if(request.getParameter("sidebar_selected").equals("edit")){ %>class="selected"<% } %> href="edit-prof-profile.jsp">Edit Profile</a>
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
<% if(request.getParameter("sidebar_type").equals("prof-project")){ %>
	<li class="first">
		<a <% if(request.getParameter("sidebar_selected").equals("current")){ %>class="selected"<% } %> href="prof-projects.jsp">Current Projects</a>
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
