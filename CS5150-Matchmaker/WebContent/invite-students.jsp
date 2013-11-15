<jsp:include page="header.jsp">
    <jsp:param name="stud_or_prof" value="researcher"/>
    <jsp:param name="sidebar_type" value="researcher-students"/>
    <jsp:param name="sidebar_selected" value="invite"/>
    <jsp:param name="top_selected" value="students"/>
</jsp:include>
<%@page import="java.util.*,model.Student, model.*, org.json.JSONObject,javax.persistence.*"%>
					<div class="content">
						<%
						EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
					 	EntityManager em = emf.createEntityManager();
				        JSONObject jsonMajor = MajorController.getMajorJson(em);
				        JSONObject jsonSkills = SkillController.getSkillJson(em);
				        JSONObject jsonInterest = InterestController.getInterestJson(em);
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
								<%List<Student> studentList = new ArrayList<Student>();
								
								if (request.getParameter("filter-name") == null || request.getParameter("filter-gpa") == null){
									studentList = StudentController.getStudentByFilter(em,
											"", "", "", "","","");
								}
								//List<Student> studentList = StudentController.getAllStudents();
								else{studentList = StudentController.getStudentByFilter(em,
										request.getParameter("filter-name"), request.getParameter("filter-gpa"), "", "","",""); 
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
											<td><%=s.getString(s.getMajors()) %></td>
											<td><%=s.getYear() %></td>
											<td><%=s.getString(s.getSkills()) %></td>
											<td><%=s.getString(s.getInterests()) %></td>
										</tr>
								<%}} %>
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