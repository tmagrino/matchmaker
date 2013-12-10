<jsp:include page="header.jsp">
	<jsp:param name="stud_or_prof" value="admin" />
	<jsp:param name="sidebar_type" value="stud-profile" />
	<jsp:param name="sidebar_selected" value="view" />
	<jsp:param name="top_selected" value="profile" />
</jsp:include>
<%@page
	import="java.util.*,model.Student, model.*, org.json.JSONObject,javax.persistence.*"%>

<div class="content">
<%
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
	EntityManager em = emf.createEntityManager();
	String category = request.getParameter("mydropdown");
	List<LatestAddition> additions;
	if (category == null || category.equals("recents")) {
		additions = FieldValueController.getLatestAddedFields(em);
		category = "Recent Additions";
	}
	else {
		additions = FieldValueController.getLatestAddedFields(em, category);
	}
%>
<%if(category.equals("recents")) {
category =  "Recent Additions";} %>
<h1><%=category.toUpperCase() %> : </h1>

<form action="latestAdditions.jsp">
Select Category To be Displayed : <select name="mydropdown" onchange="this.form.submit()">
	<option value="recents" <% if(category.equals("recents")){%>selected<%} %>>Recent Additions</option>
	<option value="college" <% if(category.equals("college")){%>selected<%} %>>Colleges</option>
	<option value="department" <% if(category.equals("department")){%>selected<%} %>>Department</option>
	<option value="interest" <% if(category.equals("interest")){%>selected<%} %>>Interests</option>
	<option value="major" <% if(category.equals("major")){%>selected<%} %>>Majors</option>
	<option value="minor" <% if(category.equals("minor")){%>selected<%} %>>Minors</option>
	<option value="skill" <% if(category.equals("skill")){%>selected<%} %>>Skills</option>
</select>
</form>

<br />
<table class="additions_table">
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
