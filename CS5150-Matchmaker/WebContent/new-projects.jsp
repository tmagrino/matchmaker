<jsp:include page="header.jsp">
    <jsp:param name="stud_or_prof" value="stud"/>
    <jsp:param name="sidebar_type" value="stud-project"/>
    <jsp:param name="sidebar_selected" value="new"/>
    <jsp:param name="top_selected" value="project"/>
</jsp:include>
					<div class="content">
						<h1>Projects</h1>
						<table class="project-list">
							<thead>
								<tr>
									<th>Project Name</th>
									<th>Researcher Name</th>
									<th>Project URL</th>
									<th>Description</th>
									<th>Research Area</th>
									<th>Required Skills</th>
								</tr>
							</thead>
							<tbody>
							<% for(int i=1;i<=500;i+=2)
							{
							%>
								<tr>
									<td>Project Name <%=i %></td>
									<td>Andrew Myers</td>
									<td><a href="#">Link to Project Webpage <%=i %></a></td>
									<td></td>
									<td>Programming Languages</td>
									<td>Java</td>
								</tr>
								<tr>
									<td>Project Name <%=i+1 %></td>
									<td>Tom Magrino</td>
									<td></td>
									<td>This is a description of Project <%=i+1 %>.  This is a...</td>
									<td>Systems</td>
									<td>Python</td>
								</tr>
							<% } %>
							</tbody>
						</table>
						<jsp:include page="pager.jsp"/>
					</div>				
				</div>
			</div>
		</div>
	</div>
</body>
</html>