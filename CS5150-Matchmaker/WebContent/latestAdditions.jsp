<jsp:include page="header.jsp">
	<jsp:param name="stud_or_prof" value="admin" />
	<jsp:param name="sidebar_type" value="stud-profile" />
	<jsp:param name="sidebar_selected" value="view" />
	<jsp:param name="top_selected" value="project" />
</jsp:include>
<%@page
	import="java.util.*,model.Student, model.*, org.json.JSONObject,javax.persistence.*"%>

<div class="content">
<%
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
	EntityManager em = emf.createEntityManager();
	String[] options = {"major", "minor", "interest", "skill", "college",
			"department"
	};
	List<String> ops = Arrays.asList(options);
	String set = request.getParameter("category");
	String category = request.getParameter("mydropdown");
	List<LatestAddition> additions;
	System.out.println("Loaded latestAddition, drop-down = "+category+" , url = "+set);
	if (category == null || category.equals("recents")) {
		if (set == null || !ops.contains(set)) { 
			System.out.println("Setting to recents");
			category = "recents";
			additions = FieldValueController.getLatestAddedFields(em);
		}
		else {
			category = set;
			additions = FieldValueController.getLatestAddedFields(em, category);
		}
	}
	else {
		additions = FieldValueController.getLatestAddedFields(em, category);
	}
%>
<form name="filter-list" id="filter-list" class="clearfix">
   <h1>Vocabulary</h1>    
	<div class="search-container">
		<input class="search-text" type="text" placeholder="Search..."/>
		<input type="submit" value="Filter"/>
	</div>
</form>
<form action="latestAdditions.jsp">
Category:<br /><select name="mydropdown" onchange="this.form.submit()">
	<option value="recents" <% if(category.equals("recents")){%>selected<%} %>>All</option>
	<option value="college" <% if(category.equals("college")){%>selected<%} %>>Colleges</option>
	<option value="department" <% if(category.equals("department")){%>selected<%} %>>Departments</option>
	<option value="interest" <% if(category.equals("interest")){%>selected<%} %>>Interests</option>
	<option value="major" <% if(category.equals("major")){%>selected<%} %>>Majors</option>
	<option value="minor" <% if(category.equals("minor")){%>selected<%} %>>Minors</option>
	<option value="skill" <% if(category.equals("skill")){%>selected<%} %>>Skills</option>
</select>
</form>

<br />
<% 
	if (category.equals("recents")) {
		%><h1>All</h1><%
	}
	else if (category.equals("college")) {
		%><h1>Colleges</h1><%
	}
	else if (category.equals("department")) {
		%><h1>Departments</h1><%
	}
	else if (category.equals("interest")) {
		%><h1>Interests</h1><%
	}
	else if (category.equals("major")) {
		%><h1>Majors</h1><%
	}
	else if (category.equals("minor")) {
		%><h1>Minors</h1><%
	}
	else if (category.equals("skill")) {
		%><h1>Skills</h1><%
	}
	else {
			
	}
%>
<%
	if (additions.size() == 0) {
		%>
			<p>No <%= category %> found</p>
		<%
	}
	else {		
%>
	<table class="additions_table">
		<tr class="no-results">
			<th></th>
			<th>Type</th>
			<th>Description</th>
			<th>Date Added</th>
		</tr>
		<% 
			for (LatestAddition add : additions) {
				String cssClasses = add.getType().replaceAll(" ", "_").toLowerCase() + " "
	                    + add.getName().replaceAll(" ", "_").toLowerCase();
				%>
					<tr class="<%=cssClasses %>">
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
