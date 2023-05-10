# booking-platform
# Getting Started

### Swagger Documentation
* [Swagger UI](http://localhost:8080/swagger-ui/index.html)
* [Swagger API Docs](http://localhost:8080/v3/api-docs)

### Actuator Link
* [Actuator](http://localhost:8080/actuator)

### Authentication
* Spring security basic authenticate is implemented to secure rest end points.
* Excluded Swagger and actuator endpoints
* Use basic authentication with user:password for calling rest api.

### Circuit Breaker Resilience
* Circuit breaker pattern is implemented while calling a custom dummy movie review service

### Test links
* [Read Scenario: Get a movie in an area for specific time](http://localhost:8080/platform/cinema/movie/MOVIE1?dateTime=2023-05-10T13:00:00&pinCode=411030)
* [Write Scenario: To book a seat for a movie in a cinema at specific time](http://localhost:8080/platform/booking/user/401/movie/601/cinema/701/datetime/2023-05-10T13:00:00/payment/1101/seat/45)

### Client class for demo booking
* com.sap.bookingplatform.client.MovieBookingClient

### Class diagram and entities diagram
* Class Diagram location:  Class Diagram/booking-platform_class_diagram.png.png
* Entities diagram location:  Class Diagram/booking-platform_entities_class_Diagram.png.png

### Add below VM argument to make ehcache work with Java17
* --add-opens java.base/java.lang=ALL-UNNAMED
* --add-opens java.base/java.util=ALL-UNNAMED
* --add-opens java.base/java.time=ALL-UNNAMED

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.6/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.6/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.0.6/reference/htmlsingle/#using.devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.0.6/reference/htmlsingle/#web)
* [Spring HATEOAS](https://docs.spring.io/spring-boot/docs/3.0.6/reference/htmlsingle/#web.spring-hateoas)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.0.6/reference/htmlsingle/#data.sql.jpa-and-spring-data)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/3.0.6/reference/htmlsingle/#actuator)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Building a Hypermedia-Driven RESTful Web Service](https://spring.io/guides/gs/rest-hateoas/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
