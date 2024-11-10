FROM openjdk:17-jdk-alpine
EXPOSE 8089
ADD target/Tp-Foyer-5.0.0.jar Tp-Foyer-5.0.0.jar
ENTRYPOINT ["java","-jar","/Tp-Foyer-5.0.0.jar"]