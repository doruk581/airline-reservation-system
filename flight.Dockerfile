FROM adoptopenjdk/openjdk11:jre-11.0.2.9-alpine
ADD flight-management-service/build/libs/flight-management-service-all-1.0-SNAPSHOT.jar flight-management-service-all-1.0-SNAPSHOT.jar
RUN "pwd"
RUN "ls"
EXPOSE 4567
ENTRYPOINT ["java","-jar", "flight-management-service-all-1.0-SNAPSHOT.jar"]