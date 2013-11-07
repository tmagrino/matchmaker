<jsp:include page="header.jsp">
    <jsp:param name="stud_or_prof" value="prof"/>
    <jsp:param name="sidebar_type" value="prof-profile"/>
    <jsp:param name="sidebar_selected" value="view"/>
    <jsp:param name="top_selected" value="profile"/>
</jsp:include>
		<%@page import="java.util.*,model.*"%>
			<%Researcher p = ResearcherController.getResearcherByNetID("js123"); %>
					<div class="content">
						<h1>My Profile</h1>
						<div class="photo-info clearfix">
							<img class="avatar" src="avatar-male.jpg" alt="avatar"/>
							<div class="info">
								<h2>Professor Bob Smith</h2>
								<p>Email: bcs575@cornell.edu</p>
								<p><a href="#">www.cs.cornell.edu/bsmith</a></p>
								<p>Department of Computer Science</p>
								<p>Research Area in Machine Learning</p>
							</div>
						</div>
					</div>				
				</div>
			</div>
		</div>
	</div>
</body>
</html>