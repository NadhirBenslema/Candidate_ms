FROM openjdk:8
EXPOSE 8085
ADD target/ms_candidate-0.0.1-SNAPSHOT.jar ms_candidate.jar
ENTRYPOINT ["java", "-jar", "ms_candidate.jar"]
