<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,model.*,model.Student, model.StudentController,model.Researcher,model.ResearcherController,javax.persistence.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/jPages.css">
<link rel="stylesheet" type="text/css" href="css/autoSuggest.css">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.js"></script>
<script src="js/script.js" type="text/javascript"></script>
<script src="js/jquery.tablesorter.min.js" type="text/javascript"></script>
<script src="js/jquery.tablesorter.pager.js" type="text/javascript"></script>
<script src="js/jquery.autoSuggest.js" type="text/javascript"></script>
<title>Matchmaker - Student Profile</title>
</head>
<!--[if IE 7]>         <body class="lt-ie10 lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <body class="lt-ie10 lt-ie9"> <![endif]-->
<!--[if IE 9]>         <body class="lt-ie10"> <![endif]-->
<!--[if gt IE 9]><!-->
<body>
	<!--<![endif]-->
	<div id="top-bar" class="clearfix">
		<ul class="login-nav">
		<% 
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
		 	EntityManager em = emf.createEntityManager();
			if(request.getParameter("stud_or_prof").equals("stud")){ 
				Student s = StudentController.getStudentByNetID(em,"lr437");%>

				<li>Welcome, <%=s.getName() %></li>

			<% } else if(request.getParameter("stud_or_prof").equals("researcher")){ %>
				<%Researcher r = ResearcherController.getResearcherByNetID(em,"tm123"); %>

				<li>Welcome, <%=r.getName() %></li>

			<% } else if(request.getParameter("stud_or_prof").equals("header")){
				User u = UserController.findUser(em, "lr437");
			%>
	
				<li>Welcome, <%=u.getName() %></li>
	
			<%} %>
			<li class="login-link"><a href="#">sign out</a></li>

		</ul>
	</div>
	<% if(!request.getParameter("stud_or_prof").equals("header")){ %>
	<div class="page">
		<div class="wrapper">
			<div id="container">
				<div class="main">
					<p class="notifications">Notifications</p>
					<div class="nav">
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
							<% if(request.getParameter("top_selected").equals("project")){ %>
							class="selected" <% } %>><a href="researcher-projects.jsp">My
								Projects</a></li>
						<li
							<% if(request.getParameter("top_selected").equals("students")){ %>
							class="selected" <% } %>><a href="project-applicants.jsp">Students</a>
						</li>
						<% } %>
					</ul>
					</div>
		<%} %>	
