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
                    c
