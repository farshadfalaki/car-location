FROM openjdk:11.0-jre

LABEL maintainer="farshad.falaki@gmail.com"

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE=target/car-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} car.jar

ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://127.0.0.1:27017/car","-jar","/car.jar"]
