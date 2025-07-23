pipeline {
    agent {
        docker {
            image 'docker:24.0.2-dind'
            args '-u root --privileged -v /var/run/docker.sock:/var/run/docker.sock'
        }
    }

    environment {
        NETWORK_NAME = 'casestudy3-network'
        MONGO_CONTAINER = 'mongodb'
        APP_IMAGE = 'casestudy3'
        APP_CONTAINER = 'casestudy3-app'
    }

    stages {
        stage('Install Java & Maven, then Build JAR') {
            steps {
                sh '''
                apk add --no-cache openjdk17 maven
                mvn clean package -DskipTests
                '''
            }
        }

        stage('Build App Docker Image') {
            steps {
                sh 'docker build -t $APP_IMAGE .'
            }
        }

        stage('Create Docker Network') {
            steps {
                sh '''
                docker network ls | grep $NETWORK_NAME || docker network create $NETWORK_NAME
                '''
            }
        }

        stage('Start MongoDB') {
            steps {
                sh '''
                docker run -d --rm --name $MONGO_CONTAINER --network $NETWORK_NAME -p 27017:27017 mongo:latest
                '''
            }
        }

        stage('Run App Container') {
            steps {
                sh '''
                docker run -d --rm --name $APP_CONTAINER --network $NETWORK_NAME -p 8080:8080 $APP_IMAGE
                '''
            }
        }
    }

    post {
        always {
            echo "Pipeline completed. MongoDB and App are running in same Docker network."
        }
    }
}
