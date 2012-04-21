#!/bin/bash
rm *.log*
rm *.log
rm *.lck
rm *.1
rm ext*

#bash tomcat/bin/shutdown.sh
#rm tomcat/logs/*
cp tomcat/prelib/*.jar tomcat/lib
#ant -buildfile jgossip/build.xml dist -Ddeploy_env=default
ant -buildfile aspect/ajtestbuild.xml

#bash tomcat/bin/startup.sh
exit 0

#bash jgossipaspect.sh 2>&1 | tee deploy.log
#rm tomcat/webapps/jgossip.war
#rm -rf tomcat/webapps/jgossip
#mv jgossip.war tomcat/webapps/
#sleep 2

#curl http://localhost:8686/jgossip/jgossip/ShowThread.do?fid=25&tid=70
