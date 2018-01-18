# Legacy Servlet

- uses Spring Framework 3
- uses Spring Security 3
- uses Spring XML for configuration
- uses Servlet Filter pattern for authorization
- uses Spring Session JDBC (test) v1.3


## Prerequisites

- mysql 5+ (use docker) with properties
  - url=jdbc:mysql://localhost:3306/sessiondb
  - database=sessiondb
  - username=root
  - password=welcome
  
  
  
### Tests

- *IT tests can only be run individually (Tomcat 7 hangs when opening/closing the tests)