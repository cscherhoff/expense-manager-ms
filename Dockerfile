FROM openjdk:17
ADD target/expense-manager-ms-0.0.1-SNAPSHOT.jar expense-manager-ms-0.0.1-SNAPSHOT.jar
RUN mkdir export
RUN mkdir properties
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "expense-manager-ms-0.0.1-SNAPSHOT.jar"]
LABEL prune=true
