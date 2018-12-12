# jenkins-config
A Jenkins docker setup to run all jobs in the DMA organizations

# Prerequisites
docker-compose (docker-compose version 1.23.1, build b02f1306)
docker


# How to start system
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
At the hour of writing not all repos have Jenkinsfiles. You can see progress in the TODO section below.

# TODO

* Update readme with install instructions
* Fix "E-nav service, why does it fail" : Steen kigger på
* Lav jenkins files til alle relevante repoer. Du kan se listen her: https://dma.ci.cloudbees.com/
  * Baltic Web. Bygger med Jenkinsfil.
  * Enav Services.
  * AisAbnormal
  * AisAnalysis
  * AisCoverage
  * AisLib
  * AisStore
  * AisTrack
  * AisView
  * AisVirtualNet
  * AisWeb
  * ArcticWeb-branch-master
  * ArcticWeb-Docker	1 hr
  * ArcticWeb-Docker-Release
  * ArcticWeb-Integration-Test
  * arcticweb-manual
  * arcticweb-manual-release
  * ArcticWeb-Release
  * ArcticWeb2Alpha
  * ArcticWeb2Test
  * async-couchdb-client
  * BalticWeb
  * BalticWeb - Build Docker Image
  * dma-commons
  * dma-web
  * e-Navigation
  * e-Navigation-javadoc
  * enav-network
  * Enav-Services
  * Enav-Services-Release
  * Enav-services_integration_tests
  * Enav-services_MC_Service_registry
  * EnavShore
  * EPD
  * forecast-in
  * Niord
  * NoGoService
  * tiler-service
  * tiler-service-dockerAisAnalysis
  * AisCoverage
  * AisLib
  * AisStore
  * AisTrack
  * AisView
  * AisVirtualNet
  * AisWeb
  * ArcticWeb-branch-master
  * ArcticWeb-Docker
  * ArcticWeb-Docker-Release
  * ArcticWeb-Integration-Test
  * arcticweb-manual
  * arcticweb-manual-release
  * ArcticWeb-Release
  * ArcticWeb2Alpha
  * ArcticWeb2Test
  * async-couchdb-client
  * BalticWeb
  * BalticWeb - Build Docker Image
  * dma-commons
  * dma-web
  * e-Navigation
  * e-Navigation-javadoc
  * enav-network
  * Enav-Services
  * Enav-Services-Release
  * Enav-services_integration_tests
  * Enav-services_MC_Service_registry
  * EnavShore
  * EPD
  * forecast-in
  * Niord
  * NoGoService
  * tiler-service
  * tiler-service-docker
* createProjects.groovy. Bootscript til Jenkins. Skal generere jobs til alle jenkinsfiles/repoer automatisk

