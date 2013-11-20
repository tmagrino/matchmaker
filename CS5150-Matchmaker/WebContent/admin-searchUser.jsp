
<%@page
	import="java.util.*,model.*, org.json.*,javax.persistence.*"%>

<jsp:include page="header.jsp">
	<jsp:param name="stud_or_prof" value="stud" />
	<jsp:param name="sidebar_type" value="stud-profile" />
	<jsp:param name="sidebar_selected" value="view" />
	<jsp:param name="top_selected" value="profile" />
</jsp:include>
<%
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
	EntityManager em = emf.createEntityManager();
	
	
	String netID = request.getParameter("netID");
	
	Student s = null;
	User u = null;
	Researcher r = null;
	System.out.println(netID);
	
	if(netID != null){
		u = UserController.findUser(em, netID);
		s = StudentController.getStudentByNetID(em, netID);
		r = ResearcherController.getResearcherByNetID(em, netID);
	}
	
	Boolean isStudent = false;
	if(s!= null){
		isStudent = true;
	}
	
	Boolean isResearcher = false;
	if(r != null){
		isResearcher = true;
	}
%>
		
			<div class="content">
						<h1>Accounts</h1>
						<h3 class="subheading">&nbsp;Search User</h3>
						<ul class="project-list">
							<li class="clearfix">
								<div class="accounts">
								 	
								 	<form action="admin-searchUser.jsp" method="Get">
								 	<font size ="4"><b>Search By Net ID : </b></font>
								 	<%if(netID != null) {%>
								 	<input type="text" name="netID" value="<%=netID%>" size=35/>
								 	<%} else{%>
								 	<input type="text" name="netID" value="" size=35/>
								 	<% }%>
								 	<input type="Submit" value="Search" size=20  style="width: 10em; height:2em"/>
								 	</form>
									<br><br>
									<% if(netID != null) {%>
									<table cellspacing=5 cellpadding=10 border=1>
									<tr>
										<td valign="top"><img src="images/blank.png" height=150 width=150/></td>
										<td>
								 		<table cellspacing=10 cellpadding=10 align=center border="0">
								 		<tr><td><h3>Name:</h3></td>
								 		<td><input type="text" value="<%=s.getName()%>" size=35/></td>
										</tr>
										<tr><td><h3>NetID:</h3></td>
									 		<td><input type="text" value="<%=s.getNetID() %>" size=35/></td>
										</tr>
									    <tr><td><h3>Role:</h3></td>
										 <td align="left">
											 	<% if(isStudent){%>
										 		<p align="left">&nbsp;&nbsp;<input type="checkbox" name="role" value="Student" checked="checked"/>Student &nbsp;&nbsp;</li>
										 		<% } else {%>
										 		<p align="left">&nbsp;&nbsp;<input type="checkbox" name="role" value="Student"/>Student &nbsp;&nbsp;</li>
										 		<% }%>
										 		<% if(isResearcher){ %>
										 		<p align="left">&nbsp;&nbsp;<input type="checkbox" name="role" value="Researcher" checked="checked"/>Researcher</li>
										 		<%} else{ %>
										 		<p align="left">&nbsp;&nbsp;<input type="checkbox" name="role" value="Researcher"/>Researcher</li>
										 		<% }%>
										 		<p align="left">&nbsp;&nbsp;<input type="checkbox" name="role" value="Admin"/>Administrator</li>
									 	</td>
										</tr>
										<tr>
											<td>
											<div class="status">
												<form>
													<input type="Submit" value="Act as User" size=20  style="width: 10em; height:2em"/>
												</form>
											</div>
											</td>
											<td>
											<div class="status">
												<form action="admin-update-role.jsp" method="Get">
													<input type="hidden" name="hiddenNetID" value="<%=netID%>">
													<input type="Submit" value="Update Role" size=20  style="width: 10em; height:2em"/>
												</form>
											</div>
											</td>
										</tr>
									</table>
									</td>
									</tr>
									</table>
									<%} %>
							</div>
							</li>
							</ul>							
		</div>			
</body>
</html>