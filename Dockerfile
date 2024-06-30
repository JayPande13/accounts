# Importing Dokcer Official Image of Java 21 - to this projects docker image.
FROM openjdk:21-jdk-slim

# Who is maintaining this Docker
MAINTAINER jay-Pande

# Here we are telling that there is a jar file present inside this location and also please copy same file to docker image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

# Providing java commands to run this jar file (which we also executed in cmd : java -jar accounts-0.0.1-SNAPSHOT.jar, We are prociding these seperetely in "" because there is space between all of them.)
ENTRYPOINT ["java","-jar","accounts-0.0.1-SNAPSHOT.jar"]