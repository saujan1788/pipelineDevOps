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

         stage("Test Application"){
            steps{
             sh "mvn test"
            }
         }



        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv(credentialsID : 'sonar-qube') {
                 sh "mvn sonar:sonar"
             }
            }
        }

    }
}
