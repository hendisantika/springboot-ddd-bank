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
## Which DDD principles are implemented?

- Modelling the domain layer as one package, which does not depend on any other package besides standard Java SE packages as `java.time` and `javax.persistence`. The latter only for the JPA annotations.

- Avoid an [anemic domain model](https://martinfowler.com/bliki/AnemicDomainModel.html) by having relevant business logic methods in entity class `Client`.
  This requires the feature **Domain Object Dependency Injection** (DODI), which can only be implemented by using full AspectJ compile-time weaving.
  See [§11.8.1 Using AspectJ to dependency inject domain objects with Spring](http://docs.spring.io/spring/docs/4.3.x/spring-framework-reference/html/aop.html#aop-atconfigurable).

- The Domain Layer references required services only by self-defined, minimal interfaces (in package `domain.imports`).

- Implementing required services in the infrastructure layer (in package `infrastructure`).

- Linking together required services and their implementations by Dependency Injection.

- Implementing an interface layer for external access to the application.
  This is implemented as a REST service in package `rest_interface`.
