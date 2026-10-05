pipeline {
    agent any

    environment {
        IMAGE_NAME = "arunubuntu/spring-boot-java"
        IMAGE_TAG = "${BUILD_NUMBER}"
    }

    tools {
        maven 'maven:3.10.0'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package '
            }
        }
      stage('Check Files') {
        steps {
        sh '''
            pwd
            echo "========== ROOT =========="
            ls -lah
            echo "========== JAR =========="
            find . -name "*.jar" -type f
            echo "========== DOCKERIGNORE =========="
            cat .dockerignore 2>/dev/null || echo "No .dockerignore"
        '''
    }
}
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Archive JAR') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t ${IMAGE_NAME}:${IMAGE_TAG} .'
                sh 'docker tag ${IMAGE_NAME}:${IMAGE_TAG} ${IMAGE_NAME}:latest'
            }
        }

        stage('Docker Login') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub-creds',
                        usernameVariable: 'DOCKER_USERNAME',
                        passwordVariable: 'DOCKER_PASSWORD'
                    )
                ]) {
                    sh '''
                        echo "$DOCKER_PASSWORD" | docker login \
                        -u "$DOCKER_USERNAME" \
                        --password-stdin
                    '''
                }
            }
        }

        stage('Docker Push') {
            steps {
                sh 'docker push ${IMAGE_NAME}:${IMAGE_TAG}'
                sh 'docker push ${IMAGE_NAME}:latest'
            }
        }

        stage('Image Deploy') {
            steps {
                sh 'docker rm -f spring-java || true'
                sh 'docker pull ${IMAGE_NAME}:${IMAGE_TAG}'
                sh 'docker run -d -p 8000:8080 --name spring-java ${IMAGE_NAME}:${IMAGE_TAG}'
            }
        }

        stage('Check Image') {
            steps {
                sh 'docker ps'
                sh 'docker images'
            }
        }
    }

    post {
        success {
            echo 'Spring Boot CI/CD pipeline completed successfully!'
            echo 'Application: http://localhost:8000'
        }

        failure {
            echo 'Pipeline failed. Check the Jenkins console output.'
        }
    }
}
