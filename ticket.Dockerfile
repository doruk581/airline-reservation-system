FROM adoptopenjdk/openjdk11:jre-11.0.2.9-alpine
ADD ticket-reservation-service/build/libs/ticket-reservation-service-all-1.0-SNAPSHOT.jar ticket-reservation-service-all-1.0-SNAPSHOT.jar
RUN "pwd"
RUN "ls"
EXPOSE 4567
ENTRYPOINT ["java","-jar", "ticket-reservation-service-all-1.0-SNAPSHOT.jar"]