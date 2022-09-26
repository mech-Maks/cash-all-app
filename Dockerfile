FROM openjdk:17-alpine

ENV DB_HOST jdbc:postgresql://db:5432/postgres
ENV DB_NAME postgres
ENV DB_USERNAME postgres
ENV DB_PASSWORD 12345

ARG JAR_FILE=build/libs/com.tinkoff.financial-tracker-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","-XX:+UseSerialGC","-Xss512k","-XX:MaxRAM=512m","/app.jar"]