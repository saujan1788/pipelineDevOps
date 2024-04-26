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

        stage("Check for Class Files") {
            steps {
                sh "ls -la target/classes"
            }
        }


        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarQube') {
                    // Assuming this is correctly pointed to the SonarQube environment configured in Jenkins
                    // This should run after 'mvn clean package' has compiled the Java files
                    sh "mvn sonar:sonar -Dsonar.projectKey=product-service -Dsonar.host.url=http://34.245.33.167:9000 -Dsonar.login=sqa_2a7ac5b9226a882d5f8c6dbc58cd756f9ef0b02e"
                }
            }
        }
    }

    post {
        always {
            // This will archive the compiled binaries even if the build or analysis fails
            archiveArtifacts artifacts: 'target/**/*', allowEmptyArchive: true
        }
    }
}
