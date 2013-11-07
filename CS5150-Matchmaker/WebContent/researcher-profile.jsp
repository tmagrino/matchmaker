<jsp:include page="header.jsp">
    <jsp:param name="stud_or_prof" value="researcher"/>
    <jsp:param name="sidebar_type" value="researcher-profile"/>
    <jsp:param name="sidebar_selected" value="view"/>
    <jsp:param name="top_selected" value="profile"/>
</jsp:include>
		<%@page import="java.util.*,model.*"%>
			<%Researcher r = ResearcherController.getResearcherByNetID("tm123"); %>
					<div class="content">
						<h1>My Profile</h1>
						<div class="photo-info clearfix">
							<img class="avatar" src="avatar-male.jpg" alt="avatar"/>
							<div class="info">
								<h2>Professor <%=r.getName()%></h2>
								<p>Email: <%=r.getEmail() %></p>
								<p><a href="#"><%=r.getWebpage() %></a></p>
								<p><%=r.getDepartment() %></p>
								<p>Research Area in <%=r.getResearchArea()%></p>
							</div>
						</div>
					</div>				
				</div>
			</div>
		</div>
	</div>
</body>
</html>