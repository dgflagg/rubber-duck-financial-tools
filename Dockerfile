# use openjdk version 12 base image
FROM openjdk:12

# copy only the uber jar into the image
COPY target/rubber-duck-financial-tools-*.jar /usr/src/app.jar

# advertise that port 8081 should be used
EXPOSE 8081

# set the workdir to the directory with the jar
WORKDIR /usr/src

# run the from the jar
CMD ["java", "-jar", "app.jar"]