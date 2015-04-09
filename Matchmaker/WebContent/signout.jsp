<%--
	This is logout page when clicked on signout.
	The session is expired only when you close the browser - CUWebAuth functionality
 --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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

<title>Matchmaker for Student Research</title>
</head>
<body>

<div id="top-bar" class="clearfix">
<center><font color="#ffffff"><span class="navlink_course" id="navlink_course_overview">

<b>Current Login: <%
if(session.getAttribute("adminUser") != null){%>
	<%=session.getAttribute("adminUser")%>
<%}else{%>
	<%=session.getAttribute("currentUser")%>	
<%}%></b>
</span></font></center>
</div>

<center><b>
Please exit your browser and sign out of SideCar to clear your credentials.<p>If you authenticate using CUWebLogin, please be sure to read the guidelines<br> on how to <a href='http://www.cit.cornell.edu/services/identity/cuweblogin/more.html'>clear your CUWebLogin credentials</a>.</p>
</b></center>

</body>
</html>











