FROM gradle:8.1.1-jdk17 AS builder
WORKDIR /app
COPY . .
RUN ./gradlew shadowJar

FROM eclipse-temurin:17
WORKDIR /app
COPY --from=builder /app/build/libs/HelloWorld-1.0-SNAPSHOT-all.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
