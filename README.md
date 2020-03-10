# springboot-ddd-bank
## Project Spring DDD Bank
=======================
A sample project following Domain Driven Design with Spring Data JPA

                                            (C) Christoph Knabe, 2017-03-17 ... 2018-10-11

In this project I am trying to apply principles of Domain Driven Design.
In contrary to full-blown DDD examples on the web I am applying here some simplifications.
This sample application is used for a course on Software Engineering at Beuth University of Applied Sciences Berlin.

This project uses

- JDK 8
- Maven 3
- Spring Boot
- Spring Data + JPA + Hibernate + Derby
- AspectJ Compile Time Weaving for main sources
- `springfox-swagger` for generating documentation and user interface for the REST service
- JUnit 4
- The Exception Handling and Reporting Framework MulTEx

Detailed version indications you can find in the file `pom.xml`.

By  `mvn clean test`   all necessary libraries will be fetched, the project will be compiled, exception message texts will be collected and the test suite will be executed.

After this is working you can import the Maven project into your Java IDE
(Spring Tool Suite is recommended, as AspectJ weaving is needed for the compile phase).

You can run the application (a REST server) in your IDE by running class `de.beuth.knabe.spring_ddd_bank.Application` or on the command line after `mvn package` by `java -jar target/spring-ddd-bank-0.1-SNAPSHOT.jar`. In the last lines of the log you will see the number of the port (usually 8080), on which the server will listen. You can stop it by typing &lt;Ctrl/c&gt;.
