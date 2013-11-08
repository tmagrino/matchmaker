<jsp:include page="header.jsp">
    <jsp:param name="stud_or_prof" value="researcher"/>
    <jsp:param name="sidebar_type" value="researcher-project"/>
    <jsp:param name="sidebar_selected" value="current"/>
    <jsp:param name="top_selected" value="project"/>
</jsp:include>
					<div class="content">
						<%@page import="java.util.*,model.Student, model.*, org.json.JSONObject"%>
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
						<h1>My Projects</h1>
						<form name="filter-list" id="filter-list">
							<input type="submit" value="Filter"/>
							<table class="project-list">
								<thead>
									<tr>
										<th>Project Name</th>
										<th>Project URL</th>
										<th>Description</th>
										<th>Research Area</th>
										<th>Required Skills</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><input type="text" name="filter-proj-name"></td>
										<td></td>
										<td></td>
										<td><input type="text" name="filter-interest"></td>
										<td><input type="text" name="filter-skill"></td>
									</tr>
									<tr>
										<td>Project Name</td>
										<td><a href="#">Link to Project Webpage</a></td>
										<td></td>
										<td>Programming Languages</td>
										<td>Java</td>
									</tr>
									<tr>
										<td>Project Name</td>
										<td></td>
										<td>This is a description of Project 2.  This is a...</td>
										<td>Systems</td>
										<td>Python</td>
									</tr>
								</tbody>
							</table>
						</form>
					</div>				
				</div>
			</div>
		</div>
	</div>
</body>
</html>
