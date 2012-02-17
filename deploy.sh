#!/bin/bash
rm *.log
rm *.lck
rm *.1
rm ext*

ant -buildfile jgossip/build.xml dist -Ddeploy_env=default
bash jgossipaspect.sh 2>&1 | tee deploy.log
bash tomcat/bin/shutdown.sh
rm tomcat/webapps/jgossip.war
rm -rf tomcat/webapps/jgossip
mv jgossip.war tomcat/webapps/
bash tomcat/bin/startup.sh
#sleep 2

#curl http://localhost:8686/jgossip/jgossip/ShowThread.do?fid=25&tid=70
