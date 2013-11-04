<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/jPages.css">
	<link rel="stylesheet" type="text/css" href="css/autoSuggest.css">
	<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
	<script src="js/script.js" type="text/javascript"></script>
	<script src="js/jPages.min.js" type="text/javascript"></script>
	<script src="js/jquery.autoSuggest.minified.js" type="text/javascript"></script>
	<title>Matchmaker - Student Profile</title>
</head>
    <!--[if IE 7]>         <body class="lt-ie10 lt-ie9 lt-ie8"> <![endif]-->
    <!--[if IE 8]>         <body class="lt-ie10 lt-ie9"> <![endif]-->
    <!--[if IE 9]>         <body class="lt-ie10"> <![endif]-->
    <!--[if gt IE 9]><!--> <body> <!--<![endif]-->
	<div id="top-bar" class="clearfix">
		<ul class="login-nav">
			<li>Welcome, Bob Smith</li>
			<li class="login-link"><a href="#">sign out</a></li>
		</ul>
	</div>
	<div class="page">
		<div class="wrapper">
			<div id="container" class="clearfix">
				<div class="sidebar">
					<p class="notifications">Notifications</p>
					<div id="sidenav">
						<ul>
							<jsp:include page="sidebar.jsp">
								<jsp:param name="sidebar_type" value="<%= request.getParameter(\"sidebar_type\") %>" />
							    <jsp:param name="sidebar_selected" value="<%= request.getParameter(\"sidebar_selected\") %>" />
							</jsp:include>
						</ul>
					</div>
				</div>
				<div class="main">
					<ul class="tabrow">
						<% if(request.getParameter("stud_or_prof").equals("stud")){ %>
							<li <% if(request.getParameter("top_selected").equals("profile")){ %>class="selected"<% } %>>
								<a href="profile.jsp">My Profile</a>
							</li>
							<li <% if(request.getParameter("top_selected").equals("project")){ %>class="selected"<% } %>>
								<a href="student-projects.jsp">My Projects</a>
							</li>
						<% } else if(request.getParameter("stud_or_prof").equals("prof")){ %>
							<li <% if(request.getParameter("top_selected").equals("profile")){ %>class="selected"<% } %>>
								<a href="prof-profile.jsp">My Profile</a>
							</li>
							<li <% if(request.getParameter("top_selected").equals("project")){ %>class="selected"<% } %>>
								<a href="student-projects.jsp">My Projects</a>
							</li>
							<li <% if(request.getParameter("top_selected").equals("students")){ %>class="selected"<% } %>>
								<a href="invite-students.jsp">Students</a>
							</li>
						<% } %>
					</ul>