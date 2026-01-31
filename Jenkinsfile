pipeline {
    agent any

    tools {
        maven 'Maven'   // make sure Maven is configured in Jenkins Global Tools
        jdk 'JDK'       // make sure JDK is configured
    }

    environment {
        GRID_URL = "http://localhost:4444/wd/hub"
    }

    stages {

        stage('Checkout AmazonDockerGrid') {
            steps {
                git url: 'https://github.com/Shusmithag/DevOps-Assignment',
                    credentialsId: '8bb1d8e6-0723-451f-a475-3bd618553bf9'
            }
        }

        stage('Build Project') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Run Selenium Grid Tests') {
            steps {
                bat 'mvn test -Dgrid.url=%GRID_URL%'
            }
        }
    }

    post {
        always {
            echo "Test execution completed!"
            archiveArtifacts artifacts: '**/target/surefire-reports/*.xml', fingerprint: true
        }
        success {
            echo "✅ Tests Passed"
        }
        failure {
            echo "❌ Tests Failed"
        }
    }
}
