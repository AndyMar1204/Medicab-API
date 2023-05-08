FROM openjdk:8
EXPOSE 8080
ADD target/medicab2API-2.jar medicab2API-2.jar
ENTRYPOINT ["java","-jar","/medicab2API-2.jar"]