FROM openjdk:17
RUN mkdir /app
COPY ./target/noodle*.jar /app/app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
