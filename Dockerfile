FROM openjdk:latest
COPY ./target/seMethods-0.1.0.2-jar-with-dependencies.jar /tmp
COPY ./sqlqueries /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "seMethods-0.1.0.2-jar-with-dependencies.jar"]