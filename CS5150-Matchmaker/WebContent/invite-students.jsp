<jsp:include page="header.jsp">
    <jsp:param name="stud_or_prof" value="researcher"/>
    <jsp:param name="sidebar_type" value="researcher-students"/>
    <jsp:param name="sidebar_selected" value="invite"/>
    <jsp:param name="top_selected" value="students"/>
</jsp:include>
<%@
	page import="java.util.*,model.Student, model.*, org.json.JSONObject,javax.persistence.*"
%>
<div class="content">
	<%
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
		EntityManager em = emf.createEntityManager();
		JSONObject jsonMajor = ListController.getItemJson(em,ItemFactory.MAJOR);
		JSONObject jsonSkills = ListController.getItemJson(em,ItemFactory.SKILL);
		JSONObject jsonInterest = ListController.getItemJson(em,ItemFactory.INTEREST);

		Researcher r = ResearcherController.getResearcherByNetID(em, (String)session.getAttribute("currentUser"));
		List<Student> hiddenStudents = r.getSettings().getHiddenStudents();
		String showhide = request.getParameter("showhidden");
		boolean showHidden = "yes".equals(showhide);
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
	
	<%
		if (showHidden) {
	%>
		<br /><p>
		<font size="2"><i>Displaying hidden projects</i><a href="invite-students.jsp?showhidden=no"> Hide Students</a></font>
		</p>
	<%
		}
		else {
	%>
		<br /><p>
		<font size="2"><i><a href="invite-students.jsp?showhidden=yes"> Show hidden students</a></i></font>
		</p>
	<%
		}
	%>
	
	<table class="project-list">
		<jsp:include page="stud-filters.jsp"/>
		<%
			List<Student> studentList = new ArrayList<Student>();
			studentList = StudentController.getAllStudents(em);
			for (Student s : studentList) {
				String cssClasses = s.getName().replaceAll(" ", "_").toLowerCase() + " "
					+ s.getString(s.getMajors()).replaceAll(" ", "_").toLowerCase() + " "
					+ s.getString(s.getSkills()).replaceAll(" ", "_").toLowerCase() + " "
					+ s.getString(s.getInterests()).replaceAll(" ", "_").toLowerCase() + " "
					+ s.getNetID().replaceAll(" ", "_").toLowerCase();
				boolean hid = hiddenStudents.contains(s);
				if (hid && !showHidden) {
					continue;
				}
		%>
		<tr class="<%=cssClasses %>">
			<td><p>
				<%  
					List<Project> projs = r.getProjects(); 
					Boolean has_avail_proj = false;
					for(Project p: projs){
						if(ApplicationController.getApplication(em, s, p) == null){
							has_avail_proj = true;
							break;
						}
					}
					if(!has_avail_proj){ %>
						<a class="placeholder">Invited</a>
					<%}else{ %>
						<a id=<%=s.getNetID() %> class="actionButton invite" href="send-invitation.jsp?id=<%=s.getNetID()%>">Invite</a>
				<%   }
        			if (hid && showHidden) {
        		%>	<a class="actionButton unhide" href="unhideStudent.jsp?id=<%=s.getNetID()%>">Unhide</a>
				<%
                	}
                    else {
                %>	<a class="actionButton hide" href="hideStudent.jsp?id=<%=s.getNetID()%>
                <% 
                	if (showHidden) {
                    	%>&amp;showhidden=yes<%
                    }
                %>
                	">Hide</a>
				<%
                }
               	%>
				</p>
				<% if(has_avail_proj){ %>
				<div id="invite-form-<%=s.getNetID() %>" class="invite-form hidden" title="Invite Students">
					<form method="get" action="send-invitation.jsp">
						<p>Select which project you would like to invite the student to</p>
						<% for(Project p: projs){ 
								if(ApplicationController.getApplication(em, s, p) == null){ %>
									<input type="radio" name="proj-id" value="<%= p.getId() %>"><%= p.getName() %>	
						<%  	} 	
						    } %>
							<input type="hidden" name="stud-id">
				         <input type="submit" value="Select">
				     </form>
				 </div>
				 <% } %>
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