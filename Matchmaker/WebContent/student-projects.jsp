<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/autoSuggest.css">
	<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
	<script src="js/script.js" type="text/javascript"></script>
	<script src="js/jquery.autoSuggest.minified.js" type="text/javascript"></script>
	<title>Matchmaker - Student Profile</title>
</head>
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
							<li class="first"><a class="selected" href="student-projects.jsp">Current Projects</a></li>
							<li><a href="project-invitations.jsp">Project Invitations</a></li>
							<li><a href="new-projects.jsp">Select New Projects</a></li>
							<li class="related">Related</li>
							<li><a href="#">How to make the most of your research experience!</a></li>
						</ul>
					</div>
				</div>
				<div class="main">
					<ul class="tabrow">
						<li><a href="profile.jsp">My Profile</a></li>
						<li class="selected"><a href="student-projects.jsp">My Projects</a></li>
						<li><a href="#">Researchers</a></li>
					</ul>
					<div class="content">
						<h1>My Projects</h1>
						<ul class="project-list">
							<li class="clearfix">
								<div class="status">
									<p class="rejected">Rejected</p>
								</div>
								<div class="project-info">
									<h3>Project Name</h3>
									<p><a href="#">Link to Project Webpage</a></p>
									<p>Researcher Name</p>
								</div>							
							</li>
							<li class="clearfix">
								<div class="status">
									<p class="accepted">Accepted</p>
								</div>
								<div class="project-info">
									<h3>Project Name</h3>
									<p><a href="#">Link to Project Webpage</a></p>
									<p>Researcher Name</p>
								</div>							
							</li>
							<li class="clearfix">
								<div class="status">
									<p class="pending">Pending</p>
									<a>Cancel</a>
								</div>
								<div class="project-info">
									<h3>Project Name</h3>
									<p><a href="#">Link to Project Webpage</a></p>
									<p>Researcher Name</p>
								</div>							
							</li>
						</ul>
						
					</div>				
				</div>
			</div>
		</div>
	</div>
</body>
</html>