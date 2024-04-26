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

        post {
            always {
                archiveArtifacts artifacts: 'target/**/*', allowEmptyArchive: true
            }
        }


        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarQube') {
                    sh "mvn sonar:sonar -Dsonar.projectKey=product-service -Dsonar.java.binaries=target/classes"
                }
            }
        }
    }
}
