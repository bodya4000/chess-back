FROM openjdk
COPY target/project-chess-back-0.0.1-SNAPSHOT.jar chess.jar
CMD ["java", "-jar", "chess.jar"]