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
					 	JSONObject jsonMajor = ListController.getItemJson(em,ItemFactory.MAJOR);
				        JSONObject jsonSkills = ListController.getItemJson(em,ItemFactory.SKILL);
				        JSONObject jsonInterest = ListController.getItemJson(em,ItemFactory.INTEREST);
				         %>
				        <script type="text/javascript">
				        	var majorData = <%= jsonMajor %>;
				        	var skillsData = <%= jsonSkills %>;
				        	var interestData = <%= jsonInterest %>;
				        </script>
				        <a href="project-applications.jsp">Show applications</a>
						<form name="filter-list" id="filter-list" class="clearfix">
						    <h1>Students</h1>    
							<div class="search-container">
								<input class="search-text" type="text" placeholder="Search..."/>
								<input type="submit" value="Filter"/>
							</div>
						</form>
							<table class="project-list">
								<jsp:include page="stud-filters.jsp"/>
								
								<%List<Student> studentList = new ArrayList<Student>();
								

								studentList = StudentController.getAllStudents(em);

									for (Student s : studentList){
										String cssClasses = s.getName().replaceAll(" ", "_").toLowerCase() + " "
							                    + s.getString(s.getMajors()).replaceAll(" ", "_").toLowerCase() + " "
							                     + s.getString(s.getSkills()).replaceAll(" ", "_").toLowerCase() + " "
							                     + s.getString(s.getInterests()).replaceAll(" ", "_").toLowerCase() + " "
							                     + s.getNetID().replaceAll(" ", "_").toLowerCase();
									%>
										<tr class="<%=cssClasses %>">
											<td>
												<p>
													<a class="actionButton invite" href="send-invitation.jsp?id=<%=s.getNetID()%>">Invite</a>
													<a class="actionButton hide" href="#">Hide</a>
												</p>
											</td>
											<td><a href="profile-nonedit.jsp?studid=<%=s.getNetID() %>"><%= s.getName()%></a></td>
											<td><%=s.getGpa() %></td>
											<td><%=s.getString(s.getMajors()) %></td>
											<td><%=s.getYear() %></td>
											<td><%=s.getString(s.getSkills()) %></td>
											<td><%=s.getString(s.getInterests()) %></td>
										</tr>
								<%} %>
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