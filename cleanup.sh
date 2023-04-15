aws ecs update-service --service duke-service --desired-count 0 --cluster duke-cluster
aws ecs delete-service --cluster duke-cluster --service duke-service
aws ecs delete-cluster --cluster duke-cluster
REPOSITORY_NAME=duke-ecr
aws ecr batch-delete-image --repository-name $REPOSITORY_NAME --image-ids imageTag=latest
aws ecr delete-repository --repository-name $REPOSITORY_NAME
aws cloudformation delete-stack --stack-name duke-stack
aws cloudformation wait stack-delete-complete --stack-name duke-stack
