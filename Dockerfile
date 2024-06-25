FROM amazoncorretto:21
ARG JAR_FILE=build/libs/authJWT-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} authentication.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "authentication.jar" ]