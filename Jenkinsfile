pipeline {
    agent any

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
              sh 'docker build -t spring-java:latest .'}
      }
      stage('image deploy'){
         steps{
            sh 'docker rm -f spring-java || true'
            sh 'docker run -d -p 8000:8000 --name spring-java spring-java:latest'
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
