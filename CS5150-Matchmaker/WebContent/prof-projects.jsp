<jsp:include page="header.jsp">
    <jsp:param name="stud_or_prof" value="prof"/>
    <jsp:param name="sidebar_type" value="prof-project"/>
    <jsp:param name="sidebar_selected" value="current"/>
    <jsp:param name="top_selected" value="project"/>
</jsp:include>
					<div class="content">
						<h1>My Projects</h1>
						<ul class="project-list">
							<li class="clearfix">
								<div class="project-info">
									<div class="delete">Cancel</div>
									<h3>Project Name</h3>
									<p><a href="#">Link to Project Webpage</a></p>
									<p>Researcher Name</p>
									<a class="edit" href="edit-project.jsp">Edit</a>
								</div>							
							</li>
							<li class="clearfix">
								<div class="project-info">
									<div class="delete">Cancel</div>
									<h3>Project Name</h3>
									<p>This is a project description instead of a URL.  This is a project description instead of
									a URL.  This is a project description instead of a URL.</p>
									<p>Researcher Name</p>
									<a class="edit" href="edit-project.jsp">Edit</a>
								</div>							
							</li>
						</ul>
						
					</div>				
				</div>
			</div>
		</div>
	</div>
</body>
</html>