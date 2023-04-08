def lintChecks() {
    sh '''  
            echo Lint Checks for ${COMPONENT}
            # mvn checkstyle:check
            echo performing lint checks for ${COMPONENT}
            echo performing lint checks completed ${COMPONENT}
    ''' 
}

// Call is the default function which will be called when you call the fileName
def call() {
    pipeline {
        agent any 
        environment { 
            SONAR = credentials('SONAR') 
            SONAR_URL = "172.31.9.236"
        }
        stages {
            stage('Lint Checks') {
                steps {
                    script {
                        lintChecks()
                    }
                }
            }
            stage('Sonar Checks') {
                steps {
                    script {
                        sh "mvn clean compile"
                        env.ARGS="-Dsonar.java.binaries=target/"
                        common.sonarChecks()
                    }
                }
            }

        stage('Test Cases') {
            parallel {
                stage('Unit Testing') {
                    steps {
                        // sh "mvn test"
                        sh "echo Performing Unit Testing"
                    }
                }
                stage('Integration Testing') {
                    steps {
                        // sh "mvn verify"
                        sh "echo Integration Unit Testing"
                    }
                }
                stage('Functional Testing') {
                    steps {
                        sh "echo Integration Unit Testing"
                        }
                    }
                }
            }
        }
    }
}