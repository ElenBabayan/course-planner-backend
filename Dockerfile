# ---------- build stage ----------
FROM maven:3.9.7-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -q clean package -DskipTests

# ---------- run stage ----------
FROM eclipse-temurin:21-jre
WORKDIR /opt/app
COPY --from=builder /app/target/course-planner-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
