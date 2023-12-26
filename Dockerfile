FROM maven:3.9.6-amazoncorretto-17
VOLUME /tmp
USER root
WORKDIR /app
COPY . .
CMD ["mvn","clean","compile","exec:java"]
