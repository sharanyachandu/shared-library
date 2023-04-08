def lintChecks() {
    sh '''  
            echo Lint Checks for ${COMPONENT}
            echo installing jslint
            # npm install jslint
            # ls -ltr node_modules/jslint/bin/
            # node_modules/jslint/bin/jslint.js server.js
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
                        env.ARGS="-Dsonar.sources=."
                        common.sonarChecks()
                    }
                }
            }

            stage('Performing npm install') {
                steps {
                    sh "npm install"
                }
            }

        stage('Test Cases') {
            parallel {
                stage('Unit Testing') {
                    steps {
                        // sh "npm test"
                        sh "echo Performing Unit Testing"
                    }
                }
                stage('Integration Testing') {
                    steps {
                        // sh "npm verify"
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

        stage('Prepare the artifacts') {
            when { expression { env.TAG_NAME != null } }
            steps {
                sh "echo Prepare the artifacts"
                }
            }

        stage('Publish the artifacts') {
            when { expression { env.TAG_NAME != null } }
            steps {
                sh "echo Publishing the artifacts"
                }
            }
        }
    }
}