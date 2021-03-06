CS5150-Matchmaker
=================

Set-up
- Install MySQL
- Create database
  - Run MySQL Command Line Client
      
      ```
      > CREATE DATABASE matchmaker;
      > USE matchmaker;
      ```
      
- Set the database user and password in src/META-INF/persistence.xml
- Install and start Apache Tomcat (ver. 7)
- Copy build.properties.in to build.properties and set `catalina.home` to the
  location of tomcat
- Build using the command:

    ```
    $ ant dist
    ```

- Copy the *.war file from build/ to [tomcat installation]/webapps/
- Deploy the webapp in tomcat

Development Notes
- src/META-INF/persistence.xml maintains a list of all the persisted objects,
  try to keep this up to date (eventually this should probably become something
  that's automatically generated by the build process)
- web/WEB-INF/web.xml should be modified to use the appropriate "welcome-file"
  before building
  - login.jsp for local testing
  - select-role.jsp for deployment with Cornell NetID

TODO:
- Loads of code clean up.
- Make differences between testing and deployed versions as small as possible
  (eg. make it possible to use the CUWebAuth fields in testing and make the
  welcome page setting be determined by which build command is run.)
- Make local configuration simpler and less error prone (eg. setting the
  password in a local copy of persistence.xml to avoid committing password to
  the repo...)
- Factor out emailing functionality in src/model/Email.java.

Backend Notes
- Major, Minor, Interest, and College classes are all entities in order to make
  them extensible by admins users.
- No SQL lines are needed with JPA (updated with EntityManager)
  - http://www.javaworld.com/javaworld/jw-01-2008/jw-01-jpa1.html?page=3

Helpful Links and Tutorials
- Tomcat
  - https://tomcat.apache.org/tomcat-7.0-doc/index.html
- JPA
  - http://www.javaworld.com/javaworld/jw-01-2008/jw-01-jpa1.html?page=1
  - http://docs.oracle.com/javaee/6/tutorial/doc/bnbqa.html
