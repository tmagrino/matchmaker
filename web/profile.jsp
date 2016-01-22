<%--
	This page is an editable profile page for Students.
 --%>
<jsp:include page="header.jsp">
  <jsp:param name="stud_or_prof" value="stud" />
  <jsp:param name="top_selected" value="profile" />
</jsp:include>
<%@page import="java.util.*,model.*, org.json.*,javax.persistence.*"%>

<%
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
         EntityManager em = emf.createEntityManager();
         Student s = StudentController.getStudentByNetID(em,(String) session.getAttribute("currentUser"));
         
         Set<String> req_attr = new HashSet<String>(Arrays.asList("Email", "Major", "Year", "College", "GPA")); 
         String[] attributes = {"Email", "Major", "Minor", "Year", "College", "GPA", "Skills", "Research Interests"};
         String[] autocomplete_attr = {FieldFactory.MAJOR, FieldFactory.MINOR, FieldFactory.COLLEGE, FieldFactory.SKILL
                         , FieldFactory.INTEREST};
         JSONArray jsonArrAll = new JSONArray();
         JSONArray jsonArrStud = new JSONArray();
         for(String auto_attr: autocomplete_attr){
                 jsonArrAll.put(FieldValueController.getItemJson(em, auto_attr));
                 if(s!=null){
                 	jsonArrStud.put(FieldValueController.getObjectJson(s.getListAttribute(auto_attr)));
                 	User usr = UserController.findUser(em, s.getNetID());
                 	UserController.setName(em, usr, s.getName());
                 }
         }
%>
<script type="text/javascript">
        var autocomplete_attr = Array("major", "minor", "college", "skills", "research_interests");
        var jsonArrAll = <%= jsonArrAll %>;
    var jsonArrStud = <%= jsonArrStud %>;
</script>

<div class="content">
  <div id="all-major" class="hidden" title="All Major Suggestions"></div>
  <div id="all-minor" class="hidden" title="All Minor Suggestions"></div>
  <div id="all-college" class="hidden" title="All College Suggestions"></div>
  <div id="all-skills" class="hidden" title="All Skill Suggestions"></div>
  <div id="all-research_interests" class="hidden"
    title="All Interest Suggestions"
  ></div>
  <div class="photo-info">
    <form name="profile" action="save-profile-changes.jsp" method="GET">
      <p class="error-msg">Errors were found. Please correct the
        errors before saving.</p>
      <table class="info">
        <%if(!(s.getName().equals("New User"))){ %>
        <tr>
          <td class="attr-label" colspan="3"><h2><%=s.getName() %></h2></td>
        </tr>
        <% } else { %>
        <tr>
          <td class="attr-label"><h2>New User:</h2></td>
          <td class="field">
            <p class="editable">
              <input name="NewUser" value=""
                placeholder="What is your name?" type="text"
              />
            </p>
          </td>
        </tr>
        <% }
                      
                                for(String attr: attributes){ %>
        <tr <%= req_attr.contains(attr) ? "class=\"required\"" : "" %>>
          <td class="attr-label"><%=attr %>:</td>
          <td class="field">
            <% if(s != null && s.getAttribute(attr) != null){ %>
            <p
              class="read-only <%= s.getAttribute(attr).equals("") ? "hidden" : "" %>"
            >
              <%=s.getAttribute(attr) %>
              <a
                class="edit-btn <%= s.getAttribute(attr) != null && s.getAttribute(attr).length() > 80 ? "extended" : "" %>"
                href="#"
              > <img src="images/pencil_small.png" alt="edit" />
              </a>
            </p>
            <p
              class="editable <%= s.getAttribute(attr).equals("") ? "" : "hidden" %>"
            >
              <% if(attr.equals("Year")){ %>
              <select name="year">
                <% String year_val = s.getAttribute(attr); %>
                <% for(int i = 1; i<=5; i++){ 
                                                                        String i_str = Integer.toString(i);
                                                                        if(i == 5){
                                                                                i_str += "+";
                                                                        }
                                                                        if(year_val.equals(i_str)){ %>
                <option value="<%= i_str %>" selected="selected"><%= i_str%></option>
                <%        } else{ %>
                <option value="<%= i_str %>"><%= i_str%></option>
                <%} %>
                <% } %>
              </select>
              <%	 } else{ %>
              <input
                name="<%=attr.replaceAll(" ", "_").toLowerCase() %>"
                value="<%=s.getAttribute(attr) %>" type="text"
              />
              <% } %>
            </p>
          </td>
          <td>
            <button class="view-suggestion hidden" type="button">View
              All Suggestions</button>
          </td>
          <% } %>
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