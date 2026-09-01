pipeline {
    agent { label 'node01' }
    tools {
        maven 'Maven'   
    }
    stages {
        stage ('PULL') {
            steps {
                git branch: 'main', url: 'https://github.com/Kairos5634/Student-app.git'
            }
        }
        stage('Build') {
            steps {
                dir('backend') {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }
    
    
        // stage ('TEST') {
        //     steps {
        //         sh '''cd backend
        //          mvn org.sonarsource.scanner.maven:sonar-maven-plugin:sonar \\
        //            -Dsonar.projectKey=student-app \
        //            -Dsonar.projectName='student=app' \
        //            -Dsonar.host.url=http://16.171.206.249:9000 \
        //            -Dsonar.token=sqp_95ad8eb2dae20ecd08fbd3a8b78e160707781467'''
        //     }
        // }
        stage ('TEST') {
            steps {
                withSonarQubeEnv(installationName: 'sonarqube', credentialsId: 'sonar-cred') {
                    sh '''cd backend
                          mvn org.sonarsource.scanner.maven:sonar-maven-plugin:sonar \
                          -Dsonar.projectKey=studentapp'''
                    }
            }
        }

        stage ('Quality-Gate') {
            steps {
                timeout(10) {
                    waitForQualityGate abortPipeline: true, credentialsId: 'sonar-cred'
                    }
                
            }
        }
    }
}
//         stage ('TEST'){
//             steps {
//                 sh 'echo "TEST SUCCESS"'
//             }
//         }
//         stage ('S3-Upload') {
//             steps {
//                 sh 'aws s3 cp backend/target/student-registration-backend-0.0.1-SNAPSHOT.jar s3://s3-upload-6741/student.jar'
//             }
//         }
//     }
// }



// sqp_95ad8eb2dae20ecd08fbd3a8b78e160707781467

// mvn clean verify sonar:sonar \
//   -Dsonar.projectKey=student-app \
//   -Dsonar.projectName='student=app' \
//   -Dsonar.host.url=http://16.171.206.249:9000 \
//   -Dsonar.token=sqp_95ad8eb2dae20ecd08fbd3a8b78e160707781467