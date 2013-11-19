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
	<title>Matchmaker - Update Role</title>
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
							<li class="first"><a href="admin-addaccounts.jsp" class="selected">Update Role</a></li>
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
							<li>
								<a href="admin-act-as-edit-researcher-profile.jsp">User Profile</a>
							</li>
							<li>
								<a href="researcher-projects.jsp">User Projects</a>
							</li>
							<li  class="selected">
								<a href="admin-updateaccounts.jsp">Change User</a>
							</li>
					</ul>
				
					<div class="content">
					<form>
						<h1>Accounts</h1>
						<h3 class="subheading">&nbsp;Update Role</h3>
						<ul class="project-list">
							<li class="clearfix">
								<div class="accounts">
								 <table cellspacing=5 cellpadding=5 align=center border=0>
								 	<tr><td><h3>Name :</h3></td>
								 		<td><input type="text" value="Tom Magrino" size=35/></td>
									</tr>
									<tr><td><h3>NetID :</h3></td>
								 		<td><input type="text" value="tm123" size=35/></td>
									</tr>
									<tr>
									 <td><h3>Role :</h3></td>
									 <td>
								 		<p><input type="checkbox" name="role" value="Student"/>Student &nbsp;&nbsp;</li>
								 		<p><input type="checkbox" name="role" value="Researcher" checked="checked"/>Researcher</li>
								 		<p><input type="checkbox" name="role" value="Admin"/>Administrator</li>
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