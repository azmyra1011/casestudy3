pipeline {
    agent {
        docker {
            image 'docker:24.0.2'
            args '-u root --privileged -v /var/run/docker.sock:/var/run/docker.sock'
        }
    }

    environment {
//         MONGO_CONTAINER = "mongo"
        IMAGE_NAME = "casestudy3"
        CONTAINER_NAME = "casestudy3"
        CONTAINER_PORT = "8080"
    }

   stages {
           stage('Build JAR') {
               steps {
                   sh '''
                           apk add --no-cache openjdk17 maven

                   mvn clean package -DskipTests
                   '''
               }
           }

           stage('Build Docker Image') {
               steps {
                   sh "docker build -t ${IMAGE_NAME} ."
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