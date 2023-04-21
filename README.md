# Duke
This project illustrates [this DZone article](https://dzone.com/articles/aws-pushing-jakarta-ee-full-platform-applications).

To deploy it run th following command:

    $ copilot init --app duke-app --name duke --type "Load Balanced Web Service" --dockerfile ./DockerfileWithWAR --port 8080 --deploy

This will create the AWS Fargate infrastructure required to deploy and run 
the Wildfly 27.0 server with our Jakarta EE 10 application.

In order to clea-up the environment, run the following:

    $ copilot app delete

Of course, you  need an AWS account and `aws cli` and `copilot` installed and 
running on your box. Please follow the instructions in the article for additional
details.

To run the integration tests on a local Wildfly server, in managed Arquillian 
adapter, run the following:

    $ mvn -Parq-managed clean package verify

To run the integration tests on a local Wildfly server, in a remote Arquillian 
adapter, run the following:

    $ mvn -Parq-remote clean package verify

To run the integration tests on a Wilfly server running in a Docker conatiner, 
using the remote Arquillian adapter, run the following:

    $ mvn -Parq-docker clean package verify

If you want to execute manual tests with the browser, without using Arquillian, run the following:

    $ mvn clean package docker:build docker:run

Then you can use your prefered browser to connect to http://localhost:8080/duke.

Last but not least, once deployed on the AWS Fargate serverless infrastructure, 
you can run integartion tests by executiong:

    $ mvn -Paws clean package verify

You need to make sure first that the following statement in the TestIT class:

      private static final String lbUrl = "";

matches the URL displayed by the `copilot init` command. For example, if you 
see the message below:

    - You can access your service at http://duke-Publi-V5NKFZU77FWB-1356331722.eu-west-3.elb.amazonaws.com over the internet.

then you have to adapt the statement above such that to read:

    private static final String lbUrl = "http://duke-Publi-V5NKFZU77FWB-1356331722.eu-west-3.elb.amazonaws.com/duke";

and, hence, you can fire your browser at this same URL.