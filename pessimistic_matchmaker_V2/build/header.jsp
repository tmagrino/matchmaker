<%--
	This page is reusable header page used in all of the jsps.
	Depending upon the roles of the person, the information is displayed in the header page.
 --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,model.*,model.Student, model.StudentController,model.Researcher,model.ResearcherController,javax.persistence.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/autoSuggest.css">
<link rel="stylesheet" type="text/css" href="//code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="css/jquery.dataTables.css" />
<script src="//code.jquery.com/jquery-1.9.1.js"></script>
<script src="//code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script src="js/script.js" type="text/javascript"></script>
<script src="js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="js/jquery.autoSuggest.js" type="text/javascript"></script>
<title>Matchmaker - Profile</title>
</head>
<!--[if IE 7]>         <body class="lt-ie10 lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <body class="lt-ie10 lt-ie9"> <![endif]-->
<!--[if IE 9]>         <body class="lt-ie10"> <![endif]-->
<!--[if gt IE 9]><!-->
<body>
	<!--<![endif]-->
	
	<div id="top-bar" class="clearfix">
		
		<ul class="login-nav">
		<!-- This a Header page!! -->
		<% 
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
		 	EntityManager em = emf.createEntityManager();

		 	EntityTransaction tx = em.getTransaction();
	   	 	tx.begin();
		 	String currentUser = (String) session.getAttribute("currentUser");
		 	//System.out.println("---------------Set by currentUser-------------------");
		 	Integer numRoles = (Integer) session.getAttribute("numberOfRoles");
		 	if(currentUser == null){
		 		currentUser = request.getParameter("netId");
		 		//System.out.println("---------------Set by currentUser-------------------");
		 		//session.setAttribute("currentUser", currentUser);
		 	}
		 	
		 	String stud_prof = request.getParameter("stud_or_prof");
		 	//System.out.println("---------------Set by adminUser-------------------"+session.getAttribute("adminUser"));
		 	if(session.getAttribute("adminUser") != null){
		 		//System.out.println("---------------Set by adminUser-------------------"+session.getAttribute("adminUser"));
		 		currentUser = (String) session.getAttribute("adminUser");
		 		stud_prof = "admin";
		 	}
		 	//System.out.println("currentUser : "+currentUser);
			if(stud_prof.equals("stud")){ 
				Student s = StudentController.getStudentByNetID(em,currentUser);
				if(s != null){%>
					<li>Welcome, <%=s.getName() %>
					<br>
					<% if(numRoles > 1) { %>
						<li class="change-role"><a href="select-role.jsp">Change Role</a></li>
					<% } %>
					<font size="2"><%=s.getNetID()%>, Student</font></li>
				<%}
			 } else if(stud_prof.equals("researcher")){ %>
				<%Researcher r = ResearcherController.getResearcherByNetID(em,currentUser); %>
				
				<li>Welcome, <%=r.getName()%>
				<br>
				<% if(numRoles > 1) { %>
						<li class="change-role"><a href="select-role.jsp">Change Role</a></li>
				<% } %>
				<font size="2"> <%=r.getNetID()%>, Project Lead</font></li>
				
			<% } else if(stud_prof.equals("admin")){
				User u = UserController.findUser(em, currentUser);
				if(u != null){
			%>
				<li class="acting-as">
				<% if(session.getAttribute("adminUser")!= null){ %>
					Acting as User : <%
						if(session.getAttribute("page").equals("adminSearch")){
							session.setAttribute("page","");	
						%> None <%}else{
						User usr = UserController.findUser(em,(String)session.getAttribute("currentUser"));%>
					<%=usr.getName()%> (<%=usr.getNetid() %>) 
					<br>
					<a href="admin-searchUser.jsp">Exit</a>
				<%}
					} else{%>
				<%} %>
				</li>
				<li>Welcome, <%=u.getName() %></li>
				<br>
				<% if(numRoles > 1) { %>
						<li class="change-role"><a href="select-role.jsp">Change Role</a></li>
				<% } %>
				<font size="2"> <%=u.getNetid()%>, Administrator</font></li>
			<%}

			} else if(stud_prof.equals("header")){
				User u = UserController.findUser(em, currentUser);
				if(u != null){
			%>
				<li>Welcome, <%=u.getName() %></li>
				<% }
				else{ %>
				<li>Welcome, New User</li>
				<% }
			  } 
			  
			  
			  %>
				<li class="login-link"><a href="signout.jsp">sign out</a></li>
		</ul>
	</div>
	<% if(! request.getParameter("stud_or_prof").equals("header")){ %>
	<div class="page">
		<div class="wrapper">
			<div id="container">
				<div class="main">
					<div class="nav clearfix">
						<ul class="tabrow">
						<% if(request.getParameter("stud_or_prof").equals("stud")){ %>
						<li
							<% if(request.getParameter("top_selected").equals("profile")){ %>
							class="selected" <% } %>><a href="profile.jsp">My
								Profile</a></li>
						<li
							<% if(request.getParameter("top_selected").equals("project")){ %>
							class="selected" <% } %>><a href="student-projects.jsp">Projects</a>
						</li>
						<% } else if(request.getParameter("stud_or_prof").equals("researcher")){ %>
						<li
							<% if(request.getParameter("top_selected").equals("profile")){ %>
							class="selected" <% } %>><a href="researcher-profile.jsp">My
								Profile</a></li>
						<li
							<% if(request.getParameter("top_selected").equals("students")){ %>
							class="selected" <% } %>><a href="project-applications.jsp">Students</a>
						</li>
						<li
							<% if(request.getParameter("top_selected").equals("project")){ %>
							class="selected" <% } %>><a href="researcher-projects.jsp">My
								Projects</a></li>
						<% } else if(request.getParameter("stud_or_prof").equals("admin")){ %>
						<li
							<% if(request.getParameter("top_selected").equals("profile")){ %>
							class="selected" <% } %>><a href="admin-searchUser.jsp">Search
								User</a></li>
								<!-- Edit Fields -->
						<li
							<% if(request.getParameter("top_selected").equals("project")){ %>
							class="selected" <% } %>><a href="latestAdditions.jsp">Vocabulary</a></li>
						<% } %>

					</ul>
					</div>
		<% } tx.commit(); %>	
