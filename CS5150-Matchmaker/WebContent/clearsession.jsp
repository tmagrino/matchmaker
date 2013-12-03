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
<title>Clear Session</title>
</head>
<!--[if IE 7]>         <body class="lt-ie10 lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <body class="lt-ie10 lt-ie9"> <![endif]-->
<!--[if IE 9]>         <body class="lt-ie10"> <![endif]-->
<!--[if gt IE 9]><!-->
<body>
<%
session.invalidate();
response.sendRedirect("login.jsp");
%>
</body>
</html>
