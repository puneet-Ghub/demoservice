pipeline {
    agent any

    environment {
        AWS_ACCESS_KEY_ID     = IAYEPACACNCK5IWUXJ
        AWS_SECRET_ACCESS_KEY = Na+PqvQijNPPT0VwDKGJeDK/T6pdfrPvyqYEc9Uo
        AWS_DEFAULT_REGION    = 'us-east-1'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'develop', url: 'https://github.com/puneet-Ghub/demoservice.git'
            }
        }

        stage('Terraform Init') {
            steps {
                 'terraform init'
            }
        }

        stage('Terraform Validate') {
            steps {
                 'terraform validate'
            }
        }

        stage('Terraform Plan') {
            steps {
                 'terraform plan -out=tfplan'
                archiveArtifacts artifacts: 'tfplan', fingerprint: true
            }
        }

        stage('Terraform Apply') {
            when {
                branch 'develop'
            }
            steps {
                input message: "Approve apply?"
                sh 'terraform apply -auto-approve tfplan'
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished.'
        }
    }
}