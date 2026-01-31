pipeline {
    agent any

    tools {
        maven 'Maven'   // must match Global Tool name
        jdk 'JDK'      // must match Global Tool name
    }

    environment {
        GRID_URL = "http://localhost:4444/wd/hub"
    }

    stages {

        stage('Checkout Project') {
            steps {
                git branch: 'main',
                    credentialsId: '8bb1d8e6-0723-451f-a475-3bd618553bf9',
                    url: 'https://github.com/Shusmithag/DevOps-Assignment.git'
            }
        }

        stage('Build Project') {
            steps {
                bat 'mvn -version'
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

