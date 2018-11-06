pipeline {
    agent {
        docker {
            image 'jamesdbloom/docker-java8-maven'
            args ' -v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Deliver') {
            steps {
                sh 'mvn -B -DskipTests clean package docker:build'
            }
        }
    }
}
