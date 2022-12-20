FROM openjdk:18
COPY /target/Project-0.0.1-SNAPSHOT.jar Project-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/Project-0.0.1-SNAPSHOT.jar"]