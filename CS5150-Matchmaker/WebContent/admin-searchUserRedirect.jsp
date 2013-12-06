
<%@page
	import="java.util.*,model.*, org.json.*,javax.persistence.*"%>
<%
session.setAttribute("adminUser", session.getAttribute("currentUser"));
response.sendRedirect("admin-searchUser.jsp");
%>