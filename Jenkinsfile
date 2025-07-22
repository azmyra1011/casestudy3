pipeline {
    agent any

    stages {
        stage('Build Project with Maven') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t casestudy3:latest .'
            }
        }

        stage('Stop Existing Container') {
            steps {
                sh 'docker stop casestudy3 || true'
                sh 'docker rm casestudy3 || true'
            }
        }

        stage('Run Docker Container') {
            steps {
                sh 'docker run -d -p 8080:8080 --name casestudy3 casestudy3:latest'
            }
        }
    }

    post {
        failure {
            echo '❌ Build failed.'
        }
        success {
            echo '✅ Build and deployment succeeded!'
        }
    }
}
