<<<<<<< HEAD
"# java-project-jenkins" 
=======
# Spring Boot DevOps App

A small Spring Boot (Java 17, Maven) REST app for practicing Jenkins CI/CD.

## Endpoints
| Route | Purpose |
|-------|---------|
| `/` | Welcome message and version |
| `/api/greet?name=Arun` | Greeting JSON |
| `/api/info` | App name, version, environment, hostname |
| `/actuator/health` | Health check |

## Build, test, run
```bash
mvn clean test          # run unit tests
mvn clean package       # build target/app.jar
java -jar target/app.jar
```
Open http://localhost:8080

## Docker
```bash
docker build -t springboot-devops-app .
docker run -d -p 8080:8080 -e APP_ENV=production springboot-devops-app
```

## Typical Jenkins stages
Checkout -> `mvn clean test` -> `mvn package` -> Docker build -> push image -> deploy
>>>>>>> 7efc80a (first commit)
