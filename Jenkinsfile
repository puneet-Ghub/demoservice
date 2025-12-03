pipeline {
    agent any // Defines where the pipeline will run (e.g., on any available agent)

    tools {
        // Specify the Maven tool configuration defined in Jenkins Global Tool Configuration
        maven 'maven-3.8.6' // Replace with your Maven tool name
        jdk 'jdk-21' // Replace with your JDK tool name
    }

    stages {
       stage('Checkout Source Code') {
            steps {
                // Clones the Git repository
                git url: 'https://github.com/your-username/your-java-project.git', // Replace with your repository URL
                    branch: 'develop' // Replace with your target branch
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests' // Builds the project, skipping tests
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test' // Runs the project's unit tests
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package' // Packages the application (e.g., creates a JAR/WAR)
            }
        }
      stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('local-sonarqube') { // Replace with the name of your SonarQube server configured in Jenkins
                    sh "mvn org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=demoservice -Dsonar.java.binaries="target/classes" -Dsonar.login=src/main/java'
                    // Optional: Add specific SonarQube properties if needed, e.g., -Dsonar.sources=src/main/java
                }
            }
        }
        // Optional: Add a deployment stage
        // stage('Deploy') {
        //     steps {
        //         // Example: Copy artifact to a server
        //         sh 'scp target/your-app.jar user@your-server:/path/to/deploy'
        //     }
        // }
    }

    post {
        always {
            echo 'Pipeline finished.'
        }
        success {
            echo 'Build successful!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}
