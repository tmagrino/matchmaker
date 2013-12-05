<jsp:include page="header.jsp">
        <jsp:param name="stud_or_prof" value="researcher" />
        <jsp:param name="top_selected" value="profile" />
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
                <img class="avatar" src="images/avatar-male.jpg" alt="avatar" />
                <form name="profile" action="save-profile-changes.jsp" method="GET">
                        <table class="info">
                                <tr>
                                        <td class="attr-label" colspan="3"><h2><%=s.getName() %></h2></td>

                                </tr>
                                <% for(String attr: attributes){ %>
                                <tr>
                                        <td class="attr-label"><%=attr %>:</td>
                                        <td class="field">
                                                <p class="read-only 
                                                	
                                                		<%= s.getAttribute(attr) == "" ? "hidden" : "" %>">
                                                        <%=s.getAttribute(attr) %>
                                                </p>
                                                <p class="editable <%= s.getAttribute(attr) != "" ? "hidden" : "" %>">
                                                    <input name="<%=attr.replaceAll(" ", "_").toLowerCase() %>"
                                                      value="<%=s.getAttribute(attr) %>" type="text" />
                                                        
                                                </p>
                                                <p class="other hidden">
                                                        <input name="<%=attr.replaceAll(" ", "_").toLowerCase()+"_other" %>" type="text" />
                                                </p>
                                        </td>
                                        <td>
                                                <button class="view-suggestion hidden" type="button">View All Suggestions</button>
                                        </td>
                                </tr>
                                <% } %>
                        </table>
                </form>

        </div>
</div>
</div>
</div>
</div>
</div>
</body>
</html>