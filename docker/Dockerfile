FROM jenkins/jenkins

COPY jenkins-plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

COPY tool_config/hudson.tasks.Maven.xml  /usr/share/jenkins/ref/hudson.tasks.Maven.xml
#COPY init.groovy.d/createProjects.groovy  /usr/share/jenkins/ref/init.groovy.d/createProjects.groovy

USER root

RUN apt-get update && apt-get install -y build-essential

USER jenkins