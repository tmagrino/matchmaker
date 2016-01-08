<%--
	This is editable researcher profile page for the researcher.
 --%>
<jsp:include page="header.jsp">
    <jsp:param name="stud_or_prof" value="researcher"/>
    <jsp:param name="top_selected" value="profile"/>
</jsp:include>
<%@page import="java.util.*,model.*, org.json.*,javax.persistence.*"%>

<%
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
	EntityManager em = emf.createEntityManager();
	
	EntityTransaction tx = em.getTransaction();
	tx.begin();

	String currentuser = (String) session.getAttribute("currentUser");
	
	Researcher r = ResearcherController.getResearcherByNetID(em,currentuser);
	Set<String> req_attr = new HashSet<String>(Arrays.asList("Email", "URL", "Department")); 
	String[] attributes = {"Email", "URL", "Department", "Research Area"};
	// Update Research Area function to retrieve Interest objects.
	String[] autocomplete_attr = {FieldFactory.DEPARTMENT, FieldFactory.INTEREST};
	JSONArray jsonArrAll = new JSONArray();
	JSONArray jsonArrStud = new JSONArray();
	for(String auto_attr: autocomplete_attr){
		jsonArrAll.put(FieldValueController.getItemJson(em, auto_attr));
		if(r != null)
	jsonArrStud.put(FieldValueController.getObjectJson(r.getListAttribute(auto_attr)));
     	User usr = UserController.findUser(em, r.getNetID());
     	em.lock(usr, Locking.getWriteLockType());
     	UserController.setName(em, usr, r.getName());

	}
%>
<script type="text/javascript">
	var autocomplete_attr = Array("department", "research_area");
	var jsonArrAll = <%= jsonArrAll %>;
    var jsonArrStud = <%= jsonArrStud %>;
</script>
<div class="content">
	<!-- <h1>My Profile</h1> -->
	<div id="all-department" class="hidden" title="All Department Suggestions"></div>
	<div id="all-research_area" class="hidden" title="All Research Area Suggestions"></div>
	<div class="photo-info clearfix">
		<form name="profile" action="save-researcher-profile-changes.jsp" method="GET">
			<p class="error-msg">Errors were found.  Please correct the errors before saving.</p>
			<table class="info">
				<%if(!(r.getName().equals("New User"))){ %>
	                                <tr>
	                                     <td class="attr-label" colspan="3"><h2><%=r.getName() %></h2></td>
	                                </tr>
                                <% } else { %>
                                	<tr>
                                    	<td class="attr-label"><h2>New User: </h2></td>
	                                   	<td class="field">
	                                    	 <p class="editable">
	                                    	 	<input name="NewUser" value="" type="text" />
	                                    	 </p>
                                    	 </td>
                            		</tr>
                                <% }
				for(String attr: attributes){ %> 
					<tr <%= req_attr.contains(attr) ? "class=\"required\"" : "" %>>
					<td class="attr-label"><%=attr %>:</td>
					<td class="field">
						<% if(r != null && r.getAttribute(attr) != null){ %>
						<p class="read-only <%= r.getAttribute(attr).equals("") ? "hidden" : "" %>">
							<% if (attr == "URL"){%>
							<a href = "http://<%=r.getWebpage() %>"><%=r.getWebpage()%></a>
							<%}else{ %>
							<%=r.getAttribute(attr) %>
							<%} %>
							<a class="edit-btn <%=(r.getAttribute(attr).length() > 80) ? "extended" : "" %>" href="#"> 
								<img src="images/pencil_small.png" alt="edit" />
							</a>
						</p>
						<p class="editable <%= !r.getAttribute(attr).equals("") ? "hidden" : "" %>">
							<input name="<%=attr.replaceAll(" ", "_").toLowerCase() %>"
								value="<%=r.getAttribute(attr) %>" type="text" />
						</p>
					</td>
					<td>
						<button class="view-suggestion hidden" type="button">View All Suggestions</button>
					</td>
					<% } %>
				</tr>
				<% } tx.commit(); %>
			</table>
			<input class="save" type="submit" value="Save Changes" />
		</form>
	</div>
</div>
</div>
</div>
</div>
</div>
</body>
</html>
