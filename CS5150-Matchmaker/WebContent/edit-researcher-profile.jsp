<jsp:include page="header.jsp">
    <jsp:param name="stud_or_prof" value="researcher"/>
    <jsp:param name="sidebar_type" value="researcher-profile"/>
    <jsp:param name="sidebar_selected" value="edit"/>
    <jsp:param name="top_selected" value="profile"/>
</jsp:include>
					<div class="content">
						<h1>My Profile</h1>
						<form name="profile-form" action="/">
							<div class="photo-info clearfix">
								<img class="avatar" src="avatar-male.jpg" alt="avatar"/>
								<div class="info">
									<h2>Bob Smith</h2>
									<p class="required"><label for="email">Email</label><input name="email" value="bcs575@cornell.edu" type="text"></input></p>
									<p class="required"><label for="url">URL</label><input name="url" value="www.cs.cornell.edu/bsmith" type="text"></input></p>
									<p class="required"><label for="dept">Department</label><input name="dept" type="text"></input></p>
									<p><label for="specialization">Research Area</label><input name="specialization" type="text"></input></p>
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