<html>
<body>
<style>
  html, body {
  font-family: Arial, sans-serif;
  background: #fff;
  margin: 0;
  padding: 0;
  border: 0;
  position: absolute;
  height: 100%;
  min-width: 95%;
  font-size: 13px;
  color: #404040;
  }
 .accountchooser-card ol li {
  height: 76px;
  border-top: 1px solid #d5d5d5;
  } 
  .accountchooser-card ol li button,
  .accountchooser-card ol li .remove-entry {
  padding: 15px 0;
  display: block;
  width: 95%;
  height: 100%;
  outline: none;
  border: 0;
  cursor: pointer;
  text-align: left;
  background: url(images/arrow_right.png) right center no-repeat;
  background-size: 21px 21px;
  }
  .accountchooser-card ol li button img,
  .accountchooser-card ol li .remove-entry img {
  float: left;
  -moz-border-radius: 50%;
  -webkit-border-radius: 50%;
  border-radius: 50%;
  height: 46px;
  width: 46px;
  }
  .accountchooser-card ol li button span,
  .accountchooser-card ol li .remove-entry span {
  display: block;
  margin-left: 58px;
  padding-right: 20px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  }
  .accountchooser-card ol li button span.account-name,
  .accountchooser-card ol li .remove-entry span.account-name {
  font-weight: bold;
  font-size: 16px;
  padding-top: 3px;
  color: #427fed;
  }
</style>

<%@page
	import="java.util.*,model.*, org.json.*,javax.persistence.*"%>
	
<%	 EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
	 EntityManager em = emf.createEntityManager();
	 User u = UserController.findUser(em, "amg289");
	 Student s = StudentController.getStudentByNetID(em, "amg289");
	 Researcher r = ResearcherController.getResearcherByNetID(em, "amg289");
	 
	 Boolean isStudent = false;
	 if(s != null){
		 isStudent = true;
	 }
	 Boolean isResearcher = false;
	 if(r != null){
		 isResearcher = true;
	 }
%>

  <p><p>
<div id="accountchooser-card" class="card accountchooser-card">
  <h1 align = "center">Choose an account</h1>
  <form action="" method="post">
 	<ol class="accounts " id="account-list">
 	 <% if (u.isAdmin()){ %>
  	<li><button type="submit" id="choose-account-1">
  		<img class="account-image" alt=""
                 src="images/blank.png">
  		<span class="account-name"><%= u.getName() %></span>
  		<span class="account-email" id="account-email-1">
  			Administrator
 		 </span>
  		</button>
  	</li>
  <% }%>
  <% if(isStudent){ %>
  <li><button type="submit" id="choose-account-0">
  <img class="account-image" src="images/blank.png">
  	<span class="account-name"><%= u.getName() %></span>
  		<span class="account-email" id="account-email-0">
  		Student 
  		</span>
  		</button>
  		</li>
  <%}%>
  <%if (isResearcher){ %>
  <li><button type="submit" id="choose-account-1">
  	<img class="account-image" alt=""
                 src="images/blank.png">
  		<span class="account-name"><%= u.getName() %></span>
  		<span class="account-email" id="account-email-1">
  		Researcher
  		</span>
  		</button>
  		</li>
  <%}%>
  
  </ol>
  </form>
</div>
 </body>
</html>