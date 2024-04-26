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

        stage("Build application") {
            steps {
                sh "mvn clean package"
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarQube') {
                    // No need to specify -Dsonar.projectKey or -Dsonar.java.binaries if they are in sonar-project.properties
                    sh "mvn sonar:sonar"
                }
            }
        }
    }
}
