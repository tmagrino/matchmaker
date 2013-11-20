<jsp:include page="header.jsp">
	<jsp:param name="stud_or_prof" value="stud" />
	<jsp:param name="sidebar_type" value="stud-profile" />
	<jsp:param name="sidebar_selected" value="view" />
	<jsp:param name="top_selected" value="profile" />
</jsp:include>
<%
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
	EntityManager em = emf.createEntityManager();
	
	String netId = request.getParameter("hiddenNetID");
	Student s = StudentController.getStudentByNetID(em,netId);
	Researcher r = ResearcherController.getResearcherByNetID(em, netId);
	
	Boolean isStudent = false;
	if(s!= null){
		isStudent = true;
	}
	
	Boolean isResearcher = false;
	if(r != null){
		isResearcher = true;
	}
%>
<%@page
	import="java.util.*,model.*, org.json.*,javax.persistence.*"%>
		
	<div class="content">
					<form>
						<h1>Accounts</h1>
						<h3 class="subheading">Update Role</h3><h4>&nbsp;&nbsp;Account Information</h4>
						<ul class="project-list">
							<li class="clearfix">
								<div class="accounts">
								 <table cellspacing=5 cellpadding=5 align=center border=0>
									<tr>
										<% if(isStudent){%>
										<td><h3>Name :</h3></td>
								 		<td><%=s.getName() %></td>
								 		<%} %>
								 		<% if(isResearcher){%>
								 		<td><h3>Name :</h3></td>
								 		<td><%=r.getName() %></td>
								 		<% } %>
									</tr>
									<tr>
										<% if(isStudent){%>
										<td><h3>NetID :</h3></td>
								 		<td><%=s.getNetID() %></td>
								 		<%} %>
								 		<% if(isResearcher){%>
								 		<td><h3>NetID :</h3></td>
								 		<td><%=r.getNetID() %></td>
								 		<% } %>
									</tr>
									<tr>
									 <td><h3>Role :</h3></td>
									 <td>
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
										<td colspan=2 align="center"><input type="button" value="Save Changes" size=20  style="width: 10em; height:2em" onClick="alert('Role(s) have been updated!')"/></td>
									</tr>
								 </table>
								</div>							
							</li>
						</ul>
					</form>
					</div>				
				</div>
			</div>
		</div>
	</div>
</body>
</html>