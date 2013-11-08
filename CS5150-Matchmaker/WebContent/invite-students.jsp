<jsp:include page="header.jsp">
    <jsp:param name="stud_or_prof" value="researcher"/>
    <jsp:param name="sidebar_type" value="researcher-students"/>
    <jsp:param name="sidebar_selected" value="invite"/>
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
								<%List<Student> studentList = StudentController.getAllStudents(); 
									for(Student s: studentList)
									{
									%>
										<tr>
											<td>
												<p><a href="#">Invite</a></p>
												<p><a href="#">Hide</a></p>
											</td>
											<td><%=s.getName() %></td>
											<td><%=s.getGpa() %></td>
											<td><%=s.getMajorString() %></td>
											<td><%=s.getYear() %></td>
											<td><%=s.getSkillString() %></td>
											<td><%=s.getInterestString() %></td>
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