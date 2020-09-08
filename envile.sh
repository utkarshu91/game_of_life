echo ${filebranchname}
echo "${filegitcommit}"
commitId=$(git rev-parse --short=10 ${filegitcommit})
echo ${commitId}
