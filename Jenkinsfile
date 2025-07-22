pipeline {
    agent any

    environment {
        IMAGE_NAME = 'case-study3-app'
        CONTAINER_NAME = 'case-study3-container'
    }

    stages {
        stage('Clone Repository') {
            steps {
                git 'https://github.com/azmyra1011/event_register_system.git'
            }
        }

        stage('Build Project with Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t $IMAGE_NAME ."
            }
        }

        stage('Stop Existing Container') {
            steps {
                sh """
                    docker stop $CONTAINER_NAME || true
                    docker rm $CONTAINER_NAME || true
                """
            }
        }

        stage('Run Docker Container') {
            steps {
                sh "docker run -d -p 8080:8080 --name $CONTAINER_NAME $IMAGE_NAME"
            }
        }
    }

    post {
        success {
            echo '✅ Deployment successful!'
        }
        failure {
            echo '❌ Build failed.'
        }
    }
}
