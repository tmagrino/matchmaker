<%--
	This page is used to edit the profile page of the project for created by the 
	Project Lead. 
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

	Project p = ProjectController.getProjectById(em,request.getParameter("id"));
	JSONObject jsonSkills = FieldValueController.getItemJson(em,FieldFactory.SKILL);
	JSONObject jsonInterest = FieldValueController.getItemJson(em,FieldFactory.INTEREST);
	Researcher r = ResearcherController.getResearcherByNetID(em,(String) session.getAttribute("currentUser"));
	String[] attributes = {ProjectController.TITLE, ProjectController.AREA, 
			ProjectController.SKILL, ProjectController.URL, ProjectController.DESCRIPTION};
	String[] autocomplete_attr = {FieldFactory.INTEREST, FieldFactory.SKILL};

	JSONArray jsonArrAll = new JSONArray();
	JSONArray jsonArrProj = new JSONArray();
	
	jsonArrAll.put(FieldValueController.getItemJson(em, FieldFactory.INTEREST));
	jsonArrAll.put(FieldValueController.getItemJson(em, FieldFactory.SKILL)); 
	jsonArrProj.put(ProjectController.getObjectJson(p.getProjectAreas()));
	jsonArrProj.put(ProjectController.getObjectJson(p.getRequiredSkills()));
%>
<script type="text/javascript">
	var autocomplete_attr = Array("research_area", "required_skills");
	var jsonArrAll = <%= jsonArrAll %>;
    var jsonArrStud = <%= jsonArrProj %>;
</script>
<div class="content">
	<div id="all-research_area" class="hidden" title="All Research Area Suggestions"></div>
	<div id="all-required_skills" class="hidden" title="All Skills Suggestions"></div>
	<h1>My Projects</h1>
	<form name="profile" action="save-project-changes.jsp">
		<p class="other hidden">
			<input name="projid"  value="<%=p.getId()%>"type="text" />
		</p>
		<table class="info">
			<tr>
				<td class="attr-label" colspan="3"><h2><%=r.getName() %></h2></td>
			</tr>
			<% for(String attr: attributes){ %>
				<tr>
					<td class="attr-label"><%=attr %>:</td>
					<% if((attr).equals(ProjectController.URL)){ %>
						<td class="field">
							<p class="read-only">
								<a href="//<%=ProjectController.getAttribute(p, attr)%>">
								<%=ProjectController.getAttribute(p, attr)%></a>							
							</p>
                                                        <p>
                                                          <a class="edit-btn <%= attr.length() > 80 ?  "extended" : "" %>" href="#">
                                                            <img src="images/pencil_small.png" alt="edit" />
                                                          </a>
                                                        </p>
						<%} else{ %>
							<td class="field">
								<p class="read-only">
									<%=ProjectController.getAttribute(p, attr)  %>
								</p>
                                                                <p>
									<a class="edit-btn <%= attr.length() > 80 ? "extended" : "" %>" href="#"> 
										<img src="images/pencil_small.png" alt="edit" />
									</a>
                                                                </p>
							<%} %>
						<p class="editable hidden">
							<% if((attr).equals(ProjectController.DESCRIPTION)){ %>
								<textarea name = "project_description"><%=p.getDescription() %></textarea>
							<% } else{ %>
								<input name="<%=attr.replaceAll(" ", "_").toLowerCase() %>"
									value="<%=ProjectController.getAttribute(p, attr) %>" type="text" />
							<% } %>
						</p>
					</td>
					<td>
						<button class="view-suggestion hidden" type="button">View All Suggestions</button>
					</td>
				</tr>
				<% } tx.commit(); %>
		</table>
		<input type="submit" value="Save Changes"></input>
		<FORM><INPUT Type="button" VALUE="Cancel" onClick="history.go(-1);return true;"></FORM>
	</form>
</div>
<jsp:include page="footer.jsp"></jsp:include>
