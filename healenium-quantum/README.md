

# Quantum Starter Kit + Healenium
This Quantum starter kit is designed to showcase integration of Healenium with  Quantum framework (sponsored by [Perfecto](https://www.perfecto.io) and powered by [QAF](https://github.com/qmetry/qaf)) within few simple steps, and enable you to start writing your tests using simple [Cucumber] (https://cucumber.io/).

Begin with installing the dependencies below, and continue with the Getting Started procedure below.

### Dependencies
There are several prerequisite dependencies you should install on your machine prior to starting to work with Quantum:

* [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

* An IDE to write your tests on - [Eclipse](http://www.eclipse.org/downloads/packages/eclipse-ide-java-developers/marsr) or [IntelliJ](https://www.jetbrains.com/idea/download/#)

* [Maven](https://maven.apache.org/)

Eclipse users should also install:

1. [Maven Plugin](http://marketplace.eclipse.org/content/m2e-connector-maven-dependency-plugin)

2. [TestNG Plugin](http://testng.org/doc/download.html)

3. [QAF Cucumber Plugin](https://marketplace.eclipse.org/content/qaf-bdd-editors). Or go to  install new software option in eclipse, and download from https://qmetry.github.io/qaf/editor/bdd/eclipse/

IntelliJ IDEA users should also install:

1. [Maven Plugin for IDEA](https://plugins.jetbrains.com/plugin/1166)

2. [Cucumber Plugin (Community version only)](https://plugins.jetbrains.com/plugin/7212)

TestNG Plugin is built-in in the IntelliJ IDEA, from version 7 onwards.

## Downloading the Quantum Project

[Download](https://github.com/Project-Quantum/Quantum-Starter-Kit/archive/master.zip) the Quantum-Started-Kit repository.

After downloading and unzipping the project to your computer, open it from your IDE by choosing the folder containing the pom.xml file (_Quantum-Starter-Kit-master_, you might consider renaming it).

Look [here](https://github.com/PerfectoCode/Quantum/wiki/Project%20Layout) to understand the project layout, and find your way in it.

**********************
# Getting Started

* Follow [Quantum Readme](https://github.com/Perfecto-Quantum/Quantum-Starter-Kit/blob/master/README.md) on how to setup this project.

* Navigate to test resources folder and run the following docker-compose commmand to start up Healenium backend. 

		cd src/main/resources/

    	docker-compose up -d

Verify that images healenium/hlm-backend:latest and  postgres:11-alpine are up and running by running ` docker ps ` command.


## Running sample as is

1. Configure your remote server, securityToken and set `healenium=true` in the _application.properties_ file (under the top  _resources/_  folder).
2. Configure [healenium.properties](https://github.com/healenium/healenium-web) (e.g. serverHost) in src/main/resources folder.
3. Navigate to base folder location.

	  	cd ../../..
    
4 Run the following maven command:
    
      mvn clean install -Dremote.server={url} -Dperfecto.capabilities.securityToken={securityToken} 

Note:
        
  	* Replace {url} with https://<<CLOUD NAME>>.perfectomobile.com/nexperience/perfectomobile/wd/hub/fast after updating your cloud name instead of <<CLOUD NAME>> (E.g. CLOUD NAME for free trial is testingcloud where as cloud url is https://testingcloud.perfectomobile.com/nexperience/perfectomobile/wd/hub/fast) 
        
   	* Replace {securityToken} with your [Perfecto security token](https://developers.perfectomobile.com/display/PD/Generate+security+tokens) (without flower brackets)

