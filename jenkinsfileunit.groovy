node('docker-node') {
    checkout scm
    docker.image('mysql:5.7').withRun('-e "MYSQL_ROOT_PASSWORD=Jivoxdb"') { c ->
        docker.image('mysql:5.7').inside("--link ${c.id}:db") {
          /* Wait until mysql service is up */
          sh 'while ! mysqladmin ping -hdb --silent; do sleep 1; done'
        }
        docker.image('jivoxhub/maven:v2').inside("--link ${c.id}:db -u root --privileged") {
          stage('Build') {
                def branch=env.BRANCH_NAME
                def commit=env.GIT_COMMIT
                final scmVars = checkout(scm)
                echo "scmVars: ${scmVars}"
                def filebranchname="${scmVars.GIT_BRANCH}"
                def filegitcommit="${scmVars.GIT_COMMIT}"
             
                echo "${filebranchname}"
                echo "${filegitcommit}"
                withEnv(["filegitcommit=${scmVars.GIT_COMMIT}" , "filebranchname=${scmVars.GIT_BRANCH}"]) {
                    sh "bash envile.sh" 
                }
                echo "scmVars.GIT_COMMIT: ${scmVars.GIT_COMMIT}"
                echo "scmVars.GIT_BRANCH: ${scmVars.GIT_BRANCH}"
                sh """
                ls -ltr
                echo '$branch'
                echo '$commit'
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
