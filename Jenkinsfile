pipeline {
    agent any

    environment {
        IMAGE_NAME = "casestudy3"
        CONTAINER_NAME = "casestudy3"
        CONTAINER_PORT = "8080"
    }

    stages {
        stage('Build JAR') {
            steps {
                sh '''
                    sudo apt update && sudo apt install -y maven openjdk-17-jdk
                    mvn clean package -DskipTests
                '''
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t ${IMAGE_NAME} .'
            }
        }

        stage('Run Docker Container') {
            steps {
                sh '''
                    docker stop ${CONTAINER_NAME} || true
                    docker rm ${CONTAINER_NAME} || true
                    docker run -d -p 8080:8080 --name ${CONTAINER_NAME} ${IMAGE_NAME}
                '''
            }
        }
    }
}
