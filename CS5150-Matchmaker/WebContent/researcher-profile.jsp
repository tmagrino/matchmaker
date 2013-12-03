<jsp:include page="header.jsp">
    <jsp:param name="stud_or_prof" value="researcher"/>
    <jsp:param name="top_selected" value="profile"/>
</jsp:include>
<%@page import="java.util.*,model.*, org.json.*,javax.persistence.*"%>

<%	EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
	EntityManager em = emf.createEntityManager();
	
	String currentuser = (String) session.getAttribute("currentUser");
	if (currentuser == null) {
		currentuser = "tm123";
	}
	Researcher r = ResearcherController.getResearcherByNetID(em,currentuser); 
	String[] attributes = {"Email", "URL", "Departments", "Research Area"};
	// Update Research Area function to retrieve Interest objects.
	String[] autocomplete_attr = {ItemFactory.MAJOR, "interest"};
	JSONArray jsonArrAll = new JSONArray();
	JSONArray jsonArrStud = new JSONArray();
	for(String auto_attr: autocomplete_attr){
		jsonArrAll.put(ListController.getItemJson(em, auto_attr));
	//	jsonArrStud.put(r.getObjectJson(r.getListAttribute(auto_attr)));
	}
%>
<script type="text/javascript">
	var autocomplete_attr = Array("department", "research_area");
	var jsonArrAll = <%= jsonArrAll %>;
    var jsonArrStud = <%= jsonArrStud %>;
</script>
<div class="content">
	<h1>My Profile</h1>
	<div id="all-department" class="hidden" title="All Department Suggestions"></div>
	<div id="all-research_area" class="hidden" title="All Research Area Suggestions"></div>
	<div class="photo-info clearfix">
		<img class="avatar" src="images/avatar-male.jpg" alt="avatar" />
		<form name="profile" action="#" method="GET">
			<table class="info">
				<tr>
					<td class="attr-label" colspan="3">
						<h2><%=r.getName()%></h2>
					</td>
				</tr>
				<% for(String attr: attributes){ %>
				<tr>
					<td class="attr-label"><%=attr %>:</td>
					<td class="field">
						<p class="read-only <%= r.getAttribute(attr) == "" ? "hidden" : "" %>">
							<%=r.getAttribute(attr) %>
							<a class="edit-btn <%= r.getAttribute(attr).length() > 80 ? "extended" : "" %>" href="#"> 
								<img src="images/pencil_small.png" alt="edit" />
							</a>
						</p>
						<p class="editable <%= r.getAttribute(attr) != "" ? "hidden" : "" %>">
							<input name="<%=attr.replaceAll(" ", "_").toLowerCase() %>"
								value="<%=r.getAttribute(attr) %>" type="text" />
						</p>
						<p class="other hidden">
							<input name="<%=attr.replaceAll(" ", "_").toLowerCase()+"_other" %>" type="text" />
						</p>
					</td>
					<td>
						<button class="view-suggestion hidden" type="button">View All Suggestions</button>
					</td>
				</tr>
				<% } %>
			</table>
		</form>
	</div>
</div>
</div>
</div>
</div>
</div>
</body>
</html>