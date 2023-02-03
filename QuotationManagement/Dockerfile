FROM openjdk:11-jdk

WORKDIR /app

COPY target/QuotationManager.jar app/QuotationManager.jar

EXPOSE 8081

CMD ["java", "-jar","app/QuotationManager.jar"]