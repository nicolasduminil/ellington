REPOSITORY_NAME=duke-ecr
aws ecr batch-delete-image --repository-name $REPOSITORY_NAME --image-ids imageTag=latest
aws ecr delete-repository --repository-name $REPOSITORY_NAME
aws cloudformation delete-stack --stack-name duke-stack
aws cloudformation wait stack-delete-complete --stack-name duke-stack
