<jsp:include page="header.jsp">
    <jsp:param name="stud_or_prof" value="stud"/>
    <jsp:param name="sidebar_type" value="stud-project"/>
    <jsp:param name="sidebar_selected" value="new"/>
    <jsp:param name="top_selected" value="project"/>
</jsp:include>
					<div class="content">
						<h1>My Projects</h1>
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
										<p class="apply">Apply</p>
									</div>
									<div class="project-info">
										<div class="delete">X</div>
										<h3>Project Name</h3>
										<p><a href="#">Link to Project Webpage <%=i %></a></p>
										<p>Researcher Name</p>
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