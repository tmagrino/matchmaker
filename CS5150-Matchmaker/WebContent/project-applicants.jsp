<jsp:include page="header.jsp">
    <jsp:param name="stud_or_prof" value="researcher"/>
    <jsp:param name="top_selected" value="students"/>
</jsp:include>
<%@page import="java.util.*,model.Student, model.*, org.json.JSONObject,javax.persistence.*"%>
<div class="content">
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
	<form name="filter-list" id="filter-list" class="clearfix">
		<h1>Search Students</h1>
		<div class="search-container">
			<input type="text" placeholder="Search"/>
			<input type="submit" value="Filter"/>
		</div>
	</form>
	<table class="project-list">
		<jsp:include page="stud-filters.jsp"/>
		<%
			List<Student> studentList = new ArrayList<Student>();
																
			if (request.getParameter("filter-name") == null || request.getParameter("filter-gpa") == null){
				studentList = StudentController.getStudentByFilter(em,
						"", "", "", "","","");
			}
			//List<Student> studentList = StudentController.getAllStudents();
			else{studentList = StudentController.getStudentByFilter(em,
					request.getParameter("filter-name"), request.getParameter("filter-gpa"), "", "","","");
			}
			for(int i=1;i<=100;i+=2) { 
				for(Student s: studentList)
			{
			List<Major> maj = s.getMajors();
			List<Interest> ints = s.getInterests();
			List<Skill> skls = s.getSkills();
		%>
<%-- 									class = "name-<%=s.getId()%> gpa-<%=s.getId()%> <%for(Major m : maj) --%>
<%-- 												%>major-<%=m.getId()%> year-<%=s.getYear()%> <%for(Interest in : ints) --%>
<%-- 												%>interest-<%=in.getId()%> <%for(Skill sk : skls) --%>
<%-- 												%>skill-<%=sk.getId()%> " --%>
			<tr > 
				<td>
					<p>
						<a class="actionButton accept" href="#">Accept</a>&nbsp;
						<a class="actionButton reject" href="#">Reject</a>
					</p>
				</td>
				<td><%=s.getName() %></td>
				<td><%=s.getGpa() %></td>
				<td><%=s.getString(s.getMajors()) %></td>
				<td><%=s.getYear() %></td>
				<td title="<%= s.getString(s.getSkills()) %>">
					<%=s.getTruncatedString(s.getSkills()) %>
				</td>
				<td title="<%= s.getString(s.getInterests()) %>">
					<%=s.getTruncatedString(s.getInterests()) %>
				</td>
			</tr>
		<% }} %>
	</tbody>
	</table>
	<jsp:include page="pager.jsp"/>
				</div>				
			</div>
		</div>
	</div>
</div>
</body>
</html>
