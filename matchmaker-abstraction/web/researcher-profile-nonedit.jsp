<%--
	This is uneditable researcher profile page that can be seen by the student
 --%>
<jsp:include page="header.jsp">
    <jsp:param name="stud_or_prof" value="stud"/>
    <jsp:param name="top_selected" value="project"/>
</jsp:include>
<%@page import="java.util.*,model.*, org.json.*,javax.persistence.*"%>

<%	EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
	EntityManager em = emf.createEntityManager();

	EntityTransaction tx = em.getTransaction();
	tx.begin();
	
	Student s = StudentController.getStudentByNetID(em,(String) session.getAttribute("currentUser"));
	
	Researcher r = ResearcherController.getResearcherByNetID(em,(String)request.getParameter("id"));
	
	String[] attributes = {"Email", "URL", "Department", "Research Area"};
%>

<div class="content">
	<!-- <h1>My Profile</h1> -->
	<div class="photo-info clearfix">
		<form name="profile" action="#" method="GET">
			<table class="info">
				<tr>
					<td class="attr-label" colspan="3">
						<h2><%=r.getName()%></h2>
					</td>
				</tr>
				<% for(String attr: attributes){ %>
				<tr>
					<td class="attr-label"><%=attr %>:</td>
					<td class="field">
						<p class="read-only <%= r.getAttribute(attr) == "" ? "hidden" : "" %>">
							<% if (attr == "URL"){%>
							<a href = "http://<%=r.getWebpage() %>"><%=r.getWebpage()%></a>
							<%}else{ %>
							<%=r.getAttribute(attr) %>
							<%} %>
						</p>
						<p class="editable <%= r.getAttribute(attr) != "" ? "hidden" : "" %>">
							<input name="<%=attr.replaceAll(" ", "_").toLowerCase() %>"
								value="<%=r.getAttribute(attr) %>" type="text" />
						</p>
						<p class="other hidden">
							<input name="<%=attr.replaceAll(" ", "_").toLowerCase()+"_other" %>" type="text" />
						</p>
					</td>
					
				</tr>
				<% } tx.commit(); %>
			</table>
			<FORM><INPUT Type="button" VALUE="Back" onClick="history.go(-1);return true;"></FORM>
		</form>
	</div>
</div>
</div>
</div>
</div>
</div>
</body>
</html>
