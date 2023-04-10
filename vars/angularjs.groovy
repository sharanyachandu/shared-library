def call() {
    node {
        git branch: 'main', url: "https://github.com/b53-clouddevops/${COMPONENT}.git"
        env.APP_TYPE = "angularjs" 
        common.lintChecks()
        env.ARGS="-Dsonar.sources=."
        common.sonarChecks()
        common.testCases()
        common.artifacts()
    }
}

// Call is the default function which will be called when you call the fileName
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
//                         // sh "npm test"
//                         sh "echo Performing Unit Testing"
//                     }
//                 }
//                 stage('Integration Testing') {
//                     steps {
//                         // sh "npm verify"
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