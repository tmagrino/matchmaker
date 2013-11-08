<jsp:include page="header.jsp">
    <jsp:param name="stud_or_prof" value="researcher"/>
    <jsp:param name="sidebar_type" value="researcher-profile"/>
    <jsp:param name="sidebar_selected" value="edit"/>
    <jsp:param name="top_selected" value="profile"/>
</jsp:include>
		<%@page import="java.util.*,model.*"%>
			<%Researcher r = ResearcherController.getResearcherByNetID("tm123"); %>
					<div class="content">
						<h1>My Profile</h1>
						<form name="profile-form" action="/">
							<div class="photo-info clearfix">
								<img class="avatar" src="avatar-male.jpg" alt="avatar"/>
								<div class="info">
									<h2><%=r.getName() %></h2>
									<p class="required"><label for="email">Email</label><input name="email" value="<%=r.getEmail() %>" type="text"></input></p>
									<p class="required"><label for="url">URL</label><input name="url" value="<%=r.getWebpage() %>" type="text"></input></p>
									<p class="required"><label for="dept">Department</label><input name="dept" value="<%=r.getDepartment() %>" type="text"></input></p>
									<p><label for="specialization">Specialization</label><input name="specialization" value = "<%=r.getResearchArea() %>" type="text"></input></p>
								</div>
							</div>
							<input type="submit" value="Save Changes"></input>
						</form>
					</div>				
				</div>
			</div>
		</div>
	</div>
</body>
</html>