#!/bin/bash

ant -buildfile jgossip/build.xml dist -Ddeploy_env=default
bash jgossipaspect.sh 2>&1 | tee deploy.log
bash tomcat/bin/shutdown.sh
rm tomcat/webapps/jgossip.war
rm -rf tomcat/webapps/jgossip
mv jgossip.war tomcat/webapps/
bash tomcat/bin/startup.sh
