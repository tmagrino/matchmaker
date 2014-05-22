CS5150-Matchmaker
=================

Set-up
- Download Eclipse (latest version of Enterprise Edition)
- MySQL
	- Download MySQL
		- http://dev.mysql.com/downloads/mirror.php?id=414474
		- Pick "No thanks on bottom"
		- Install (make sure to include JDBC connector)
	- Create database
		- Run MySQL Command Line Client
		- Type 'CREATE DATABASE matchmaker'
		- Type 'USE matchmaker'
- Link up local database in Eclipse
	- http://itsolutionsforall.com/eclipse_sql.php
	- Skip the downloading part
	- Skip the setting up database part
	- Jar file should be within MySQL folder
	- Set URL as: jdbc:mysql://localhost:3306/matchmaker
- Download and set up Apache Tomcat for testing the web application within Eclipse
	- http://www.coreservlets.com/Apache-Tomcat-Tutorial/tomcat-7-with-eclipse.html
- Set up EGit in Eclipse
	- Help -> Install new software
	- Work with: Hit drop down arrow, pick EGit P2...
	- Check Eclipse Git Team Provider
	- Install
	- Window -> Customize Perspective -> Command Availability Tab -> Check the 'Git' box
- Set up Project
	- File -> Import -> Git -> Clone URI
	- URI: https://github.com/jkahuja/CS5150-Matchmaker.git
	- Type in user and password from www.github.com
	- Next -> Next -> Next -> Choose 'Import existing projects' -> Next -> Finish
 

Backend Notes
- Major, Minor, Interest, and College classes are all entities in order to make them extensible by admins users.
- No SQL lines are needed with JPA (updated with EntityManager)
	- http://www.javaworld.com/javaworld/jw-01-2008/jw-01-jpa1.html?page=3

Helpful Tutorials
- JPA
	- http://www.javaworld.com/javaworld/jw-01-2008/jw-01-jpa1.html?page=1
	- http://docs.oracle.com/javaee/6/tutorial/doc/bnbqa.html
