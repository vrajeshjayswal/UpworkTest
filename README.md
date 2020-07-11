Online learning portal – REST API for Price of Cource

This application is consist of two projects:

1) ConfigServer: This configuration server loads application specific configuration from properties file from the GitHub repository and provides to its client (Spring boot API project)
2) CoursePricing: This is REST API project. It has been configured through bootstrap.properties to load its configuration from ConfigServer. So whenever you want to change any of the properties in CoursePriceApi-development.properties, do the changes and push it to the GitHub.  Also when properties are modified in GitHub, no need to re-start REST API. Only call actuator/refresh endpoint of REST API so it will load latest properties from GitHub via ConfigServer. This is also useful for global configuration or default values. No need to hard code any properties in REST API project.

Steps to test:
1) Clone from repository from https://github.com/vrajeshjayswal/UpworkTest.git
2) Start MySQL server and create database and insert test data executing script from dbscript.sql file
3) Open command prompt and go to ConfigServer directory and run following command to start the configuration server
mvnw clean spring-boot:run
4) Open another command prompt and go to CoursePricing directory and run following command to start the REST API application.
mvnw clean spring-boot:run
5) Execute following curl commands to test the REST API
             In URL two path parameter needs to be pass – first courseId and second countryId
To get price breakup of the Course:  curl http://localhost:8083/base-price/5/1 
To get price breakup of the Course: curl http://localhost:8083/price-breakup/5/1 
To get whole price of the Course: curl  http://localhost:8083/whole-price/5/1 

6) To change the default value of paymentStrategy , modify properties file at following location.
https://github.com/vrajeshjayswal/globalconfiguration.git
7) Call following refresh end point of REST API to load latest properties from GitHub via Config-server.
http://localhost:8083/actuator/refresh 
To test if new properties have been read from GitHub or not, execute following Curl command.
curl http://localhost:8083/base-price/5/1

Database used: MySQL
Since there is relationship between different tables and data needs to be structural, I have chosen RDBMS (MySQL).
Database script provided in dbscript.sql file which contains both – database creation scripts and data insert scripts

Assumptions:
1) As mentioned in problem-statement, there is no restriction for accessing REST API.
2) Since User is not authenticated before calling this REST API, the API can't identify the user and its details like country-id. That is why country-id has been pass into URL path. In URL path first value is courseId and second value is countryId.
3) Junit test case are developed for Service class only. Integration test cases for Controller class are not developed for this exercise. But I know how to develop it.
