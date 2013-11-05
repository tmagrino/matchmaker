<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*,model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% StudentController.updateStudent(new Student(), request.getParameter("name"), "jb20",
		Double.parseDouble(request.getParameter("gpa")), request.getParameter("email"),
		YearController.getYear(request.getParameter("grad-year")), CollegeController.parseCollege(colleges), majors, minors, skills, priorExperience, interests, transcript)


StudentController controller = new StudentController();
List<College> clist= new ArrayList<College>();
clist.add(new College(request.getParameter("school")));
List<Major> majorlist= new ArrayList<Major>();
majorlist.add(new model.Major(request.getParameter("major")));
List<Minor> minorlist= new ArrayList<Minor>();
minorlist.add(new model.Minor(request.getParameter("minor")));
List<Skill> slist= new ArrayList<Skill>();
slist.add(new Skill(request.getParameter("skill")));
List<Course> courselist = new ArrayList<Course>();
int nCourses = Integer.parseInt(request.getParameter("nVals"));
for(int i = 0; i<nCourses; i++){
	Course cobj = new Course();
	cobj.setCoursenum(request.getParameter("coursenum-"+i));
	cobj.setTitle(request.getParameter("title-"+i));
	cobj.setGrade(request.getParameter("grade-"+i));
	cobj.setSemester(request.getParameter("semester-"+i));
	courselist.add(cobj);
}
	
	List<Skill> skillList = SkillController.parseSkill(request.getParameter("as_values_skills"));
	List<Interest> interestList = InterestController.parseInterest(request.getParameter("as_values_research"));
	List<Major> majorList = MajorController.parseMajor(request.getParameter("as_values_major"));
	List<Minor> minorList = MinorController.parseMinor(request.getParameter("as_values_minor"));
// StudentController.updateStudent(s, request.getParameter("name"), "jb20", 
// 		Double.parseDouble(request.getParameter("gpa")),
// 		request.getParameter("email"),YearController.getYear(request.getParameter("grad-year")),
// 		clist, majorlist,minorlist, slist,new ArrayList<model.Experience>(),
// 		new ArrayList<Interest>(),courselist);
	for (Interest i : interestList){	%>
	<li><%=i.getDescription() %></li>
	<%} %>



</body>
</html>
