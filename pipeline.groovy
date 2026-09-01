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
    }
    }
//         stage ('TEST') {
//             steps {
//                 sh '''cd backend
//                         mvn clean verify sonar:sonar \
//                          -Dsonar.projectKey=pipeline \
//                           -Dsonar.projectName='pipeline' \
//                           -Dsonar.host.url=http://51.20.193.186:9000 \
//                           -Dsonar.token=sqp_d8ea28575d2879e33f33188fea6a9022df4bd88d

//                         '''
//             }
//         }
//     }
// }
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