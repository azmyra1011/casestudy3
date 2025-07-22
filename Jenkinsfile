pipeline {
    agent any

    environment {
        IMAGE_NAME = "casestudy3"
        CONTAINER_NAME = "casestudy3_container"
        PORT = "8082"
    }

    stages {
        stage('Build Project with Maven') {
            steps {
                sh 'docker run --rm -v "$PWD":/usr/src/app -w /usr/src/app maven:3.9.4-eclipse-temurin-17 mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $IMAGE_NAME:latest .'
            }
        }

        stage('Stop Existing Container') {
            steps {
                sh '''
                    if [ "$(docker ps -aq -f name=$CONTAINER_NAME)" ]; then
                        docker stop $CONTAINER_NAME || true
                        docker rm $CONTAINER_NAME || true
                    fi
                '''
            }
        }

        stage('Run Docker Container') {
            steps {
                sh 'docker run -d --name $CONTAINER_NAME -p $PORT:8080 $IMAGE_NAME:latest'
            }
        }
    }

    post {
        failure {
            echo '❌ Build failed.'
        }
        success {
            echo '✅ Build succeeded.'
        }
    }
}
