TestPlans Readme
-------------------
The test plans in this folder have been built for Apache Jmeter and the *.jmx files can directly be imported to Jmeter.

Starting  jmeter
----------------
"apache-jmeter-2.13" folder has a script in "bin/jmeter.sh"
Run this script and the jmeter application will launch, provided JRE is available.
The test plans in the other folders can now be directly imported into jmeter.

researcher
----------
Folder contains all test plans related to the researcher/project leader role.

The "Create Project Leader Test Plan.jmx" must be run first so that sufficient netIDs have been generated to simulate multiple
logins.

The "Researcher-superset-test-plans.jmx" can be run next to simulate all probable use-cases occurring in parallel.

The number of reserachers and other login boundary ranges must be defined in the "User Defined Variables" section of the Jmeter
test plan.

student
--------
Contains all test plans relevant to the student role.

The "MM_CreateUser.jmx" must be run first to generate sufficient netIDs. This test plan creates students starting from netID 1
to some defined boundary value.

The "MM_Super_set_test_plan.jmx" must be run next. This will simulate all possible use cases with random logins created
in the previous step.

Most of the boundary variables have been defined as "User Defined variables" in the test plan in this case too.

"db_queries_optimistic.txt" - contains the queries to alter the database schema to include the "version" attribute for all 
the entitites in the DB.
