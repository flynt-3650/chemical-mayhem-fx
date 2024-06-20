FROM eclipse-temurin:17-jdk-jammy
WORKDIR /cmf
COPY . .
RUN ./mvnw clean install
ENTRYPOINT ["java", "-jar", "target/cmf-1.0-SNAPSHOT-shaded.jar", "cli"]
