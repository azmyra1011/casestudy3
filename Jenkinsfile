pipeline {
    agent {
        docker {
            image 'docker:24.0.2-dind'
            args '-u root --privileged -v /var/run/docker.sock:/var/run/docker.sock'
        }
    }

    environment {
        MONGO_CONTAINER = "mongo"
        APP_IMAGE = "casestudy3"
        APP_PORT = "8080"
    }

    stages {
        stage('Start MongoDB') {
            steps {
                sh '''
                docker network create casestudy3-network || true
                docker run -d --rm --network casestudy3-network --name ${MONGO_CONTAINER} -p 27017:27017 mongo:latest
                '''
            }
        }

        stage('Install Java & Maven, then Build JAR') {
            steps {
                sh '''
                apk add --no-cache openjdk17 maven
                mvn clean package -DskipTests
                '''
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${APP_IMAGE} ."
            }
        }

        stage('Run Docker Container') {
            steps {
                sh '''
                docker run -d --rm --network casestudy3-network -p ${APP_PORT}:8080 -e SPRING_DATA_MONGODB_URI=mongodb://${MONGO_CONTAINER}:27017/CaseStudy3 ${APP_IMAGE}
                '''
            }
        }
    }
}