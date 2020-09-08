node('docker-node') {
    checkout scm
    docker.image('mysql:5.7').withRun('-e "MYSQL_ROOT_PASSWORD=Jivoxdb"') { c ->
        docker.image('mysql:5.7').inside("--link ${c.id}:db") {
          /* Wait until mysql service is up */
          sh 'while ! mysqladmin ping -hdb --silent; do sleep 1; done'
        }
        docker.image('jivoxhub/maven:v2').inside("--link ${c.id}:db -u root --privileged") {
          stage('Build') {
                def branch = env.BRANCH_NAME
                sh """
                ls -ltr
                echo "$branch"
                echo $JOB_NAME
                echo "${BUILD_NUMBER}"
                
                echo "set branchName"
                branchName=""
                
                echo "set commit id"
            
                
                
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
