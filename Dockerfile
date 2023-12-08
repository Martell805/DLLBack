FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
WORKDIR /app
COPY target/DLL-1.0.jar app.jar
CMD ["java","-jar","app.jar"]
