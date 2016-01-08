<%--
	This page allows you to edit the fields on your project profile.
 --%>
<jsp:include page="header.jsp">
	<jsp:param name="stud_or_prof" value="admin" />
	<jsp:param name="top_selected" value="profile" />
</jsp:include>
<%@page import="java.util.*,model.*, org.json.*,javax.persistence.*"%>
<%
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
	EntityManager em = emf.createEntityManager();

	EntityTransaction tx = em.getTransaction();
	tx.begin();

	JSONObject jsonSkills = FieldValueController.getItemJson(em,FieldFactory.SKILL);
	JSONObject jsonInterest = FieldValueController.getItemJson(em,FieldFactory.INTEREST);
	Researcher r = ResearcherController.getResearcherByNetID(em,(String)session.getAttribute("currentUser"));
	String[] attributes = {"Title", "Research Area", "Required Skills", "Project URL", "Project Description"};
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
	<form name="profile" action="save-project-changes.jsp">
		<table class="info">
			<tr>
				<td class="attr-label" colspan="3"><h2>Edit Fields</h2></td>
			</tr>
			<% for(String attr: attributes){ %>
				<tr>
					<td class="attr-label"><%=attr %>:</td>
					<td class="field">
						<p class="read-only">
							<%=attr %>
						</p>
						<p>
							<a class="edit-btn <%= attr.length() > 80 ? "extended" : "" %>" href="#"> 
								<img src="images/pencil_small.png" alt="edit" />
							</a>
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
					<td>
						<button class="view-suggestion hidden" type="button">View All Suggestions</button>
					</td>
				</tr>
				<% } tx.commit(); %>
		</table>
		<input type="submit" value="Save Changes"></input>
	</form>
</div>
<jsp:include page="footer.jsp"></jsp:include>
