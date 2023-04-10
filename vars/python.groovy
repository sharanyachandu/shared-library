def call() {
    node {
        env.APP_TYPE = "python" 
        common.lintChecks()
        env.ARGS="-Dsonar.sources=."
        common.sonarChecks()
        common.testCases()
    }
}

// Call is the default function which will be called when you call the fileName
// **** Uncomment it only when you prefer to use Declarative Pipeline 
// def call() {
//     pipeline {
//         agent any 
//         environment { 
//             SONAR = credentials('SONAR') 
//             SONAR_URL = "172.31.9.236"
//         }
//         stages {
//             stage('Lint Checks') {
//                 steps {
//                     script {
//                         lintChecks()
//                     }
//                 }
//             }
//             stage('Sonar Checks') {
//                 steps {
//                     script {
//                         env.ARGS="-Dsonar.sources=."
//                         common.sonarChecks()
//                     }
//                 }
//             }
//         stage('Test Cases') {
//             parallel {
//                 stage('Unit Testing') {
//                     steps {
//                         // sh "py test"
//                         sh "echo Performing Unit Testing"
//                     }
//                 }
//                 stage('Integration Testing') {
//                     steps {
//                         // sh "py verify"
//                         sh "echo Integration Unit Testing"
//                     }
//                 }
//                 stage('Functional Testing') {
//                     steps {
//                         sh "echo Integration Unit Testing"
//                         }
//                     }
//                 }
//             }
//         }
//     }
// }