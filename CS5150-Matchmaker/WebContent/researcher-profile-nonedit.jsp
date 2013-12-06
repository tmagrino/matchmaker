<jsp:include page="header.jsp">
    <jsp:param name="stud_or_prof" value="stud"/>
    <jsp:param name="top_selected" value="profile"/>
</jsp:include>
<%@page import="java.util.*,model.*, org.json.*,javax.persistence.*"%>

<%	EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
	EntityManager em = emf.createEntityManager();
	
	Student s = 
	
	Researcher r = ResearcherController.getResearcherByNetID(em,(String)request.getParameter("id")); 
	String[] attributes = {"Email", "URL", "Departments", "Research Area"};
	

	

%>

<div class="content">
	<!-- <h1>My Profile</h1> -->
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