# Healenium Web + Maven + Testng + Perfecto Integration

This is a sample Java + Maven based [Healenium](https://healenium.io) web integration with Perfecto Smart Reporting integration.

This sample runs against the url: https://sha-test-app.herokuapp.com/ with randomly generated markup 

## Pre-requisites:

* Install [docker desktop](https://www.docker.com/get-started) and make sure that docker is accessible from command prompt/ terminal.
* Install [Maven](https://maven.apache.org/) and make sure that it is accessible from command prompt/ terminal.
* [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* An IDE to write your tests on - [Eclipse](http://www.eclipse.org/downloads/packages/eclipse-ide-java-developers/marsr) or [IntelliJ](https://www.jetbrains.com/idea/download/#)

Eclipse users should also install:

1. [Maven Plugin](http://marketplace.eclipse.org/content/m2e-connector-maven-dependency-plugin)

2. [TestNG Plugin](http://testng.org/doc/download.html)

IntelliJ IDEA users should also install:

1. [Maven Plugin for IDEA](https://plugins.jetbrains.com/plugin/1166)

## How to start
### 1. Start Healenium backend:

* Navigate to test resources folder and run the following docker-compose commmand to start up Healenium backend. 

    cd src/test/resources/

    docker-compose up -d

Verify that images healenium/hlm-backend:latest and  postgres:11-alpine are up and running by running ` docker ps ` command.


### 2. Run test in terminal with maven
* Navigate to base folder location.

      cd ../../..

* Run the following maven commands:
    
      mvn install -DcloudName={cloudName} -DsecurityToken={securityToken} 

Note:
        
* Substitute your cloudName (E.g. cloudName for free trial is testingcloud where as cloud url is https://testingcloud.perfectomobile.com) and securityToken as maven properties respectively (without flower brackets).
        
* This [link](https://developers.perfectomobile.com/display/PD/Generate+security+tokens) will showcase how to generate Perfecto security token.


### 3. Report 
    
	Refer to Perfecto Smart Reporting for execution steps, results, screenshots, video, etc. 
    More info: https://developers.perfectomobile.com/display/PD/Access+and+navigate+the+Test+Analysis+UI 
     

