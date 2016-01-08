<%--
	This page is uneditable profile page which can be displayed to all the users from
	the search page.
 --%>
<jsp:include page="header.jsp">
	<jsp:param name="stud_or_prof" value="stud" />
	<jsp:param name="top_selected" value="project" />
</jsp:include>
<%@page
	import="java.util.*,model.*, org.json.*,javax.persistence.*"%>
<%
EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
EntityManager em = emf.createEntityManager();

EntityTransaction tx = em.getTransaction();
tx.begin();

Project p = ProjectController.getProjectById(em, request.getParameter("pid"));
String[] attributes = {"Research Area", "Required Skills", "Project URL", "Project Description"};


 %>
<div class="content">
	<form name="profile">
		<table class="info">
			<tr>
				<td class="attr-label" colspan="3"><h2><%=p.getName()%></h2>
			</tr>
			<tr>
				<td class="attr-label" >Researcher Name(s):</td>
				<td class="attr-label"><h1><% 
			for (Researcher r : p.getResearchers()) {
		%>
			<a href = "researcher-profile-nonedit.jsp?id=<%=r.getNetID()%>"><%=r.getName()%></a><br />
		<% 
        	}
		%></h1></td>
			</tr>
			<% for(String attr: attributes){ %>
				<tr>
					<td class="attr-label"><%=attr %>:</td>
					<td class="field">
						<p class="read-only">
						<% if((attr).equals("Research Area")){ %>
						<%=p.getAreaString() %>
						<% }
						if((attr).equals("Required Skills")){ %>
						<%=p.getSkillString() %>
						<%} if((attr).equals("Project URL")){ %>
							<a href="//<%=p.getURL() %>"><%=p.getURL()%></a>
						<%} if((attr).equals("Project Description")){ %>
						<%=p.getDescription() %>
						<%}  } tx.commit(); %>
						</p>
					</td>
				</tr>
		</table>
		<FORM><INPUT Type="button" VALUE="Back" onClick="history.go(-1);return true;"></FORM>
	</form>
</div>
</body>
</html>
