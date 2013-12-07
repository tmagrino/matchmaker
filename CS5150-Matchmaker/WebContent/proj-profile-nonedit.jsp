<!-- Project profile page -->
<jsp:include page="header.jsp">
	<jsp:param name="stud_or_prof" value="stud" />
	<jsp:param name="top_selected" value="project" />
</jsp:include>
<%@page
	import="java.util.*,model.*, org.json.*,javax.persistence.*"%>
<%
EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
EntityManager em = emf.createEntityManager();
Project p = ProjectController.getProjectById(em, request.getParameter("pid"));
String[] attributes = {"Research Area", "Required Skills", "Project URL", "Project Description"};


 %>
<div class="content">
	<form name="profile">
		<table class="info">
			<tr>
				<td class="attr-label" colspan="3"><h2><%=p.getName()%></h2>
			</tr>
			<tr>
				<td class="attr-label">Researcher Name :</td>
				<td class="attr-label"><h2><%=p.getResearchersString()%></h2></td>
			</tr>
			<% for(String attr: attributes){ %>
				<tr>
					<td class="attr-label"><%=attr %>:</td>
					<td class="field">
						<p class="read-only">
							<%=attr %>
						</p>
						<p class="editable hidden">
							<% if((attr).equals("Project Description")){ %>
								<textarea name = "project_description"><%=attr %></textarea>
							<% } else{ %>
							<input name="<%=attr.replaceAll(" ", "_").toLowerCase() %>"
								value="<%=attr %>" type="text" />
							<% } %>
						</p>
						<p class="other hidden">
							<input name="<%=attr.replaceAll(" ", "_").toLowerCase()+"_other" %>" type="text" />
						</p>
					</td>
				</tr>
				<% } %>
		</table>
		<FORM><INPUT Type="button" VALUE="Back" onClick="history.go(-1);return true;"></FORM>
	</form>
</div>
</body>
</html>