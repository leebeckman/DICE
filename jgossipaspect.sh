#!/bin/bash

rm /home/lee/JavaTaintTracker/weavelog.log
rm /home/lee/JavaTaintTracker/connector_weavelog.log
cd /home/lee/workspace/TaintAspects/bin
jar cfM traceLib.jar .

cd /home/lee/JavaTaintTracker/jgossipFiles
ant dist -Ddeploy_env=default
cd /home/lee/JavaTaintTracker/jgossipFiles/dist/default
jar xf jgossip.war
cd WEB-INF
jar cfM jgossipclasses.jar -C classes .
mv /home/lee/workspace/TaintAspects/bin/traceLib.jar ./

export CLASSPATH="/home/lee/aspectj1.6/lib/aspectjrt.jar:/home/lee/JavaTaintTracker/tomcat6.0.18/output/build/lib/*:/home/lee/JavaTaintTracker/jgossipFiles/database/oracle/lib/*:/home/lee/JavaTaintTracker/jgossipFiles/database/mysql/lib/*:/home/lee/JavaTaintTracker/jgossipFiles/lib/*"
ajc -inpath jgossipclasses.jar -aspectpath traceLib.jar -1.5 -showWeaveInfo -log /home/lee/JavaTaintTracker/weavelog.log -outjar tracedjgossip.jar
rm jgossipclasses.jar

cd /home/lee/JavaTaintTracker/jgossipFiles/dist/default/WEB-INF/lib
mv /home/lee/JavaTaintTracker/jgossipFiles/dist/default/WEB-INF/traceLib.jar ./
#ajc -inpath mysql-connector-java-3.0.14-production-bin.jar -aspectpath traceLib.jar -1.5 -showWeaveInfo -log /home/lee/JavaTaintTracker/connector_weavelog.log -outjar mysql-connector-java-3.0.14-production-bin-aspects.jar
#rm mysql-connector-java-3.0.14-production-bin.jar
ajc -inpath commons-dbcp.jar -aspectpath traceLib.jar -1.5 -showWeaveInfo -log /home/lee/JavaTaintTracker/connector_weavelog.log -outjar commons-dbcp-aspects.jar
ajc -inpath commons-beanutils.jar -aspectpath traceLib.jar -1.5 -showWeaveInfo -log /home/lee/JavaTaintTracker/beanutils_weavelog.log -outjar commons-beanutils-aspects.jar
rm commons-dbcp.jar
rm /home/lee/JavaTaintTracker/tomcat6.0.18/output/build/lib/traceLib.jar
mv traceLib.jar /home/lee/JavaTaintTracker/tomcat6.0.18/output/build/lib

cd ..
rm -rf classes
mkdir classes
mv tracedjgossip.jar classes
cd classes
jar xf tracedjgossip.jar
rm tracedjgossip.jar
cd ../..

mv jgossip.war ../
rm WEB-INF/lib/jsp-api.jar
#rm /home/lee/JavaTaintTracker/tomcat6.0.18/output/build/webapps/jgossip.war
#jar cvf /home/lee/JavaTaintTracker/tomcat6.0.18/output/build/webapps/jgossip.war *
rm /home/lee/JavaTaintTracker/$1
jar cvf /home/lee/JavaTaintTracker/$1 *
mv ../jgossip.war ./
rm -rf jgossip
rm -rf META-INF
rm -rf web
rm -rf WEB-INF

#bash /home/lee/JavaTaintTracker/tomcat6.0.18/output/build/bin/startup.sh
