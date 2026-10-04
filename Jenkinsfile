pipeline {
    agent any
    environment {
        IMAGE_NAME ="${{ vars.NAME }}/spring-boot-java"
        IMAGE_TAG = "${2.0}"
    }
    tools{
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
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true } 
             }
        
      stage(' docker build'){
          steps{
              sh 'docker build -t ${IMAGE_NAME}:${IMAGE_TAG} .'}
      }
      stage('image deploy'){
         steps{
            sh 'docker rm -f ${IMAGE_NAME} || true'
            sh 'docker run -d -p 8000:8000 --name spring-java ${IMAGE_NAME}:${IMAGE_TAG}'
            }
        }
        stage('check image'){
            steps{
                sh 'docker ps'
                sh 'docker images'
            }
        }
    }
    post {
        success {
            echo 'Spring Boot CI pipeline completed successfully!'
        }

        failure {
            echo 'Pipeline failed. Check the Jenkins console output.'
        }
    }
}
