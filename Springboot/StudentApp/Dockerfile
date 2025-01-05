FROM amazoncorretto:21-alpine-jdk
ADD  target/student-app.jar student-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/student-app.jar"]