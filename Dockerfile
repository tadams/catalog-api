FROM adoptopenjdk:11-jre-hotspot-bionic

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} catalog-service.jar

ENTRYPOINT ["java", "-jar", "catalog-service.jar"]