# Getting Started

To Run locally run:
* `mvn spring-boot:run`

To deploy follow the instructions:
* Build Docker image: `docker build -t yourapp:latest .`
* Run the Docker container: `docker run -p 8080:8080 yourapp:latest`
* This command maps port 8080 on your local machine to port 8080 in the Docker container, allowing you to access the Spring Boot application at http://localhost:8080.

# API Usage Examples

* GET all players: `curl --location 'http://localhost:8080/api/players/'`
* GET player by id: `curl --location 'http://localhost:8080/api/players/aardsda01'`

# Things I would consider improving if I had more time:
1. Introduce logging via AOP class for all controller/service classes
2. Remove opencsv lib and implement csv reader internally (check if possible)
3. Introduce pagination for the response for getAllPlayers (response too big)

# Things I needed more time to finish:
1. Complete unit tests
2. Create a clear interface for the CSV reader component in order to replace it with JPA data store in future