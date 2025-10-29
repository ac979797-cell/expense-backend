# -------- Stage 1: Build the Spring Boot jar --------
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# copy Maven wrapper and config first
COPY pom.xml mvnw ./
COPY .mvn .mvn

# make sure mvnw is executable and warm up dependencies
RUN chmod +x mvnw && ./mvnw dependency:go-offline -DskipTests

# now copy the source code
COPY src src

# build the jar
RUN ./mvnw clean package -DskipTests

# -------- Stage 2: Run the jar --------
FROM eclipse-temurin:17-jre
WORKDIR /app

# copy the jar we just built
COPY --from=build /app/target/*.jar app.jar

# Render (and similar platforms) route traffic to your $PORT.
# We'll expose 8080 by default, and also allow overriding via env.
ENV PORT=8080
EXPOSE 8080

CMD ["java", "-jar", "app.jar", "--server.port=${PORT}"]
