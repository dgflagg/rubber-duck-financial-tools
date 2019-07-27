# 'build' stage builds using maven 3.6 and jdk 12
FROM maven:3.6-jdk-12 AS build

# copy the pom and source into the container for only the build stage
COPY pom.xml .
COPY src src/

# create jar using the maven package command
RUN mvn package


# use openjdk version 12 alpine base image
FROM openjdk:12-alpine

# copy only the uber jar into the image from the build stage
COPY --from=build target/rubber-duck-financial-tools-*.jar /app/app.jar

# advertise that port 8081 should be used
EXPOSE 8081

# set the workdir to the directory with the jar
WORKDIR /app

# run the from the jar
CMD ["java", "-jar", "app.jar"]