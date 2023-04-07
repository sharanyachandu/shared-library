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

def sonarChecks() {
    sh ''' 
        sonar-scanner -Dsonar.host.url=http://172.31.9.236:9000 -Dsonar.sources=. -Dsonar.projectKey==${COMPONENT}

    '''
}

// Call is the default function which will be called when you call the fileName
def call() {
    pipeline {
        agent any 
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
                        sonarChecks()
                    }
                }
            }

        }
    }
}