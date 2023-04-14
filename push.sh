#!/bin/bash
ECR_URI=$(aws ecr create-repository --repository-name duke-ecr --output text --query repository.repositoryUri)
aws ecr get-login-password | docker login --username AWS --password-stdin $ECR_URI
mvn -Paws -DECR_URI=$ECR_URI -DskipITs clean verify
echo $ECR_URI
