# Spring Boot CI/CD with Jenkins & Docker

A simple DevOps project that automates the build, test, Docker image creation, and deployment of a Spring Boot application using **Jenkins**.

## 🚀 Project Flow

```text
GitHub
   ↓
Jenkins
   ↓
Maven Build
   ↓
Maven Test
   ↓
Create JAR
   ↓
Docker Build
   ↓
Docker Hub
   ↓
Docker Container
   ↓
Spring Boot App
```

## 🛠️ Technologies

* Java 21
* Spring Boot
* Maven
* Git & GitHub
* Jenkins
* Docker
* Docker Hub
* Linux / WSL2

## 📁 Project Structure

```text
spring-boot-cicd/
├── src/
├── pom.xml
├── Dockerfile
└── Jenkinsfile
```

## 🔄 Jenkins Pipeline

The Jenkinsfile contains these stages:

1. **Checkout** – Gets code from GitHub.
2. **Build** – Builds the Spring Boot application using Maven.
3. **Test** – Runs application tests.
4. **Archive JAR** – Stores the generated JAR in Jenkins.
5. **Docker Build** – Creates a Docker image.
6. **Docker Push** – Pushes the image to Docker Hub.
7. **Deploy** – Runs the Docker container.
8. **Verify** – Checks that the container is running.

## 🐳 Docker

The Spring Boot JAR is packaged into a Docker image.

```bash
docker build -t spring-boot-cicd:latest .
```

Run the application:

```bash
docker run -d \
  -p 8081:8080 \
  --name spring-boot-app \
  spring-boot-cicd:latest
```

Application:

```text
http://localhost:8081
```

## 🎯 Project Goal

The main goal of this project is to learn how **Jenkins CI/CD and Docker can automate the build and deployment of a Spring Boot application**.

## 👨‍💻 Skills Demonstrated

**Java | Spring Boot | Maven | Git | GitHub | Jenkins | Docker | Docker Hub | Linux | CI/CD | DevOps**
