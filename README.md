# LoanApp-RESTful
Application was Developed on Java 14.0.2 using IntelliJ idea IDE and Mysql database.
Usage:

database setup will be completed by the application through liquibase and h2 inmemory database
to access the database go to http://localhost:8080/h2-console/ 

JDBC URL: "jdbc:h2:mem:test"
, User Name: "sa"
, Password: "password"

1. Once you have the project open in your IDE you can launch The application using LaunchApp.java to ensure 
that everything is working correctly run the tests in the test directory.
The RestTemplate tests require the application to be running in order for them to pass.

2. if all tests have passed you can continue to manually test/play around with the available options for 
the RESTful api by going to http://localhost:8080/swagger.html in your browser.

To see that the web service performs country resolution you can check ClientHttp.java 
or by performing a post request through swagger and checking the request_limiter table in you database
if you execute 5 or more posts for /loan/ in less than 30 seconds you will get a different Json response body
with a message saying that there are too many incoming requests, this works per country.
