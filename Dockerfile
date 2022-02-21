FROM openjdk:17
COPY target/customer-complaints*.jar /usr/src/customer-complaints.jar
COPY src/main/resources/application.properties /opt/conf/application.properties
CMD ["java", "-jar", "/usr/src/customer-complaints.jar", "--spring.config.location=file:/opt/conf/application.properties"]