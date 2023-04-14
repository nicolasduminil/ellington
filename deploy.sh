#!/bin/bash
ECR_URI=$(cat)
if [ -z "$ECR_URI" ]
then
  echo "### deploy.sh: ECR URI is empty"
else
  sam deploy --stack-name duke-stack --capabilities CAPABILITY_NAMED_IAM --parameter-overrides EcrUri=$ECR_URI
  aws cloudformation wait stack-create-complete --stack-name duke-stack
fi
