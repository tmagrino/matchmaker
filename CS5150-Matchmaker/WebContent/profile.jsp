<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="java.util.*,model.Student, model.GetStudentInfo"%>
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
		<%Student s = GetStudentInfo.maxGPA(); %>
			<li>Welcome, <%=s.getName() %></li>
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
							<li class="first"><a class="selected" href="profile.jsp">View Profile</a></li>
							<li><a href="edit-profile.jsp">Edit Profile</a></li>
							<li class="related">Related</li>
							<li class="tips"><a href="#">Tips on creating a good
									profile</a></li>
						</ul>
					</div>
				</div>
				<div class="main">
					<ul class="tabrow">
						<li class="selected"><a href="profile.jsp">My Profile</a></li>
						<li><a href="student-projects.jsp">My Projects</a></li>
					</ul>
					<div class="content">
						<h1>My Profile</h1>
						<h2 class="subheading">General Information</h2>
						<div class="photo-info clearfix">
							<img class="avatar" src="avatar-female.jpg" alt="avatar"/>
							<div class="info">
							
								<h2><%=s.getName() %></h2>
								<p>Email: jd322@cornell.edu</p>
								<p>Major: Computer Science</p>
								<p>Minor: Music</p>
								<p>Year: <%=s.getYear() %></p>
								<p>School: College of Engineering</p>
							</div>
						</div>
						<h2 class="subheading">Application Information</h2>	
						<div class="application-info">
							<p>GPA: <%=s.getGpa() %></p>
							<p>Skills: Java, C++, Python</p>
							<p>Research Interests: Machine Learning</p>
						</div>
					</div>				
				</div>
			</div>
		</div>
	</div>
</body>
</html>