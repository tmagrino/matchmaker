<%--
	 This page is used for the Administrator to search for the users 
	 The admin can also act as user or can add/delete accounts.
--%>

<% session.setAttribute("page", "adminSearch"); %>
<%@page import="java.util.*,model.*, org.json.*,javax.persistence.*" %>

<jsp:include page="header.jsp">
	<jsp:param name="stud_or_prof" value="admin" />		
	<jsp:param name="top_selected" value="profile" />
</jsp:include>
<%
	//If a netid or name were passed to this page, we will do a search for the user in this code
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
	EntityManager em = emf.createEntityManager();

	EntityTransaction tx = em.getTransaction();
	tx.begin();

	String netID = request.getParameter("netID");
	
	
	// Null User fix 
		User user = UserController.findUser(em, netID);
	
		if(user == null && session.getAttribute("adminUser") == null){
				response.sendRedirect("select-role.jsp");	
		}
		
		// End - Null User fix 
	
	String name = request.getParameter("name");
	
	Student s = null;
	User u = null;
	Researcher r = null;
	
	if(netID != null && netID != ""){
		u = UserController.findUser(em, netID);
		s = StudentController.getStudentByNetID(em, netID);
		r = ResearcherController.getResearcherByNetID(em, netID);
	}
	else if (name != null){
		s = StudentController.getStudentByName(em, name);
		r = ResearcherController.getResearcherByName(em, name);
		if (s != null){
			u = UserController.findUser(em, s.getNetID());
		}
		else if (r != null){
			u = UserController.findUser(em, r.getNetID());
		}
	}


	
	Boolean isStudent = false;
	if(s!= null){
		isStudent = true;
	}
	
	Boolean isResearcher = false;
	if(r != null){
		isResearcher = true;
	}
	
	Boolean isAdmin = false;
	if(u != null){
		if(u.isAdmin()){
			isAdmin = true;
		}
	}
	
	Boolean searchCompleted = false;
	if(!isAdmin && !isStudent && !isResearcher && (netID != null || name != null) ){
		searchCompleted = true;
	}
%>
<div class="content">
	<h3 class="subheading">&nbsp;&nbsp;Search User</h3>
	<ul class="searchUser">
		<li class="clearfix">
			<div class="accounts">	
				<form action="admin-searchUser.jsp" method="Get">
					<font size ="4"><b>Search By Net ID : </b></font>
					<%if(netID != null) {%>
						<input type="text" name="netID" value="<%=netID%>" size=35/>
					<%} else{%>
						<input type="text" name="netID" size=35/>
					<% }%><br />
					<font size ="4"><b>Search By Name : </b>&nbsp;</font>
					<%if(name != null) {%>
						<input type="text" name="name" value="<%=name%>" size=35/>
					<%} else{%>
						<input type="text" name="name" size=35/>
					<% }%>
					<input type="Submit" value="Search" size=20  style="width: 10em; height:2em"/>
				</form>
				<br><br>
				<!--  Display information about user if it was requested through URL -->
				<% if((netID != null || name != null) && u != null) { %>
					<table cellspacing=10 cellpadding=10 align=center width="100%">
						<tr>
							<td><h3>Name:</h3></td>
							<td><p class = "read-only" size=35><%= u.getName()%></p></td>
						</tr>
						<tr>
							<td><h3>NetID:</h3></td>
							<td><p class = "read-only" size=35><%= u.getNetid()%></p></td>
						</tr>
						<tr>
							<td>
								<h3>Role:</h3>
								<br>
								<div class="status">
									<% if(isStudent){
										session.setAttribute("currentUser", s.getNetID());
									%>
								 		<form action="profile.jsp" method="get">
								 			<input type="hidden" name="netId" value="<%=u.getNetid() %>">
											<input type="submit" value="Act as User" size=20  style="width: 10em; height:2em"/>
										</form>
							 		<%} else if(isResearcher){
							 			session.setAttribute("currentUser", r.getNetID());
							 		%>
								 		<form action="researcher-profile.jsp" method="get">
								 			<input type="hidden" name="netId" value="<%=u.getNetid() %>">
											<input type="submit" value="Act as User" size=20  style="width: 10em; height:2em"/>
										</form>
									<%} else if(u!= null && u.isAdmin()){
							 			session.setAttribute("currentUser", u.getNetid());
							 		%>
								 		<form action="admin-searchUser.jsp" method="get">
								 			<input type="hidden" name="netId" value="<%=u.getNetid() %>">
											<input type="submit" value="Act as User" size=20  style="width: 10em; height:2em"/>
										</form>
									<%}%>
								</div> 		<!--  Close div.status -->
							</td>	<!-- Close td with "Role" title -->
							<td align="left" rowspan="2">
								 	<form action="update-Role.jsp" method="Get">
								 		<% if(isAdmin){ %>
								 			<p align="left">&nbsp;&nbsp;<input type="checkbox" name="adminRole" value="Admin" checked="checked"/>Administrator
								 		<%} else{%>
								 			<p align="left">&nbsp;&nbsp;<input type="checkbox" name="adminRole" value="Admin"/>Administrator
								 		<%} %>
								 		<% if(isResearcher){ %>
								 			<p align="left">&nbsp;&nbsp;<input type="checkbox" name="researcherRole" value="Researcher" checked="checked"/>Project Leader
								 		<%} else{ %>
								 			<p align="left">&nbsp;&nbsp;<input type="checkbox" name="researcherRole" value="Researcher"/>Project Leader
								 		<% }%>
								 		<% if(isStudent){%>
								 			<p align="left">&nbsp;&nbsp;<input type="checkbox" name="studentRole" value="Student" checked="checked"/>Student &nbsp;&nbsp;
								 		<% } else {%>
								 			<p align="left">&nbsp;&nbsp;<input type="checkbox" name="studentRole" value="Student"/>Student &nbsp;&nbsp;
								 		<% }%>
								 		<input type="hidden" name="userNetID" value="<%=request.getParameter("netID") %>" />
								 		<input type="hidden" name="userName" value="<%=request.getParameter("name") %>"/>
								 		<p><br><input type="submit" value="Update Roles" size=20  style="width: 10em; height:2em"/>
									</form> 		
						 	 </td>  <!-- Close td with role checkboxes -->
						</tr>  <!--  Close role tr -->
									
					</table>

				<!--  If netid, name, and u are null but searchCompleted is true, then the user that was searched for doesn't exist -->
				<% } else if(searchCompleted){ %>	
					<h4 class="subheading"><i>No User found</i></h4>
				<%} tx.commit(); %>
					<h4 class="subheading"><i>&nbsp;&nbsp;<%=(request.getParameter("searchDisplay") == null) ? "" : request.getParameter("searchDisplay")%></i></h4>
			</div>	<!-- Close the accounts div -->
		</li>	<!-- Close li.clearfix -->
	</ul>	<!-- Close ul.searchUser -->
</div>							
<jsp:include page="footer.jsp"></jsp:include>
