FROM openjdk:18-jdk-oracle
EXPOSE 8080
ADD target/DIPLOMA-cloud-storage-0.0.1-SNAPSHOT.jar cloud-storage.jar
ENTRYPOINT ["java", "-jar", "cloud-storage.jar"]