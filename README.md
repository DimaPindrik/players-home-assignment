# Getting Started

To deploy follow the instructions:
* Build Docker image: `docker build -t yourapp:latest .`
* Run the Docker container: `docker run -p 8080:8080 yourapp:latest`
* This command maps port 8080 on your local machine to port 8080 in the Docker container, allowing you to access the Spring Boot application at http://localhost:8080.

# API Usage Examples

* GET all players: `curl --location 'http://localhost:8080/api/players/'`
* GET player by id: `curl --location 'http://localhost:8080/api/players/aardsda01'`