# jenkins-config
A Jenkins docker setup to run all jobs in the DMA organizations

# Prerequisites

docker-compose (docker-compose version 1.23.1, build b02f1306)
docker


If docker-compose does not work for you for some reason, you can start the container without it like so
```
docker run --rm --name jenkins -p 8087:8080 -p 50007:50000 -v ~/jenkins_home:/var/jenkins_home jenkins/jenkins
```
