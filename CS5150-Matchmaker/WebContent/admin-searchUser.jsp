<% session.setAttribute("page", "adminSearch"); %>
<%@page
	import="java.util.*,model.*, org.json.*,javax.persistence.*"%>

<jsp:include page="header.jsp">
	<jsp:param name="stud_or_prof" value="admin" />
	<jsp:param name="sidebar_type" value="stud-profile" />
	<jsp:param name="sidebar_selected" value="view" />
	<jsp:param name="top_selected" value="profile" />
</jsp:include>
<%
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
	EntityManager em = emf.createEntityManager();
	String netID = request.getParameter("netID");
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
						<h4 class="subheading"><i>&nbsp;&nbsp;<%=(request.getParameter("searchDisplay") == null) ? "" : request.getParameter("searchDisplay")%></i></h4>
						<h3 class="subheading">&nbsp;&nbsp;Search User</h3>
						<ul class="searchUser">
							<li class="clearfix">
								<div class="accounts">	
								 	<form action="admin-searchUser.jsp" method="Get">
								 	<font size ="4"><b>Search By Net ID : </b></font>
								 	<%if(netID != null) {%>
								 	<input type="text" name="netID" value="<%=netID%>" size=35/>
								 	<%} else{%>
								 	<input type="text" name="netID" value="" size=35/>
								 	<% }%><br />
								 	<font size ="4"><b>Search By Name : </b>&nbsp;</font>
								 	<%if(name != null) {%>
								 	<input type="text" name="name" value="<%=name%>" size=35/>
								 	<%} else{%>
								 	<input type="text" name="name" value="" size=35/>
								 	<% }%>
								 	<input type="Submit" value="Search" size=20  style="width: 10em; height:2em"/>
								 	</form>
									<br><br>
									<% if((netID != null || name != null) && u != null) {
								 	%>
									
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
												</div>
									    	</td>
										 	<td align="left" rowspan="2">
											 	<form action="update-Role.jsp" method="Get">
												 	<% if(isStudent){%>
											 			<p align="left">&nbsp;&nbsp;<input type="checkbox" name="studentRole" value="Student" checked="checked"/>Student &nbsp;&nbsp;
											 		<% } else {%>
											 		<p align="left">&nbsp;&nbsp;<input type="checkbox" name="studentRole" value="Student"/>Student &nbsp;&nbsp;
											 		<% }%>
											 		<% if(isResearcher){ %>
											 		<p align="left">&nbsp;&nbsp;<input type="checkbox" name="researcherRole" value="Researcher" checked="checked"/>Researcher
											 		<%} else{ %>
											 		<p align="left">&nbsp;&nbsp;<input type="checkbox" name="researcherRole" value="Researcher"/>Researcher
											 		<% }%>
											 		<% if(isAdmin){ %>
											 		<p align="left">&nbsp;&nbsp;<input type="checkbox" name="adminRole" value="Admin" checked="checked"/>Administrator
											 		<%} else{%>
											 		<p align="left">&nbsp;&nbsp;<input type="checkbox" name="adminRole" value="Admin"/>Administrator
											 		<%} %>
											 		<input type="hidden" name="userNetID" value="<%=request.getParameter("netID") %>" />
											 		<input type="hidden" name="userName" value="<%=request.getParameter("name") %>"/>
											 		<p><br><input type="submit" value="Update Roles" size=20  style="width: 10em; height:2em"/>
												</form> 		
									 	 </td>
										</tr>
									<%-- 	
										<tr>
											<td align="center">
											<div class="status">
												<% if(isStudent){
													session.setAttribute("currentUser", s.getNetID());
												%>
										 		<form action="profile.jsp" method="get">
										 			<input type="hidden" name="netId" value="<%=u.getNetid() %>">
													<input type="Submit" value="Act as User" size=20  style="width: 10em; height:2em"/>
												</form>
										 		<%} else if(isResearcher){
										 			session.setAttribute("currentUser", r.getNetID());
										 			%>
										 		<form action="researcher-profile.jsp" method="get">
										 			<input type="hidden" name="netId" value="<%=u.getNetid() %>">
													<input type="Submit" value="Act as User" size=20  style="width: 10em; height:2em"/>
												</form>
												<%} else if(u!= null && u.isAdmin()){
										 			session.setAttribute("currentUser", u.getNetid());
										 			%>
										 		<form action="admin-searchUser.jsp" method="get">
										 			<input type="hidden" name="netId" value="<%=u.getNetid() %>">
													<input type="Submit" value="Act as User" size=20  style="width: 10em; height:2em"/>
												</form>
												<%}%>
											</div>
											</td> --%>
										<!-- 	<td align="center">
											<div class="status">
												
											</div> 
											</td> -->
										</tr>
									</table>

									<% } else if(searchCompleted){ %>
									<h4 class="subheading"><i>No User found</i></h4>
									<%} %>
							</div>
							</li>
							</ul>							
		</div>			
</body>
</html>