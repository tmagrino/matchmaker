<% 
	int id; 
	String proj_sidebar = null;
	if(request.getParameter("proj_id") != null){
		id = Integer.parseInt(request.getParameter("proj_id"));
		proj_sidebar = request.getParameter("proj_id");
	}
%>
<jsp:include page="header.jsp">
    <jsp:param name="stud_or_prof" value="researcher"/>
    <jsp:param name="sidebar_type" value="researcher-students"/>
    <jsp:param name="sidebar_selected" value="<%= proj_sidebar %>"/>
    <jsp:param name="top_selected" value="students"/>
</jsp:include>
					<div class="content">
						<h1>Students</h1>
						<table class="project-list">
							<thead>
								<tr>
									<th>Name</th>
									<th>Major</th>
									<th>Minor</th>
									<th>Class Year</th>
									<th>Skills</th>
									<th>Research Area</th>
								</tr>
							</thead>
							<tbody>
								<% for(int i=1;i<=500;i+=2) { %>
									<tr class="odd">
										<td>Student <%=i %></td>
										<td>Computer Science</td>
										<td></td>
										<td>1st Year</td>
										<td>Python</td>
										<td>Machine Learning</td>
									</tr>
									<tr class="even">
										<td>Student <%=i+1 %></td>
										<td>Art History</td>
										<td>Music</td>
										<td>4th Year</td>
										<td>Java</td>
										<td>Natural Language Processing</td>
									</tr>
								<% } %>
							</tbody>
						</table>
						<div id="pager" class="pager">
							<form>
								<img src="images/first.png" class="first arrow"/>
								<img src="images/prev.png" class="prev arrow"/>
								<input type="text" class="pagedisplay"/>
								<img src="images/next.png" class="next arrow"/>
								<img src="images/last.png" class="last arrow"/>
								<select class="pagesize">
									<option selected="selected"  value="10">10</option>
									<option value="20">20</option>
									<option value="30">30</option>
									<option  value="40">40</option>
								</select>
							</form>
						</div>
					</div>				
				</div>
			</div>
		</div>
	</div>
</body>
</html>
