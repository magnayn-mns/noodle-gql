FROM openjdk:17
RUN mkdir /app
COPY ./target/noodle*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
