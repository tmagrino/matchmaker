<% 
	int id; 
	String proj_sidebar = null;
	if(request.getParameter("proj_id") != null){
		id = Integer.parseInt(request.getParameter("proj_id"));
		proj_sidebar = request.getParameter("proj_id");
	}
%>
<jsp:include page="header.jsp">
    <jsp:param name="stud_or_prof" value="researcher"/>
    <jsp:param name="sidebar_type" value="researcher-students"/>
    <jsp:param name="sidebar_selected" value="<%= proj_sidebar %>"/>
    <jsp:param name="top_selected" value="students"/>
</jsp:include>
<%@page import="java.util.*,model.Student, model.*, org.json.JSONObject"%>
					<div class="content">
						<%
				        JSONObject jsonMajor = MajorController.getMajorJson();
				        JSONObject jsonSkills = SkillController.getSkillJson();
				        JSONObject jsonInterest = InterestController.getInterestJson();
				         %>
				        <script type="text/javascript">
				        	var majorData = <%= jsonMajor %>;
				        	var skillsData = <%= jsonSkills %>;
				        	var interestData = <%= jsonInterest %>;
				        </script>
						<h1>Students</h1>
						<form name="filter-list" id="filter-list">
							<input type="submit" value="Filter"/>
							<table class="project-list">
									<jsp:include page="stud-filters.jsp"/>
									<% 
									List<Student> studentList = StudentController.getAllStudents();
									
									for(int i=1;i<=100;i+=2) { 
										for(Student s: studentList)
									{
									List<Major> maj = s.getMajors();
									List<Interest> ints = s.getInterests();
									List<Skill> skls = s.getSkills();
									%>
										<tr class = "name-<%=s.getId()%> gpa-<%=s.getId()%> <%for(Major m : maj)
												%>major-<%=m.getId()%> year-<%=s.getYear()%> <%for(Interest in : ints)
												%>interest-<%=in.getId()%> <%for(Skill sk : skls)
												%>skill-<%=sk.getId()%> "> 
											<td>
												<p><a href="#">Accept</a></p>
												<p><a href="#">Reject</a></p>
											</td>
											<td><%=s.getName() %></td>
											<td><%=s.getGpa() %></td>
											<td><%=s.getMajorString() %></td>
											<td><%=s.getYear() %></td>
											<td><%=s.getTruncatedSkillString() %></td>
											<td><%=s.getTruncatedInterestString() %></td>
										</tr>
									<% }} %>
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
