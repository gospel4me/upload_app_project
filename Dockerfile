FROM openjdk:11
ADD target/upload_app.jar upload_app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","upload_app.jar"]