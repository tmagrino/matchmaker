<%--
	This page is editable profile page for the project.
 --%>
<jsp:include page="header.jsp">
	<jsp:param name="stud_or_prof" value="researcher" />
	<jsp:param name="top_selected" value="project" />
</jsp:include>
<%@page import="java.util.*,model.*, org.json.*,javax.persistence.*"%>
<%
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
	EntityManager em = emf.createEntityManager();

	EntityTransaction tx = em.getTransaction();
	tx.begin();

	JSONObject jsonSkills = FieldValueController.getItemJson(em,FieldFactory.SKILL);
	JSONObject jsonInterest = FieldValueController.getItemJson(em,FieldFactory.INTEREST);
	Researcher r = ResearcherController.getResearcherByNetID(em,(String) session.getAttribute("currentUser"));
	Set<String> req_attr = new HashSet<String>(Arrays.asList(ProjectController.TITLE)); 
	String[] attributes = {ProjectController.TITLE, ProjectController.AREA, 
			ProjectController.SKILL, ProjectController.URL, ProjectController.DESCRIPTION};
	String[] autocomplete_attr = {FieldFactory.INTEREST, FieldFactory.SKILL};
	JSONArray jsonArrAll = new JSONArray();
	JSONArray jsonArrStud = new JSONArray();
	for(String auto_attr: autocomplete_attr){
		jsonArrAll.put(FieldValueController.getItemJson(em, auto_attr));
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
	<h1>Add new project</h1>
	<form name="profile" action="save-project-changes.jsp">
		<p>Note: Either the Project URL field or the Project Description field is required.</p>
		<p class="error-msg">Errors were found.  Please correct the errors before saving.</p>
		<table class="info">
			<tr>
				<td class="attr-label" colspan="3"><h2><%=r.getName() %></h2></td>
			</tr>
			<% for(String attr: attributes){ %>
				<tr <%= req_attr.contains(attr) ? "class=\"required\"" : "" %>>
					<td class="attr-label"><%=attr %>:</td>
					<td class="field">
						<p class="editable">
							<% if((attr).equals(ProjectController.DESCRIPTION)){ %>
								<textarea name = "project_description"></textarea>
							<% } else{ %>
							<input name="<%=attr.replaceAll(" ", "_").toLowerCase() %>"
								type="text" />
							<% } %>
						</p>
					</td>
					<td>
						<button class="view-suggestion hidden" type="button">View All Suggestions</button>
					</td>
				</tr>
				<%  } tx.commit();%>
		</table>
		<input type="submit" value="Save Changes"></input>
		<FORM><INPUT Type="button" VALUE="Cancel" onClick="history.go(-1);return true;"></FORM>
	</form>
</div>
<jsp:include page="footer.jsp"></jsp:include>
