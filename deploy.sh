#!/bin/bash
rm *.log
rm *.lck
rm *.1
rm ext*

bash tomcat/bin/shutdown.sh
rm -rf tomcat/webapps/jgossip
rm tomcat/webapps/jgossip.war
rm tomcat/logs/*

ant -buildfile jgossip/build.xml dist -Ddeploy_env=default
ant -buildfile aspect/build.xml

cp aspect/output/jgossip.war tomcat/webapps
bash tomcat/bin/startup.sh
exit 0

#bash jgossipaspect.sh 2>&1 | tee deploy.log
#rm tomcat/webapps/jgossip.war
#rm -rf tomcat/webapps/jgossip
#mv jgossip.war tomcat/webapps/
#sleep 2

#curl http://localhost:8686/jgossip/jgossip/ShowThread.do?fid=25&tid=70
