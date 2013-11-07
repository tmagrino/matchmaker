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
							<a class="filter-all" href="#">Filter by all profile info</a>
							<input type="checkbox" name="research-interest">Research Interest
							<input type="checkbox" name="research-interest">Skills
							GPA:
					          	<select>
					          		<option value="gt4"> &gt;4.0</option>
					          		<option value="35to4">3.5 - 4.0</option>
					          		<option value="3to35">3.0 - 3.5</option>
					          		<option value="lt3">&lt;2.0</option>
					          	</select>
					        Sort By:
					        	<select>
					        		<option value="researcher">Researcher</option>
					        		<option value="research-area">Research Area</option>
					        		<option value="skills">Skills</option>
					        	</select>
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
										<div class="delete">Hide</div>
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