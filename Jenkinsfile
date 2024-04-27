
pipeline {
    agent any

    tools {
        jdk 'Java17'
        maven 'Maven3'
    }

    stages {
        stage("Checkout from SCM") {
            steps {
                git branch: 'main', url: 'https://github.com/saujan1788/pipelineDevOps'
            }
        }

        stage("Build application") {
            steps {
                sh "mvn clean package"
            }
        }

        stage('SonarQube Analysis') {
            steps {
                // 'sonarQube' should match the name configured in Jenkins for SonarQube servers
                withSonarQubeEnv('sonarQube') {
                    sh "mvn sonar:sonar"
                }
            }
        }
    }
}
