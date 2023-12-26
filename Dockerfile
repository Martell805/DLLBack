FROM openjdk:17
VOLUME /tmp
USER root
WORKDIR /app
COPY target/DLL-1.0.jar app.jar
CMD ["java","-jar","app.jar"]
