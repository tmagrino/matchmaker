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
%>


<h1>Latest Additions</h1><br />
<table>
	<tr>
		<th>
			<td>Type</td>
			<td>Description</td>
			<td>Date Added</td>
		</th>
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
						<td> </td>
						<td><%= add.getType() %> </td>
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
