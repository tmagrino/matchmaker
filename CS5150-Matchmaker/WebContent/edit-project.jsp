<% 
	int id; 
	String proj_sidebar, proj_title;
	if(request.getParameter("proj_id") != null){
		id = Integer.parseInt(request.getParameter("proj_id"));
		proj_sidebar = request.getParameter("proj_id");
		/* NOTE: this code is just here as placeholder.  we will ultimately look up the id
		in the database to retrieve all the info about the project */
		if(id == 0){
			proj_title = "Project A";	
		}
		else{
			proj_title = "Project B";
		}
	}
	else{
		id = -1;
		proj_sidebar = "new";
		proj_title = "";
	}
%>
<jsp:include page="header.jsp">
    <jsp:param name="stud_or_prof" value="prof"/>
    <jsp:param name="sidebar_type" value="prof-project"/>
    <jsp:param name="sidebar_selected" value="<%= proj_sidebar %>"/>
    <jsp:param name="top_selected" value="project"/>
</jsp:include>
					<div class="content">
						<h1>My Projects</h1>
						<form name="profile-form" action="/">
							<div class="photo-info clearfix">
								<div class="info">
									<h2>Bob Smith</h2>
									<p class="required"><label for="title">Title</label><input name="title" value="<%= proj_title %>" type="text"></input></p>
									<p class="required"><label for="dept">Department</label><input name="dept" type="text"></input></p>
									<p><label for="specialization">Specialization</label><input name="specialization" type="text"></input></p>
									<p><label for="req-skills">Required Skills</label><input name="req-skills" type="text"></input></p>
									<p><label for="url">Project URL</label><input name="url" type="text"></input></p>
									<p><label for="description">Project Description</label><textarea name="description"></textarea></p>
								</div>
							</div>
							<input type="submit" value="Save Changes"></input>
						</form>
					</div>				
				</div>
			</div>
		</div>
	</div>
</body>
</html>