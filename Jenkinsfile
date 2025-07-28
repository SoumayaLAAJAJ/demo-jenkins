pipeline {
    agent any

    stages {
        stage('Télécharger') {
            steps {
              git branch: 'master', url: 'https://github.com/SoumayaLAAJAJ/demo-jenkins.git'
            }
        }
        stage('Compilation') {
            steps {
                bat "mvn clean compile -DskipTests"
            }
        }
        stage('Tests') {
            steps {
                bat "mvn test"
            }
            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }
        stage('Packaging') {
            steps {
                bat "mvn package -DskipTests"
            }
        }
        stage('Creation image') {
            steps {
                bat "docker build -t wisdom-pet ."
            }
        }
        stage ('Stop'){
            steps {
                bat "docker stop demo-isika || exit /b 0"
            }
        }
        stage('Run container') {
            steps {
                bat "docker run -d --rm -p 8180:8180 --network isika-net --name demo-isika wisdom-pet"
            }
        }

    }
}