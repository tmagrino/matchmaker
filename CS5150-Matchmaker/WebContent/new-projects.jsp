<jsp:include page="header.jsp">
    <jsp:param name="stud_or_prof" value="stud"/>
    <jsp:param name="sidebar_type" value="stud-project"/>
    <jsp:param name="sidebar_selected" value="new"/>
    <jsp:param name="top_selected" value="project"/>
</jsp:include>
					<div class="content">
						<%@page import="java.util.*,model.Student, model.*, org.json.JSONObject,javax.persistence.*"%>
						<%
						EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
					 	EntityManager em = emf.createEntityManager();
					 	JSONObject jsonMajor = ListController.getItemJson(em,ItemFactory.MAJOR);
				        JSONObject jsonSkills = ListController.getItemJson(em,ItemFactory.SKILL);
				        JSONObject jsonInterest = ListController.getItemJson(em,ItemFactory.INTEREST);
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
							<% for(int i=1;i<=500;i+=2)
							{
							%>
								<tr>
									<td><a href="#">Apply</a></td>
									<td>Project Name <%=i %></td>
									<td>Andrew Myers</td>
									<td><a href="#">Link to Project Webpage <%=i %></a></td>
									<td></td>
									<td>Programming Languages</td>
									<td>Java</td>
								</tr>
								<tr>
									<td><a href="#">Apply</a></td>
									<td>Project Name <%=i+1 %></td>
									<td>Tom Magrino</td>
									<td></td>
									<td>This is a description of Project <%=i+1 %>.  This is a...</td>
									<td>Systems</td>
									<td>Python</td>
								</tr>
							<% } %>
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