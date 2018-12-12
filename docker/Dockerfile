# Fixed version to 2.60.3. Do not really care which version is chosen, but its important that the version is not
#   changed "under the hood" from the user perspective, which is what may happen when not fixing the version.
#   This is what an unfixed FROM looks like:
#
#   FROM jenkins/jenkins:2.60.3
#
FROM jenkins/jenkins:2.155

COPY jenkins-plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

COPY tool_config/hudson.tasks.Maven.xml  /usr/share/jenkins/ref/hudson.tasks.Maven.xml
COPY tool_config/config.xml  /usr/share/jenkins/ref/config.xml
COPY tool_config/hudson.maven.MavenModuleSet.xml  /usr/share/jenkins/ref/hudson.maven.MavenModuleSet.xml
#COPY init.groovy.d/createProjects.groovy  /usr/share/jenkins/ref/init.groovy.d/createProjects.groovy

USER root

RUN apt-get update && apt-get install -y build-essential dos2unix

RUN dos2unix /usr/share/jenkins/ref/plugins.txt
RUN dos2unix /usr/share/jenkins/ref/hudson.tasks.Maven.xml

USER jenkins
