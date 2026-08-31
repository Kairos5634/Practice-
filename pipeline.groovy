pipeline {
    agent any
    stages {
        stage ('PULL') {
            steps {
                git branch: 'main', url: 'https://github.com/jambhulkarcloudblitz-alt/CDEC-studentapp.git' 
            }
        }
        stage ('BUILD') {
            steps {
                sh '''cd backend
                        mvn clean package -DskipTests
                        '''
            }
        }
        stage ('TEST') {
            steps {
                sh '''cd backend
                        mvn clean verify sonar:sonar \
                         -Dsonar.projectKey=pipeline \
                          -Dsonar.projectName='pipeline' \
                          -Dsonar.host.url=http://51.20.193.186:9000 \
                          -Dsonar.token=sqp_d8ea28575d2879e33f33188fea6a9022df4bd88d

                        '''
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