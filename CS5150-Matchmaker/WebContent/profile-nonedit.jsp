<jsp:include page="header.jsp">
        <jsp:param name="stud_or_prof" value="researcher" />
        <jsp:param name="top_selected" value="students" />
</jsp:include>
<%@page
        import="java.util.*,model.*, org.json.*,javax.persistence.*"%>

<%         EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
         EntityManager em = emf.createEntityManager();
         Researcher r = ResearcherController.getResearcherByNetID(em,(String) session.getAttribute("currentUser"));
         
         Student s = StudentController.getStudentByNetID(em, (String) request.getParameter("studid"));
         
         String[] attributes = {"Email", "Major", "Minor", "Year", "College", "GPA", "Skills", "Research Interests"};
        
     
%>

<div class="content">
       
        <div class="photo-info clearfix">
        	<form name="profile" action="#" method="GET">
                        <table class="info">
                                <tr>
                                        <td class="attr-label" colspan="3"><h2><%=s.getName() %></h2></td>

                                </tr>
                                <% for(String attr: attributes){ %>
                                <tr>
                                        <td class="attr-label"><%=attr %>:</td>
                                        <td class="field">
                                                <p class="read-only">
                                                      <%=s.getAttribute(attr) %>
                                                </p>
                                        </td>
                                </tr>
                                <% } %>
                                <% if(request.getParameter("appid") != null && request.getParameter("appid").length() > 0){ %>
	                                <tr>
	                                	<td class="attr-label">Student Description:</td>
	                                	<td class="field">
	                                		<p class="read-only">
	                                			<%
	                                				Application a = ApplicationController.getApplicationById(em, request.getParameter("appid"));
	                                			%>
	                                			<%= a.getStudentResponse() %>
	                                		</p>
	                                	</td>
	                                </tr>
	                             <% } %>
                        </table>
            	<FORM><INPUT Type="button" VALUE="Back" onClick="history.go(-1);return true;"></FORM>
        	</form>

        </div>
</div>
</div>
</div>
</div>
</div>
</body>
</html>