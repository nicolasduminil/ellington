#!/bin/bash
AWS_REGION=$(aws configure get region)
sam deploy --stack-name duke-stack --capabilities CAPABILITY_IAM --region $AWS_REGION
aws cloudformation wait stack-create-complete --stack-name duke-stack
ECR_URI=$(sam list stack-outputs --stack-name duke-stack --output json | jq -r ".[0].OutputValue")
aws ecr get-login-password --region $AWS_REGION | docker login --username AWS --password-stdin $ECR_URI
mvn -Paws -DECR_URI=$ECR_URI -DskipITs clean verify
aws ecs create-cluster --cluster-name duke-cluster

