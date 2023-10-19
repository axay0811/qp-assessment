FROM openjdk:17
EXPOSE 8080
ADD target/spring-qp-assignment.jar.jar spring-qp-assignment.jar
ENTRYPOINT [ "java", "jar", "/spring-qp-assignment.jar"]