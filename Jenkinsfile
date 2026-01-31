pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Shusmitha/AmazonDockerGrid.git'
            }
        }

        stage('Run Selenium Grid Tests') {
            steps {
                bat 'mvn clean test'
            }
        }
    }

    post {
        always {
            echo 'Test execution completed!'
            archiveArtifacts artifacts: 'test-output/**', fingerprint: true
        }
    }
}
