#!/bin/bash

rm $HOME/DICE/weavelog.log
rm $HOME/DICE/weavelog_connector.log
rm $HOME/DICE/weavelog_beanutils.log

export CLASSPATH="$HOME/DICE/tomcat/lib/*:$HOME/DICE/jgossip/database/oracle/lib/*:$HOME/DICE/jgossip/database/mysql/lib/*:$HOME/DICE/jgossip/lib/*"

ajc -sourceroots $HOME/DICE/src -1.5 -outjar $HOME/DICE/traceLib.jar
cp $HOME/DICE/traceLib.jar $HOME/DICE/tomcat/lib

rm -rf $HOME/DICE/jgossipAspect
mkdir $HOME/DICE/jgossipAspect
echo '>>>jar xf...'
unzip $HOME/DICE/jgossip/dist/default/jgossip.war -d $HOME/DICE/jgossipAspect
echo '>>>jar cfM...'
jar cfM $HOME/DICE/jgossipAspect/WEB-INF/jgossipClasses.jar -C $HOME/DICE/jgossipAspect/WEB-INF/classes .
echo '>>>done'

ajc -inpath $HOME/DICE/jgossipAspect/WEB-INF/jgossipClasses.jar -aspectpath $HOME/DICE/traceLib.jar -1.5 -showWeaveInfo -log $HOME/DICE/weavelog.log -outjar $HOME/DICE/jgossipAspect/WEB-INF/tracedjgossipClasses.jar
rm $HOME/DICE/jgossipAspect/WEB-INF/jgossipClasses.jar

ajc -inpath $HOME/DICE/jgossipAspect/WEB-INF/lib/commons-dbcp.jar -aspectpath $HOME/DICE/traceLib.jar -1.5 -showWeaveInfo -log $HOME/DICE/weavelog_connector.log -outjar $HOME/DICE/jgossipAspect/WEB-INF/lib/commons-dbcp-aspects.jar
ajc -inpath $HOME/DICE/jgossipAspect/WEB-INF/lib/commons-beanutils.jar -aspectpath $HOME/DICE/traceLib.jar -1.5 -showWeaveInfo -log $HOME/DICE/weavelog_beanutils.log -outjar $HOME/DICE/jgossipAspect/WEB-INF/lib/commons-beanutils-aspects.jar
rm $HOME/DICE/jgossipAspect/WEB-INF/lib/commons-dbcp.jar
rm $HOME/DICE/jgossipAspect/WEB-INF/lib/commons-beanutils.jar


rm -rf $HOME/DICE/jgossipAspect/WEB-INF/classes
mkdir $HOME/DICE/jgossipAspect/WEB-INF/classes
unzip $HOME/DICE/jgossipAspect/WEB-INF/tracedjgossipClasses.jar -d $HOME/DICE/jgossipAspect/WEB-INF/classes
rm $HOME/DICE/jgossipAspect/WEB-INF/tracedjgossipClasses.jar

jar cvf $HOME/DICE/jgossip.war -C $HOME/DICE/jgossipAspect .
