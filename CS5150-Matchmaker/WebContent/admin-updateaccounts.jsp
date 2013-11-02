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
							<li class="first"><a href="admin-addaccounts.jsp">Add Account</a></li>
							<li><a href="admin-deleteaccounts.jsp">Delete Account</a></li>
							<li><a href="admin-updateaccounts.jsp" class="selected">Update Account</a></li>
						</ul>
					</div>
				</div>
				<div class="main">
					<ul class="tabrow">
						<li><a href="profile.jsp">Accounts</a></li>
						<li class="selected"><a href="student-projects.jsp">Requests</a></li>
					</ul>
					<div class="content">
					<form>
						<h1>Accounts</h1>
						<h3 class="subheading">&nbsp;Update Account</h3>
						<ul class="project-list">
							<li class="clearfix">
								<div class="accounts">
								 <table cellspacing=1 cellpadding=5 align=center border=0>
								 	<tr><td><font size ="4"><b>Search By Net ID : </b></font><input type="text" value="jd255" size=35/>
								 	<input type="Submit" value="Search" size=20  style="width: 10em; height:2em"/></td>
									</tr>
									<tr>
									<td><table cellspacing=5 cellpadding=5 border=0>
									<tr>
										<td valign="top"><img src="blank.png"/></td>
										<td>
								 		<table cellspacing=5 cellpadding=5 align=center border=0>
								 		<tr><th><b><font size="4">Name :</font></b></th>
								 		<td><input type="text" value="John Doe" size=35/></td>
										</tr>
										<tr><th><b><font size="4">Net ID :</font></b></th>
									 		<td><input type="text" value="jd255" size=35/></td>
										</tr>
										<tr><th><b><font size="4">GPA :</font></b></th>
									 		<td><input type="text" value="3.9" size=35/></td>
										</tr>
										<tr>
										 <th><b><font size="4">Role :</font></b></th>
										 <td>
									 		<input type="radio" name="role" value="Student" checked="checked"/>Student &nbsp;&nbsp;
									 		<input type="radio" name="role" value="Researcher"/>Researcher
									 	</td>
										</tr>
										<tr>
											<td colspan="2" align="center">
											<div class="status">
												<p class="deleted">Update</p>
											</div>
										</td>
										</tr>
									</table>
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