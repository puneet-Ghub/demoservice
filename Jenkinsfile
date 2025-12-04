pipeline {
    agent any // Specifies that the pipeline can run on any available agent

    tools {
        // Defines the Maven tool to use, ensure 'maven-3' matches the name configured in Jenkins Global Tool Configuration
        maven 'Maven-3.9.11'
        // Defines the JDK to use, ensure 'jdk-11' matches the name configured in Jenkins Global Tool Configuration
        jdk 'amazon-jdk-21' 
    }

    stages {
        stage('Checkout Source Code') {
            steps {
                // Checkout the source code from the Git repository
                git branch: 'develop', // Or your specific branch, e.g., 'develop'
                    //credentialsId: 'your-git-credentials-id', // ID of your stored Jenkins credentials
                    url: 'https://github.com/shyamjava/demoservice.git' // URL of your Git repository
            }
        }
        stage('Build') {
            steps {
                // Cleans, compiles, and packages the Java application using Maven, skipping tests for faster build
               bat 'mvn -B -DskipTests clean package'
            }
        }

        stage('Test') {
            steps {
                // Runs the unit tests using Maven
               bat 'mvn test'
            }
            post {
                always {
                    // Publishes JUnit test results for reporting in Jenkins
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                // Packages the application into a JAR or WAR file
               bat 'mvn package'
            }
        }

      stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('local-sonarqube') { // Replace with your configured SonarQube server name
                   bat 'mvn sonar:sonar -Dsonar.projectKey=demoservice -Dsonar.java.binaries="target/classes" -Dsonar.sources=src/main/java'
                    // Add other SonarQube properties as needed
                }
            }
        }

        // Optional: Add a deployment stage here if needed
        // stage('Deploy') {
        //     steps {
        //         // Example: Deploy to a server or artifact repository
        //         sh 'scp target/*.jar user@server:/path/to/deploy'
        //     }
        // }
    }

    post {
        // Actions to perform after the pipeline completes, regardless of success or failure
        always {
            echo 'Pipeline finished.'
        }
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
