-- If we create an sql file name data.sql in the resource section of our project then during the
-- start of our application all the sql written in this file will be executed into the embedded h2 DB

-- We will initialize the H2 DB with a Person table and also populate some data

-- As H2 is an in memory database that's why whenever we restart the application all data are lost

-- Query to create a person table during application startup

/*
This following create table query won't be needed for H2 or ay other in-memory db, when
we are using JPA because Spring boot auto-configuration will automatically search the classpath for several
entity classes and create the tables for us if it finds that we are using an in-memory db in our class path like H2.
*/


/*CREATE TABLE PERSON(
id varchar (255) not null ,
name varchar(255) not null,
location varchar(255),
birth_date timestamp ,
PRIMARY KEY (id)
);
*/
-- query to populate the person table with some initial data

INSERT INTO PERSON(id,name,location,birth_date) values (1001,'Priyojit Pal','Kolkata','1993-06-08');
INSERT INTO PERSON(id,name,location,birth_date) values (1002,'Sagarika Chanani','Kolkata','1995-01-19');
INSERT INTO PERSON(id,name,location,birth_date) values (1003,'Rajesh De','Kolkata','1993-04-08');
INSERT INTO PERSON(id,name,location,birth_date) values (1004,'Rahul Bose','Jaipur','1993-01-18');
INSERT INTO PERSON(id,name,location,birth_date) values (1005,'Niraj Pandey','Patna','1993-04-28');
