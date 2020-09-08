node('docker-node') {
    checkout scm
    docker.image('mysql:5.7').withRun('-e "MYSQL_ROOT_PASSWORD=Jivoxdb"') { c ->
        docker.image('mysql:5.7').inside("--link ${c.id}:db") {
          /* Wait until mysql service is up */
          sh 'while ! mysqladmin ping -hdb --silent; do sleep 1; done'
        }
        docker.image('jivoxhub/maven:v2').inside("--link ${c.id}:db -u root --privileged") {
          stage('Build') {
            
                sh """
                ls -ltr
                echo "${JOB_NAME}"
                echo "${BUILD_NUMBER}"
                echo "set branchName"
                branchName="${BRANCH_NAME}"
                echo "${BRANCH_NAME}"
                echo "set commit id"
                echo "${GIT_COMMIT}"
                echo "${GIT_BRANCH}"
                commitId=$(git rev-parse --short=10 ${GIT_COMMIT})
                echo $commitId
                
                """   
                
            }
        }
        stage('Download artifact from s3') {
            
                sh """
                ls -ltr
               
                """
            
        }
  }
}
