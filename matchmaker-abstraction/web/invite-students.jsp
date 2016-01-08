<%--
	This page allows the project lead to invite students to his/her projects.
 --%>
<jsp:include page="header.jsp">
    <jsp:param name="stud_or_prof" value="researcher"/>
    <jsp:param name="top_selected" value="students"/>
</jsp:include>
<%@
	page import="java.util.*,model.Student, model.*, org.json.JSONObject,javax.persistence.*"
%>
<div class="content">
	<%
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		JSONObject jsonMajor = FieldValueController.getItemJson(em,FieldFactory.MAJOR);
		JSONObject jsonSkills = FieldValueController.getItemJson(em,FieldFactory.SKILL);
		JSONObject jsonInterest = FieldValueController.getItemJson(em,FieldFactory.INTEREST);

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
	<a class="filter-button" href="project-applications.jsp">Show applications</a>
	<h1>Students</h1>    
	
	<%
		if (showHidden) {
	%>
		<br /><p>
		<span class="hidden-message">Displaying hidden students</span><a class="filter-button hide" href="invite-students.jsp?showhidden=no"> Hide Students</a>
		</p>
	<%
		}
		else {
	%>
		<br /><p>
			<a class="filter-button hide" href="invite-students.jsp?showhidden=yes"> Show hidden students</a>
		</p>
	<%
		}
	%>
	
	<table class="project-list searchable">
		<jsp:include page="stud-filters.jsp"/>
		<%
			List<Student> studentList = new ArrayList<Student>();
			studentList = StudentController.getAllStudents(em);
			for (Student s : studentList) {
				boolean hid = hiddenStudents.contains(s);
				if (hid && !showHidden) {
					continue;
				}
		%>
		<tr>
			<td class="no-title">
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
						Invited
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
				<% if(has_avail_proj){ %>
				<div id="invite-form-<%=s.getNetID() %>" class="invite-form hidden" title="Invite Students">
					<form method="get" action="send-invitation.jsp">
						<p>Select which project you would like to invite the student to:</p>
						<% for(Project p: projs){ 
								if(ApplicationController.getApplication(em, s, p) == null){ %>
									<input type="radio" name="proj-id" value="<%= p.getId() %>"><%= p.getName() %>
									<br>	
						<%  	} 	
						    } %>
							<input type="hidden" name="stud-id">
				         <input type="submit" value="Select">
				     </form>
				 </div>
				 <% } %>
			</td>
			<td><p><a href="profile-nonedit.jsp?studid=<%=s.getNetID() %>"><%= s.getName()%></a></p></td>
			<td><p><%=s.getGpa() %></p></td>
			<td><p><%=s.getString(s.getMajors()) %></p></td>
			<td><p><%=s.getYear() %></p></td>
			<td><p><%=s.getString(s.getSkills()) %></p></td>
			<td><p><%=s.getString(s.getInterests()) %></p></td>
		</tr>
		<% } tx.commit(); %>
		</tbody>
	</table>
</div>				
<jsp:include page="footer.jsp"></jsp:include>
