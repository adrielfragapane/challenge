FROM openjdk:8 
ADD target/challenge.jar challenge.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "challenge.jar"]

