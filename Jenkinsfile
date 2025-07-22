pipeline {
    agent any

    tools {
        maven 'Maven 3.8.6'
        jdk 'JDK 17'
    }

    environment {
        IMAGE_NAME = 'case-study3-app'
        CONTAINER_NAME = 'case-study3-container'
    }

    stages {
        stage('Clone Repository') {
            steps {
                git 'https://github.com/your-username/CaseStudy3.git'
            }
        }

        stage('Build Project with Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh "docker build -t $IMAGE_NAME ."
                }
            }
        }

        stage('Stop Existing Container') {
            steps {
                script {
                    sh """
                        docker stop $CONTAINER_NAME || true
                        docker rm $CONTAINER_NAME || true
                    """
                }
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
            echo '🎉 Deployment successful!'
        }
        failure {
            echo '❌ Build or deployment failed.'
        }
        cleanup {
            echo '🧹 Pipeline complete.'
        }
    }
}
