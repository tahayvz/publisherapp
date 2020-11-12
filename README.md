# Spring Publisherapp:

The application is a Spring Boot web application

## Tech stack & Open-source libraries
===================
This is a basic Java web application projects. This template uses the following foundational technologies:

* 	[Maven](https://maven.apache.org/) - Dependency Management
* 	[JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java™ Platform, Standard Edition Development Kit
* 	[Spring Boot](https://spring.io/projects/spring-boot) makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run"
* 	[Spring Security](https://spring.io/projects/spring-security) is a powerful and highly customizable authentication and access-control framework. It is the de-facto standard for securing Spring-based applications.
* 	[H2 Database Engine](https://www.h2database.com/html/main.html) - Very fast, open source, JDBC API. Embedded and server modes; in-memory databases
* 	[Bootstrap](https://getbootstrap.com/) - Bootstrap is a free and open-source CSS framework directed at responsive, mobile-first front-end web development.
* 	[Thymeleaf](https://www.thymeleaf.org/) - Modern server-side Java template engine for both web and standalone environments. 	
* 	[Lombok](https://projectlombok.org/) - Never write another getter or equals method again, with one annotation your class has a fully featured builder, Automate your logging variables, and much more.
* 	[Hibernate Validator](https://hibernate.org/validator/) - Express validation rules in a standardized way using annotation-based constraints and benefit from transparent integration with a wide variety of frameworks.

## Getting Your Development Environment Setup
### Recommended Versions
 | Recommended | Reference
| ----------- | ---------
| Oracle Java 11 JDK | [Download](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) |
| Maven 3.6.3 or higher | [Download](https://maven.apache.org/download.cgi) | [Installation Instructions](https://maven.apache.org/install.html)|
| Spring Boot 2.3 or higher | [What's new](https://spring.io/blog/2020/10/29/spring-boot-2-3-5-available-now) 

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.tahayvz.publisherapp.PublisherappApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
$ git clone https://github.com/tahayvz/publisherapp.git
$ cd publisherapp
$ mvn spring-boot:run
```

To get the code:
-------------------
Clone the repository:

    $ git clone https://github.com/tahayvz/publisherapp.git

If this is your first time using Github, review https://docs.github.com/en/free-pro-team@latest/github/creating-cloning-and-archiving-repositories/cloning-a-repository to learn the basics.