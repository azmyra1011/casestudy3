pipeline {
    agent {
         docker {
                        image 'docker:24.0.2-dind'
                        args '-u root --privileged -v /var/run/docker.sock:/var/run/docker.sock'
                    }
    }

    environment {
        IMAGE_NAME = "casestudy3"
        CONTAINER_NAME = "casestudy3"
        CONTAINER_PORT = "8080"
    }

    stages {
        stage('Build JAR') {
            agent {
                docker {
                    image 'maven:3.9.6-eclipse-temurin-17'
                }
            }
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            agent any
            steps {
                sh 'docker build -t ${IMAGE_NAME} .'
            }
        }

        stage('Run Docker Container') {
            agent any
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
