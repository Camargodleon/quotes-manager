FROM maven:3.8.5-openjdk-11 AS maven_build

WORKDIR /app

COPY pom.xml pom.xml
COPY src /app/src/
EXPOSE 8081
RUN mvn clean package -DSPRING_PROFILES_ACTIVE=prod

FROM eclipse-temurin:11

WORKDIR /app

EXPOSE 8081


COPY --from=maven_build app/target/QuotationManager.jar /app/QuotationManager.jar

ENTRYPOINT java -jar /data/hello-world-0.1.0.jar