# Library API
## Description
Simple Library API consist 4 main actions :-
- Register borrower
- Register new book
- List all books and show its status
- Borrow books
- Return books
## Technology Stack

### Java 17
- **Java 17** is the latest long-term support (LTS) version of Java, providing new features, improvements, and security enhancements.
- Official Documentation: [Java 17 Documentation](https://docs.oracle.com/en/java/javase/17/)

### Docker
- **Docker** is used to containerize the application, ensuring consistency across different environments.
- Official Documentation: [Docker Documentation](https://docs.docker.com/)

### PostgreSQL 16
- **PostgreSQL 16** is a powerful, open-source object-relational database system, known for its robustness, scalability, and SQL compliance.
- Official Documentation: [PostgreSQL 16 Documentation](https://www.postgresql.org/docs/16/)

### Spring Boot
- **Spring Boot** simplifies the development of production-ready applications by providing a robust and flexible framework.
- Official Documentation: [Spring Boot Documentation](https://spring.io/projects/spring-boot)

### Gradle
- **Gradle** is used as the build tool for this project, offering powerful and flexible configuration
- Official Documentation: [Gradle Documentation](https://docs.gradle.org/)

## Prerequisites
Before you begis, ensure you have me the following requirement:-
* **Java Development Kit(JDK)**: Download and install JDK 11
* **Gradle**: Download and install Gradle 8
* **Docker and Docker Compose** : Download and Install Docker and Docker-Compose
* **(Optional)Postgresql 16**: This will download automatically by Docker.
## Getting Started
### Clone the Repository
First, clone the repository to your local machine using Git

### Run unit test
```
.\gradlew test
```
### Run integration test
```
.\gradlew integrationTest
```

### Build the Application
```
.\gradlew build
```
### Build the Docker Image
```
./gradlew bootBuildImage
```

### Run the Docker Container
```
docker-compose up
```
please look into docker-compose.yaml file in the directory to know more on how it was set
## API Documentation
### Base URL
The base URL for the API is: http://localhost:8080
### API Endpoints and Documentation
API definition can be found in this url http://localhost:8080/swagger-ui/index.html after service successfully running

## Sample Data
Sample data can be found inside sql folder. Just execute those .sql file using your preferable sql editor connect to library_db


