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
					<form action="http://localhost/CS5150-Matchmaker/admin-act-as-edit-researcher-profile.jsp">
						<h1>Accounts</h1>
						<h3 class="subheading">&nbsp;Search User</h3>
						<ul class="project-list">
							<li class="clearfix">
								<div class="accounts">
								 
								 	<font size ="4"><b>Search By Net ID : </b></font><input type="text" value="tm123" size=35/>
								 	<input type="Submit" value="Search" size=20  style="width: 10em; height:2em"/></td>
									<br><br>
									<table cellspacing=5 cellpadding=10 border=0>
									<tr>
										<td valign="top"><img src="blank.png" height=150 width=150/></td>
										<td>
								 		<table cellspacing=10 cellpadding=10 align=center style="b">
								 		<tr><td><h3>Name:</h3></td>
								 		<td><input type="text" value="Tom Magrino" size=35/></td>
										</tr>
										<tr><td><h3>NetID:</h3></td>
									 		<td><input type="text" value="tm123" size=35/></td>
										</tr>
									    <tr><td><h3>Role:</h3></td>
										 <td>
									 		<input type="radio" name="role" value="Student"/>Student &nbsp;&nbsp;
									 		<input type="radio" name="role" value="Researcher"  checked="checked"/>Researcher
									 	</td>
										</tr>
										<tr>
											<td colspan="2" align="center">
											<div class="status">
												<input type="Submit" value="Act as User" size=20  style="width: 10em; height:2em"/>
											</div>
										</td>
										</tr>
									</table>
									</td>
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