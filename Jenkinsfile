pipeline {
    agent any

    environment {
        IMAGE_NAME = "arunubuntu/spring-boot-java"
        IMAGE_TAG  = "${BUILD_NUMBER}"
        EC2_HOST = "18.234.149.71"
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
                sh 'mvn clean package'
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

        stage('Docker Push') {
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

                        docker push ${IMAGE_NAME}:${IMAGE_TAG}
                        docker push ${IMAGE_NAME}:latest

                        docker logout
                    '''
                }
            }
        }

        stage('Deploy to EC2') {
            steps {
                sshagent(['ec2-ssh']) {
                    sh '''
                        ssh -o StrictHostKeyChecking=no ubuntu@${EC2_HOST} "
                            docker pull ${IMAGE_NAME}:${IMAGE_TAG} &&
                            docker rm -f spring-boot-app || true &&
                            docker run -d \
                                --name spring-boot-app \
                                -p 8081:8080 \
                                ${IMAGE_NAME}:${IMAGE_TAG}
                        "
                    '''
                }
            }
        }

        stage('Verify Deployment') {
            steps {
                sshagent(['ec2-ssh']) {
                    sh '''
                        ssh -o StrictHostKeyChecking=no ubuntu@${EC2_HOST} "
                            docker ps
                        "
                    '''
                }
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
