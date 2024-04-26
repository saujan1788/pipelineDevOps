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
                    // Assuming this is correctly pointed to the SonarQube environment configured in Jenkins
                    // This should run after 'mvn clean package' has compiled the Java files
                    sh "mvn sonar:sonar -Dsonar.projectKey=product-service -Dsonar.java.binaries=target/classes"
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
