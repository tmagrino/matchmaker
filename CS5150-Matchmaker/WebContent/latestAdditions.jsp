<jsp:include page="header.jsp">
	<jsp:param name="stud_or_prof" value="stud" />
	<jsp:param name="top_selected" value="project" />
</jsp:include>
<%@page
	import="java.util.*,model.Student, model.*, org.json.JSONObject,javax.persistence.*"%>

<div class="content">
<%
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
	EntityManager em = emf.createEntityManager();
	List<LatestAddition> additions = ListController.getLatestAddedFields(em);
	String category = request.getParameter("category");
	if (category == null) {
		category = "Recent Additions";
	}
%>
<h1><%= category %></h1><br />
<select>
	<option value="recents">Recent Additions</option>
	<option value="colleges">Colleges</option>
	<option value="departments">Departments</option>
	<option value="interests">Interests</option>
	<option value="majors">Majors</option>
	<option value="minors">Minors</option>
	<option value="skills">Skills</option>
</select>
<br />
<table>
	<tr>
		<th></th>
		<th>Type</th>
		<th>Description</th>
		<th>Date Added</th>
	</tr>
	<% 
		if (additions.size() == 0) {
			%>
				<td colspan="4">No additions have been made</td>
			<%
		}
		else {
			for (LatestAddition add : additions) {
				%>
					<tr>
						<td> 
							<a class="actionButton remove" href="remove-item.jsp?type=
								<%= add.getType() %>&desc=<%= add.getName() %>
								<% 
									if (category != null) {
										%>&category=<%= category %>
								<%
									}
									
								%>
								">Remove
							</a>
						</td>
						<td><%= ("" + add.getType().charAt(0)).toUpperCase() + add.getType().substring(1) %> </td>
						<td><%= add.getName() %> </td>
						<td><%= add.getSubmissionDate() %> </td>
					</tr>
				
				<%
			}
		}
	%>
</table>
<jsp:include page="pager.jsp" />
</div>
<div></div>
<div></div>
<div></div>
<div></div>
<body></body>
<html></html>
