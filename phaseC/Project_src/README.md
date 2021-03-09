# Spring-Boot Plagiarism Detector

## Build Instructions
Project was built in Intellij using Java 8.

to build executable jar using maven
`<maven> clean install spring-boot:repackage`

to run jar
`java -jar target/plagiarism-detector-1.0-SNAPSHOT.jar`

Open up web browser to
`localhost:8080`

If 8080 in use, change port in application.properties.


## About
-> This application detects the plagiarism between two C++ or Python packages. Files of different languages cannot be compared.

-> Application reports the percentage of plagiarism detected and the similar lines of code which where detected.

-> If an error or exception occurs user will be redirected to an error page. The specific error message is only displayed in the terminal and not the browser.

## Instructions
-> User selects the language of the files to be processed. Currently supported languages are C++ and Python.

-> User uploads two different packages, either an individual file or selecting multiple files from the popup.

-> Python packages can only contain .py files.

-> C++ packages can only contain .cpp or .h files.

-> The application processes the packages and checks for plagiarism and reports on the next screen.

-> The percentages are given for every possible pair of files from package 1 and package 2.

-> The side-by-side line comparison is given wherever potential plagiarism is detected.

-> Percentage and similar lines of code are shown on next package.

## Future Development
Database persistence and support for professor login/registration, registration of students, storage of packages, and storage of plagiarism analysis.

reports is in development for version 0.0.2.


### Spring Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.1.RELEASE/maven-plugin/)
* [Thymeleaf](https://docs.spring.io/spring-boot/docs/2.2.1.RELEASE/reference/htmlsingle/#boot-features-spring-mvc-template-engines)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.1.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.2.1.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.2.1.RELEASE/reference/htmlsingle/#using-boot-devtools)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.2.1.RELEASE/reference/htmlsingle/#boot-features-security)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Managing Transactions](https://spring.io/guides/gs/managing-transactions/)
* [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)

