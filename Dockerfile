FROM eclipse-temurin:17-jdk-jammy
WORKDIR /cmf
COPY . .
RUN ./mvnw clean install
ENTRYPOINT ["java", "-jar", "target/ChemicalMayhemFX-1.0-shaded.jar", "cli"]
