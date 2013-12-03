<jsp:include page="header.jsp">
	<jsp:param name="stud_or_prof" value="stud" />
	<jsp:param name="top_selected" value="profile" />
</jsp:include>
<%@page
	import="java.util.*,model.*, org.json.*,javax.persistence.*"%>

<%	 EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
	 EntityManager em = emf.createEntityManager();
	 Student s;
	 if(request.getParameter("netId") == null){
		 s= StudentController.getStudentByNetID(em,"lr437");
	 }else{
		 s = StudentController.getStudentByNetID(em,request.getParameter("netId"));
	 }
	 
	 String[] attributes = {"Email", "Major", "Minor", "Year", "College", "GPA", "Skill", "Interest"};
	 String[] autocomplete_attr = {ItemFactory.MAJOR, ItemFactory.MINOR, ItemFactory.COLLEGE, ItemFactory.SKILL
			 , ItemFactory.INTEREST};
	 JSONArray jsonArrAll = new JSONArray();
	 JSONArray jsonArrStud = new JSONArray();
	 for(String auto_attr: autocomplete_attr){
		 jsonArrAll.put(ListController.getItemJson(em, auto_attr));
		 jsonArrStud.put(s.getObjectJson(s.getListAttribute(auto_attr)));
	 }
%>
<script type="text/javascript">
	var autocomplete_attr = Array("major", "minor", "college", "skills", "research_interests");
	var jsonArrAll = <%= jsonArrAll %>;
    var jsonArrStud = <%= jsonArrStud %>;
</script>

<div class="content">
	<h1>My Profile</h1>
	<div id="all-major" class="hidden" title="All Major Suggestions"></div>
	<div id="all-minor" class="hidden" title="All Minor Suggestions"></div>
	<div id="all-college" class="hidden" title="All College Suggestions"></div>
	<div id="all-skills" class="hidden" title="All Skill Suggestions"></div>
	<div id="all-research_interests" class="hidden" title="All Interest Suggestions"></div>
	<div class="photo-info clearfix">
		<img class="avatar" src="images/avatar-male.jpg" alt="avatar" />
		<form name="profile" action="save-profile-changes.jsp" method="GET">
			<table class="info">
				<tr>
					<td class="attr-label" colspan="3"><h2><%=s.getName() %></h2></td>
				</tr>
				<% for(String attr: attributes){ %>
				<tr>
					<td class="attr-label"><%=attr %>:</td>
					<td class="field">
						<p class="read-only <%= s.getAttribute(attr) == "" ? "hidden" : "" %>">
							<%=s.getAttribute(attr) %>
							<a class="edit-btn <%= s.getAttribute(attr).length() > 80 ? "extended" : "" %>" href="#"> 
								<img src="images/pencil_small.png" alt="edit" />
							</a>
						</p>
						<p class="editable <%= s.getAttribute(attr) != "" ? "hidden" : "" %>">
							<% if(attr.equals("Year")){ %>
								<select name = "year">
									<option value="">---</option>
								<% String year_val = s.getAttribute(attr); %>
								<% for(int i = 1; i<=5; i++){ 
									String i_str = Integer.toString(i);
									if(i == 5){
										i_str += "+";
									}
									if(year_val.equals(i_str)){ %>
										<option value="<%= i %>" selected="selected"><%= i_str%></option>
									<%	} else{ %>
										<option value="<%= i %>"><%= i_str%></option>
									<% } %>
								<% } %>
							</select>
							<% } else{ %>
							<input name="<%=attr.replaceAll(" ", "_").toLowerCase() %>"
								value="<%=s.getAttribute(attr) %>" type="text" />
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
				<% } %>
			</table>
			<input type="submit" value="Save Changes" />
		</form>

	</div>
</div>
</div>
</div>
</div>
</div>
</body>
</html>
