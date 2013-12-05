<%@page
	import="java.util.*,model.*, org.json.*,javax.persistence.*"%>
<%	 
	 EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
	 EntityManager em = emf.createEntityManager();
	 
	 String netId = request.getParameter("netId");
	 session.setAttribute("currentUser", netId);
	 User u = UserController.findUser(em, netId);
	 Student s = StudentController.getStudentByNetID(em, netId);
	 Researcher r = ResearcherController.getResearcherByNetID(em, netId);
	 
	 int count = 0;
	 Boolean isStudent = false;
	 if(s != null){
		 isStudent = true;
		 count++;
	 }
	 
	 Boolean isResearcher = false;
	 if(r != null){
		 isResearcher = true;
		 count++;
	 }
	 
	 if(u!=null && u.isAdmin()){
		 count++;
	 }
	 
	 if(count==1){
		 if(isResearcher){
			 response.sendRedirect("researcher-profile.jsp");
		 }
		 if(isStudent){
			 response.sendRedirect("profile-nonedit.jsp");
		 }
		 if(u.isAdmin()){
			 response.sendRedirect("admin-searchUser.jsp");
		 }
	 }
	 
%>

<style>
  html, body {
  font-family: Arial, sans-serif;
  background: #fff;
  margin: 0;
  padding: 0;
  border: 0;
  position: absolute;
  height: 100%;
  min-width: 100%;
  font-size: 13px;
  color: #404040;
  }
  
  .accountchooser-card ol li {
  height: 76px;
  border-top: 1px solid #d5d5d5;
  list-style-type : none;
  } 
  .accountchooser-card ol li button,
  .accountchooser-card ol li .remove-entry {
  padding: 15px 0;
  display: block;
  width: 100%;
  height: 100%;
  outline: none;
  border: 0;
  cursor: pointer;
  text-align: left;
  background: url(images/arrow_right.png) right center no-repeat;
  background-size: 21px 21px;
  list-style-type : none;
  }
  .accountchooser-card ol li button img,
  .accountchooser-card ol li .remove-entry img {
  float: left;
  -moz-border-radius: 50%;	
  -webkit-border-radius: 50%;
  border-radius: 50%;
  height: 46px;
  width: 46px;
  list-style-type : none;
  }
  .accountchooser-card ol li button span,
  .accountchooser-card ol li .remove-entry span {
  display: block;
  margin-left: 58px;
  padding-right: 20px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  list-style-type : none;
  }
  .accountchooser-card ol li button span.account-name,
  .accountchooser-card ol li .remove-entry span.account-name {
  font-weight: bold;
  font-size: 16px;
  padding-top: 3px;
  color: #427fed;
  list-style-type : none;
  }
  
</style>

<jsp:include page="header.jsp">
	<jsp:param name="stud_or_prof" value="header" />
	<jsp:param name="sidebar_type" value="stud-profile" />
	<jsp:param name="sidebar_selected" value="view" />
	<jsp:param name="top_selected" value="profile" />
</jsp:include>

<div class="content">
 
			<table class="info" align="center" cellspacing="20" cellpadding="20">
			<tr>
			<td valign="bottom">
			 
			<div id="accountchooser-card" class="card accountchooser-card">
			<% if (u != null){ %>
			  <h2 align ="center">Choose an account</h2>
			  <% } %>
			 	<ol class="accounts " id="account-list">
			 	 <% if (u != null && u.isAdmin()){ %>
			  	<li>
			  	<form action="admin-searchUser.jsp" method="get">
			  	<button type="submit" id="choose-account-1">
			  		<img class="account-image" alt=""
			                 src="images/blank.png">
			  		<span class="account-name"><%= u.getName() %></span>
			  		<span class="account-email" id="account-email-1">
			  			Administrator
			 		 </span>
			  		</button>
			  	</form>
			  	</li>
			  <% }%>
			  <% if(isStudent){ %>
			   
			  <li><form action="profile.jsp" method="get"><button type="submit" id="choose-account-0">
			  <img class="account-image" src="images/blank.png">
			  	<span class="account-name"><%= u.getName() %></span>
			  		<span class="account-email" id="account-email-0">
			  		Student 
			  		</span>
			  		</button></form>
			  		</li>
			  <%}%>
			  <%if (isResearcher){ %>
			  <li>
			  <form action="researcher-profile.jsp" method="get">
			  <button type="submit" id="choose-account-1">
			  	<img class="account-image" alt=""
			                 src="images/blank.png">
			  		<span class="account-name"><%= u.getName() %></span>
			  		<span class="account-email" id="account-email-1">
			  		Project Lead
			  		</span>
			  		</button>
			  	</form>
			  		</li>
			  <%}%>
			  
			   <%if (u == null){ %>
					<form action="createUser.jsp">
							<h3>Are you a Student or a Project Lead?</h3>
							<p>
								<input type="radio" name="role" value="student"> Student
								<br>
								<input type="radio" name="role" value="researcher"> Project Lead
							</p>
							<p>&nbsp;&nbsp;<input type="submit" value="Submit"></p>
					</form>
			  	<% }%>
			  </ol>
			</div>
			</td>
			</tr>
			</table>
</div>
</body>
</html>

