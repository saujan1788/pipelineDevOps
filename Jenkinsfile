pipeline {
    agent any

    tools {
        jdk 'Java17'
        maven 'Maven3'
    }

    stages {
        stage("Clean Workspace") {
            steps {
                cleanWs()
            }
        }

        stage("Checkout from SCM") {
            steps {
                git branch: 'main', url: 'https://github.com/saujan1788/pipelineDevOps'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('Your-Sonar-Env-Here') {
                    sh "mvn clean verify sonar:sonar -Dsonar.projectKey=product-service -Dsonar.java.binaries=target/classes"
                }
            }
        }

    }
}
