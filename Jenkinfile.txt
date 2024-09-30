pipeline {
    agent any

    stages {
        stage('Clone Repository') {
            steps {
                echo 'Cloning the repository...'
                // Clone the Git repository
                git 'https://github.com/007tarun/demo_java.git'
            }
        }

        stage('Build') {
            steps {
                echo 'Building the project...'
                script {
                    if (isUnix()) {
                        sh '''
                            # Compile Java files with JUnit and Hamcrest JARs in the current directory
                            javac -cp .:junit-4.13.2.jar:hamcrest-core-1.3.jar *.java
                        '''
                    } else {
                        bat '''
                            REM Compile Java files with JUnit and Hamcrest JARs in the current directory
                            javac -cp .;junit-4.13.2.jar;hamcrest-core-1.3.jar *.java
                        '''
                    }
                }
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                script {
                    if (isUnix()) {
                        sh '''
                            # Run JUnit tests
                            java -cp .:junit-4.13.2.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore CalculatorTest
                        '''
                    } else {
                        bat '''
                            REM Run JUnit tests
                            java -cp .;junit-4.13.2.jar;hamcrest-core-1.3.jar org.junit.runner.JUnitCore CalculatorTest
                        '''
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying the application...'
                script {
                    if (isUnix()) {
                        sh '''
                            # Create deploy directory if not exists
                            mkdir -p deploy
                            
                            # Move compiled .class files to the deploy directory
                            mv *.class deploy/
                            
                            # (Optional) Run the Calculator as part of the deployment verification
                            java -cp deploy Calculator
                        '''
                    } else {
                        bat '''
                            REM Create deploy directory if not exists
                            if not exist "deploy" mkdir "deploy"
                            
                            REM Move compiled .class files to the deploy directory
                            move *.class deploy\\
                            
                            REM (Optional) Run the Calculator as part of the deployment verification
                            java -cp deploy Calculator
                        '''
                    }
                }
            }
        }
    }

    post {
        success {
            echo 'Build, Test, and Deploy stages completed successfully.'
        }
        failure {
            echo 'Pipeline failed. Please check the logs.'
        }
    }
}
