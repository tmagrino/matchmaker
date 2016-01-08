About source code:
==================
These folders are named according to the type of locking they contain. There are two versions, optimistic and pessimistic. Each version has 3 sub versions - 
1. Both Read and Write locks
2. Only read locks
3. Both kinds of locks, but the transactions are abstracted out to the jsp pages that call functions on the entities

- The "matchmaker-abstraction" folder contains a Locking class with variables readLockType and writeLockType. These can be changed and the lock type will take effect throughout the application. This code contains "version" attribute for all entities.
- The "pessimistic_matchmaker_writeonly" folder contains code without "version" entities and with only write locks.

Test plans:
-----------
There is a TestPlans folder containing a "Readme.txt" in it. That file explains how to use the jmeter test cases.

Known issues:
==============
1. On deploying optimistic locking code, the entities were getting initialized in the backend database but, the initial values weren't getting populated.
   To circumvent this issue, we were deploying the Pessimistic locking code first and then re-deploying the Optimistic locking code. The database schema could
   be altered to include a "version" entity by running the sql commands in "TestPlans/db_queries_optimistic".

2. When using the "matchmaker-abstraction", it is initially set to OPTIMISTIC lock mode. This can be changed to other modes but, enabling both PESSIMISTIC_READ and   PESSIMISTIC_WRITE locks caused a lot of deadlocks.

Most of the testing was done in pessimistic_matchmaker_writeonly and matchmaker-abstraction. There were exceptions and deadlocks observed when load testing and stress testing different lock combinations.
