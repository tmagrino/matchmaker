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
							<li class="first"><a href="prof-projects.jsp">Current Projects</a></li>
							<li><a class="selected" href="edit-project.jsp">Project A</a></li>
							<li><a href="edit-project.jsp">Project B</a></li>
							<li><a href="create-project.jsp">Create New Projects</a></li>
						</ul>
					</div>
				</div>
				<div class="main">
					<ul class="tabrow">
						<li><a href="prof-profile.jsp">My Profile</a></li>
						<li class="selected"><a href="prof-projects.jsp">My Projects</a></li>
						<li><a href="prof-students.jsp">Students</a></li>
					</ul>
					<div class="content">
						<h1>My Projects</h1>
						<form name="profile-form" action="/">
							<div class="photo-info clearfix">
								<div class="info">
									<h2>Bob Smith</h2>
									<p class="required"><label for="title">Title</label><input name="title" value="Project A" type="text"></input></p>
									<p class="required"><label for="dept">Department</label><input name="dept" type="text"></input></p>
									<p><label for="specialization">Specialization</label><input name="specialization" type="text"></input></p>
									<p><label for="req-skills">Required Skills</label><input name="req-skills" type="text"></input></p>
									<p><label for="url">Project URL</label><input name="url" type="text"></input></p>
									<p><label for="description">Project Description</label><textarea name="description"></textarea></p>
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