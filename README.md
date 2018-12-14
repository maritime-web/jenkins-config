jenkins-config
==============

A Jenkins docker setup to run all jobs in the DMA organizations

## Prerequisites
docker-compose (docker-compose version 1.23.1, build b02f1306)
docker


## How to start the system
Starting from the project folder
```
cd docker
docker-compose build
docker-compose up
```

watch the output from docker-compose, there should be no errors. At some point
a Password will be printed. Copy it

Once Jenkins is done starting, open a browser and to to http://localhost:8087

Here you will be prompted for the Password

On the screen that follows, select the option to choose which plugins to install. Since all plugins are already
installed, unselect everything and click the accept button.

If you enter the jenkins main screen, click the blue ocean button.

If jenkins starts at the blue ocean screen, you are happy.

At the blue ocean screen, you have to manually create all the pipelines. Do this by clicking "create pipeline" for each of the repositories.

## Populate jenkins with pipelines
1. Click the "Open Blue Ocean" link
2. First time you enter Blue Ocean it will prompt you to create a new pipeline
3. In the pipeline wizard choose GitHub
4. Enter a valid GitHub Access Token
5. Choose one of the DMA organizations (see the list below)
6. Choose a repository
7. Repeat steps 3-6 (except 4) for all the desired repositories 

## Known GitHub repositories with a Jenkinsfile
* dma-ais
  * AisAbnormal		
  * AisAnalysis		
  * AisCoverage		
  * AisLib		
  * AisStore		
  * AisTrack		
  * AisView		
  * AisVirtualNet		
  * AisWeb
* maritime-web		
  * BalticWeb		
  * Enav-Services		
  * forecast-in		
  * NoGoService		
  * TilerService
* dma-dk 		
  * dma-commons		
  * dma-developers
* NiordOrg		
  * niord		

## Pre installed features
* All relevant plugins
* Maven installations 
* Secret integration test property file 

# TODO

* Create a bootscript to Jenkins to automatically configure a user and create pipelines for all the repo's listed above.
  * createProjects.groovy is a first try

