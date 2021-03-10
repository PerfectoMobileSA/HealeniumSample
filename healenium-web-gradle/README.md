# Healenium Web +  Perfecto Integration

This is a sample Java based [Healenium](https://healenium.io) web integration with Perfecto Smart Reporting integration.

This sample runs against the url: https://sha-test-app.herokuapp.com/ with randomly generated markup 

## Pre-requisites:

* Install [docker desktop](https://www.docker.com/get-started) and make sure that docker is accessible from command prompt/ terminal.
* Install [gradle](https://gradle.org/install/) 6.3 or above and make sure that gradle is accessible from command prompt/ terminal.
* [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* An IDE to write your tests on - [Eclipse](http://www.eclipse.org/downloads/packages/eclipse-ide-java-developers/marsr) or [IntelliJ](https://www.jetbrains.com/idea/download/#)

Note: Import  this gradle project with gradle wrapper as gradle distribution.

## How to start
### 1. Start Healenium backend from infra folder

* Navigate to infra folder and run the following docker-compose commmand to start up Healenium backend. 

    cd infra

    docker-compose up -d

Verify that images healenium/hlm-backend:latest and  postgres:11-alpine are up and running by running ` docker ps ` command.


### 2. Run test in terminal with gradle
* Navigate to base folder location.

     cd ..
* Run the following gradle commands:

    ./gradlew clean build test -PcloudName={cloudName} -PsecurityToken={securityToken} 

Note:

* Substitute your cloudName (E.g. cloudName for free trial is testingcloud where as cloud url is https://testingcloud.perfectomobile.com) and securityToken as gradle properties respectively (without flower brackets).


* This [link](https://developers.perfectomobile.com/display/PD/Generate+security+tokens) will showcase how to generate Perfecto security token.


* Follow [this](https://stackoverflow.com/a/65211651) if you get the below gradle error:
        Could not find tools.jar. Please check that /Library/Internet Plug-Ins/JavaAppletPlugin.plugin/Contents/Home contains a valid JDK installation.


### 3. Report 

    - Healenium Report:
        
            Healenium Report will be available inside build/reports/tests folder
      
    - Perfecto Integration:
    
            Refer to Perfecto Smart Reporting for execution steps, results, screenshots, video, etc. 
            More info: https://developers.perfectomobile.com/display/PD/Access+and+navigate+the+Test+Analysis+UI 
     

