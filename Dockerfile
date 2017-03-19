FROM navicore/java:oracle-java8

MAINTAINER ed@onextent.com

EXPOSE 8080
COPY target/scala-2.11/*.jar /app/
WORKDIR /app
ENTRYPOINT ["java","-jar", "/app/oemap-server.jar", "-Xms128m", "-Xmx128m"]

