FROM openjdk:17
ADD target/JPAWorkout-docker.jar /JPAWorkout-docker.jar
ENTRYPOINT ["java","-jar","/JPAWorkout-docker.jar"]
