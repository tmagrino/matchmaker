<jsp:include page="header.jsp">
    <jsp:param name="stud_or_prof" value="stud"/>
    <jsp:param name="sidebar_type" value="stud-project"/>
    <jsp:param name="sidebar_selected" value="current"/>
    <jsp:param name="top_selected" value="project"/>
</jsp:include>
					<div class="content">
						<h1>My Projects</h1>
						<ul class="project-list">
							<li class="clearfix">
								<div class="status">
									<p class="rejected">Rejected</p>
								</div>
								<div class="project-info">
									<div class="delete">Hide</div>
									<h3>Project Name</h3>
									<p><a href="#">Link to Project Webpage</a></p>
									<p>Researcher Name</p>
								</div>							
							</li>
							<li class="clearfix">
								<div class="status">
									<p class="accepted">Accepted</p>
								</div>
								<div class="project-info">
									<div class="delete">Hide</div>
									<h3>Project Name</h3>
									<p><a href="#">Link to Project Webpage</a></p>
									<p>Researcher Name</p>
								</div>							
							</li>
							<li class="clearfix">
								<div class="status">
									<p class="pending">Pending</p>
									<a>Cancel</a>
								</div>
								<div class="project-info">
									<div class="delete">Hide</div>
									<h3>Project Name</h3>
									<p><a href="#">Link to Project Webpage</a></p>
									<p>Researcher Name</p>
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