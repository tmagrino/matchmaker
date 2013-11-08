<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,model.Student, model.StudentController"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/jPages.css">
	<link rel="stylesheet" type="text/css" href="css/autoSuggest.css">
	<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.js"></script>
	<script src="js/script.js" type="text/javascript"></script>
	<script src="js/jquery.tablesorter.min.js" type="text/javascript"></script>
	<script src="js/jquery.tablesorter.pager.js" type="text/javascript"></script>
	<script src="js/jquery.autoSuggest.js" type="text/javascript"></script>
	<title>Matchmaker - Administrator Profile</title>
</head>
<%@page import="java.util.*,model.*"%>
			<%Researcher r = ResearcherController.getResearcherByNetID("tm123"); %>
    <!--[if IE 7]>         <body class="lt-ie10 lt-ie9 lt-ie8"> <![endif]-->
    <!--[if IE 8]>         <body class="lt-ie10 lt-ie9"> <![endif]-->
    <!--[if IE 9]>         <body class="lt-ie10"> <![endif]-->
    <!--[if gt IE 9]><!--> <body> <!--<![endif]-->
	<div id="top-bar" class="clearfix">
		<ul class="login-nav">
			<li>Welcome, Jane Doe</li>
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
							<li class="first"><a href="admin-addaccounts.jsp">Update Role</a></li>
							<li><a href="admin-deleteaccounts.jsp">My Profile</a></li>
							<li><a href="admin-deleteaccounts.jsp">My Projects</a></li>
							<li><a href=""><u>Project A</u></a>
							<li><a href=""><u>Project B</u></a>
						</ul>
					</div>
				
	

					
				</div>
				<div class="main">
					<ul class="tabrow">
							<li>
								<a href="profile.jsp">My Profile</a>
							</li>
							<li>
								<a href="student-projects.jsp">My Projects</a>
							</li>
							<li class="selected">
								<a href="student-projects.jsp">User Profile</a>
							</li>
							<li>
								<a href="profile.jsp">User Projects</a>
							</li>
							<li>
								<a href="admin-updateaccounts.jsp">Change User</a>
							</li>
					</ul>	

					<div class="content">
						<h1>Administrator Profile :  User tm123</h1>
						<form name="profile-form" action="/">
							<div class="photo-info clearfix">
								<img class="avatar" src="avatar-male.jpg" alt="avatar"/>
								<div class="info">
<!-- <<<<<<< HEAD

									<h2>Bob Smith</h2>
									<p class="required"><label for="email">Email</label><input name="email" value="bcs575@cornell.edu" type="text"></input></p>
									<p class="required"><label for="url">URL</label><input name="url" value="www.cs.cornell.edu/bsmith" type="text"></input></p>
									<p class="required"><label for="dept">Department</label><input name="dept" type="text"></input></p>
									<p><label for="specialization">Research Area</label><input name="specialization" type="text"></input></p>
======= -->

									<h2><%=r.getName() %></h2>
									<p class="required"><label for="email">Email</label><input name="email" value="<%=r.getEmail() %>" type="text"></input></p>
									<p class="required"><label for="url">URL</label><input name="url" value="<%=r.getWebpage() %>" type="text"></input></p>
									<p class="required"><label for="dept">Department</label><input name="dept" value="<%=r.getDepartment() %>" type="text"></input></p>
									<p><label for="specialization">Research Area</label><input name="specialization" value = "<%=r.getResearchArea() %>" type="text"></input></p>
<!-- >>>>>>> branch 'master' of https://github.com/jkahuja/CS5150-Matchmaker.git -->
								</div>
							</div>
							<input type="submit" value="Save Changes"></input>
						</form>
					</div>				
				</div>
			</div>
		</div>
	</div>
</body>
</html>
