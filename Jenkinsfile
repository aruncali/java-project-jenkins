pipeline {
    agent any

    stages {
        stage('Checkout') { 
          steps { checkout scm } }
            }
        }
    stage('Build'){
       steps { 
        sh 'mvn clean package '
          } 
        }
    stage('Test') {
        steps {
         sh 'mvn test' 
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
