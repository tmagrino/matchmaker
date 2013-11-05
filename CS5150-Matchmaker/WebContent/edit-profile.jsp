<jsp:include page="header.jsp">
    <jsp:param name="stud_or_prof" value="stud"/>
    <jsp:param name="sidebar_type" value="stud-profile"/>
    <jsp:param name="sidebar_selected" value="edit"/>
    <jsp:param name="top_selected" value="profile"/>
</jsp:include>
<%@page import="java.util.*,model.*"%>
<div class="content">
	<%StudentController controller = new StudentController();
	 Student s = controller.getStudentByNetID("jb20");
	 List<Minor> minors = MinorController.getMinorList();
	 List<Major> majors = MajorController.getMajorList();
	 List<Skill> skills = SkillController.getSkillList();
	 List<Interest> interests = InterestController.getInterestList();
	 List<College> colleges = CollegeController.getCollegeList();
		 %>
	<h1>My Profile</h1>
		<h2 class="subheading">General Information</h2>
		<form name="profile-form" action="savechanges.jsp" method="GET">
			<div class="photo-info clearfix">
				<img class="avatar" src="avatar-female.jpg" alt="avatar"/>
				<div class="info">
					<h2><%=s.getName() %></h2>
					<p class="required"><label for="email">Email</label><input name="email" value="<%=s.getEmail() %>" type="text"></input></p>
					<p class="required"><label for="major">Major</label><input name="major" value="Computer Science" type="text"></input></p>
					<p><label for="minor">Minor</label><input name="minor" value="Music" type="text"></input></p>
					<p class="required"><label for="grad-year">Year of Graduation</label><input name="grad-year" type="text"></input></p>
					<p class="required"><label for="school">College</label><input name="school" type="text"></input></p>
				</div>
			</div>
			<h2 class="subheading">Application Information</h2>	
			<div class="application-info">
				<p class="required"><label for="gpa">GPA</label><input name="gpa" type="text" maxlength="4"></input></p>
				<p><label for="skills">Skills</label><input name="skills" type="text"></input></p>
				<p><label for="research-interest">Research Interests</label><input name="research-interest" type="text"></input></p>
			</div>
			<input type="submit" value="Save Changes"></input>
		</form>
</div>			
	
<!-- Close div's opened in header -->
</div> <!-- main -->
</div> <!--  container -->
</div> <!-- wrapper -->
</div> <!-- page -->
</body>
</html>