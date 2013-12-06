<jsp:include page="header.jsp">
	<jsp:param name="stud_or_prof" value="researcher" />
	<jsp:param name="top_selected" value="project" />
</jsp:include>
<%@page
	import="java.util.*,model.*, org.json.*,javax.persistence.*"%>
<%
EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
EntityManager em = emf.createEntityManager();
JSONObject jsonSkills = ListController.getItemJson(em,ItemFactory.SKILL);
JSONObject jsonInterest = ListController.getItemJson(em,ItemFactory.INTEREST);


Project p = ProjectController.getProjectById(em, request.getParameter("projectId"));

String[] attributes = {"Research Area", "Required Skills", "Project URL", "Project Description"};
String[] autocomplete_attr = {ItemFactory.INTEREST, ItemFactory.SKILL};
JSONArray jsonArrAll = new JSONArray();
JSONArray jsonArrStud = new JSONArray();
for(String auto_attr: autocomplete_attr){
	jsonArrAll.put(ListController.getItemJson(em, auto_attr));
//	jsonArrStud.put(r.getObjectJson(r.getListAttribute(auto_attr)));
}
 %>
<script type="text/javascript">
	var autocomplete_attr = Array("research_area", "required_skills");
	var jsonArrAll = <%= jsonArrAll %>;
    var jsonArrStud = <%= jsonArrStud %>;
</script>
<div class="content">
	<div id="all-research_area" class="hidden" title="All Research Area Suggestions"></div>
	<div id="all-required_skills" class="hidden" title="All Skills Suggestions"></div>
	<h1>My Projects</h1>
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
	</form>
</div>
</body>
</html>