<jsp:include page="header.jsp">
	<jsp:param name="stud_or_prof" value="stud" />
	<jsp:param name="sidebar_type" value="stud-profile" />
	<jsp:param name="sidebar_selected" value="view" />
	<jsp:param name="top_selected" value="profile" />
</jsp:include>
<%@page import="java.util.*,model.*, org.json.JSONObject"%>
<%
     Student s = StudentController.getStudentByNetID("lr437"); 
     String[] attributes = {"Email", "Major", "Minor", "Year", "College", "GPA", "Skills", "Research Interests"};
     
     /* These JSON objects store all possible values */
     JSONObject jsonMajor = MajorController.getMajorJson();
     JSONObject jsonMinor = MinorController.getMinorJson();
     JSONObject jsonCollege = CollegeController.getCollegeJson();
     JSONObject jsonSkills = SkillController.getSkillJson();
     JSONObject jsonInterest = InterestController.getInterestJson();
     
     /* These JSON objects store values specific to the given student */
     JSONObject jsonStudMajor = s.getMajorJson();
     JSONObject jsonStudMinor = s.getMinorJson();
     JSONObject jsonStudCollege = s.getCollegeJson();
     JSONObject jsonStudSkills = s.getSkillJson();
     JSONObject jsonStudInterest = s.getInterestJson();
     
%>
<script type="text/javascript">
         var majorData = <%= jsonMajor %>;
         var minorData = <%= jsonMinor %>;
         var collegeData = <%= jsonCollege %>;
         var skillsData = <%= jsonSkills %>;
         var interestData = <%= jsonInterest %>;
         
         var prefillMajor = <%= jsonStudMajor %>;
         var prefillMinor = <%= jsonStudMinor %>;
         var prefillCollege = <%= jsonStudCollege %>;
         var prefillSkills = <%= jsonStudSkills %>;
         var prefillInterests = <%= jsonStudInterest %>;
</script>
 
<div class="content">
	<h1>My Profile</h1>
	<div class="photo-info clearfix">
		<img class="avatar" src="images/avatar-male.jpg" alt="avatar" />
		<form name="profile">
			<table class="info">
				<tr>
					<td><h2><%=s.getName() %></h2></td>
					<td></td>
				</tr>
				<% for(String attr: attributes){ %>
				<tr>
					<td><%=attr %>:</td>
					<td class="field">
						<p
							class="read-only <%= s.getAttribute(attr) == "" ? "hidden" : "" %>">
							<%=s.getAttribute(attr) %>
							<a class="edit-btn" href="#"> <img
								src="images/pencil_small.png" alt="edit" />
							</a>
						</p>
						<p
							class="editable <%= s.getAttribute(attr) != "" ? "hidden" : "" %>">
							<input name="<%=attr.replaceAll(" ", "_").toLowerCase() %>"
								value="<%=s.getAttribute(attr) %>" type="text" />
						</p>
					</td>
				</tr>
				<% } %>
			</table>
			<input type="submit" value="Save Changes" />
		</form>
	</div>
</div>
</div>
</div>
</div>
</div>
</body>
</html>