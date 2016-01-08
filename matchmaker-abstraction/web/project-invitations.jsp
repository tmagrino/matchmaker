<%--
	This page contains all the invitations to the projects.
--%>
<jsp:include page="header.jsp">
    <jsp:param name="stud_or_prof" value="stud"/>
    <jsp:param name="sidebar_type" value="stud-project"/>
    <jsp:param name="sidebar_selected" value="invitation"/>
    <jsp:param name="top_selected" value="project"/>
</jsp:include>
					<div class="content">
						<%@page import="java.util.*,model.Student, model.*, org.json.JSONObject,javax.persistence.*"%>
						<%
							EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
													 	EntityManager em = emf.createEntityManager();
													 	EntityTransaction tx = em.getTransaction();
														tx.begin();
													 	JSONObject jsonMajor = FieldValueController.getItemJson(em,FieldFactory.MAJOR);
												        JSONObject jsonSkills = FieldValueController.getItemJson(em,FieldFactory.SKILL);
												        JSONObject jsonInterest = FieldValueController.getItemJson(em,FieldFactory.INTEREST);

												        tx.commit();
						%>
				        <script type="text/javascript">
				        	var majorData = <%= jsonMajor %>;
				        	var skillsData = <%= jsonSkills %>;
				        	var interestData = <%= jsonInterest %>;
				        </script>
						<h1>Projects</h1>
						<form name="filter-list" id="filter-list">
							<input type="submit" value="Filter"/>
							<table class="project-list">
								<jsp:include page="proj-filters.jsp"/>
								<tr>
									<td><a href="#">Apply</a></td>
									<td>Project Name</td>
									<td>Andrew Myers</td>
									<td><a href="#">Link to Project Webpage</a></td>
									<td></td>
									<td>Programming Languages</td>
									<td>Java</td>
								</tr>
								<tr>
									<td><a href="#">Apply</a></td>
									<td>Project Name</td>
									<td>Tom Magrino</td>
									<td></td>
									<td>This is a description of Project 2.  This is a...</td>
									<td>Systems</td>
									<td>Python</td>
								</tr>
							</tbody>
							</table>
						</form>
						<jsp:include page="pager.jsp"/>
					</div>				
				</div>
			</div>
		</div>
	</div>
</body>
</html>
