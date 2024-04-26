pipeline {
    agent {
        label "jenkins-agent"
    }

    tools {
        jdk 'Java21'
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
    }
}
