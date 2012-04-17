#!/bin/bash

export CLASSPATH="$HOME/DICE/src/lib/*:$HOME/DICE/aspect/lib/*:$HOME/DICE/tomcat/lib/*:$HOME/DICE/jgossip/database/oracle/lib/*:$HOME/DICE/jgossip/database/mysql/lib/*:$HOME/DICE/jgossip/lib/*"

ajc -sourceroots $HOME/DICE/aspect/ajsrc -1.5 -log $HOME/DICE/aspectcompile.log -outjar $HOME/DICE/traceLib.jar

exit 0
cp $HOME/DICE/traceLib.jar $HOME/DICE/tomcat/lib

rm -rf $HOME/DICE/jgossipAspect
mkdir $HOME/DICE/jgossipAspect
unzip $HOME/DICE/jgossip/dist/default/jgossip.war -d $HOME/DICE/jgossipAspect
jar cfM $HOME/DICE/jgossipAspect/WEB-INF/jgossipClasses.jar -C $HOME/DICE/jgossipAspect/WEB-INF/classes .

ajc -Xlint:ignore -nowarn -g:none -inpath $HOME/DICE/jgossipAspect/WEB-INF/jgossipClasses.jar -aspectpath $HOME/DICE/traceLib.jar -1.5 -outjar $HOME/DICE/jgossipAspect/WEB-INF/tracedjgossipClasses.jar
#ajc -showWeaveInfo -log $HOME/DICE/weavelog.log -inpath $HOME/DICE/jgossipAspect/WEB-INF/jgossipClasses.jar -aspectpath $HOME/DICE/traceLib.jar -1.5 -outjar $HOME/DICE/jgossipAspect/WEB-INF/tracedjgossipClasses.jar
rm $HOME/DICE/jgossipAspect/WEB-INF/jgossipClasses.jar

cd $HOME/DICE/jgossipAspect/WEB-INF/lib
mkdir temp
for jarfile in *.jar
do
	ajc -Xlint:ignore -nowarn -g:none -inpath $HOME/DICE/jgossipAspect/WEB-INF/lib/$jarfile -aspectpath $HOME/DICE/traceLib.jar -1.5 -outjar $HOME/DICE/jgossipAspect/WEB-INF/lib/temp/$jarfile
#	ajc -showWeaveInfo -log $HOME/DICE/$jarfile.log -inpath $HOME/DICE/jgossipAspect/WEB-INF/lib/$jarfile -aspectpath $HOME/DICE/traceLib.jar -1.5 -outjar $HOME/DICE/jgossipAspect/WEB-INF/lib/temp/$jarfile
	echo $jarfile
done
rm $HOME/DICE/jgossipAspect/WEB-INF/lib/*.jar
mv $HOME/DICE/jgossipAspect/WEB-INF/lib/temp/* $HOME/DICE/jgossipAspect/WEB-INF/lib/
rm -rf $HOME/DICE/jgossipAspect/WEB-INF/lib/temp
#ajc -inpath $HOME/DICE/jgossipAspect/WEB-INF/lib/commons-dbcp.jar -aspectpath $HOME/DICE/traceLib.jar -1.5 -showWeaveInfo -log $HOME/DICE/weavelog_connector.log -outjar $HOME/DICE/jgossipAspect/WEB-INF/lib/commons-dbcp-aspects.jar
#ajc -inpath $HOME/DICE/jgossipAspect/WEB-INF/lib/commons-beanutils.jar -aspectpath $HOME/DICE/traceLib.jar -1.5 -showWeaveInfo -log $HOME/DICE/weavelog_beanutils.log -outjar $HOME/DICE/jgossipAspect/WEB-INF/lib/commons-beanutils-aspects.jar
#rm $HOME/DICE/jgossipAspect/WEB-INF/lib/commons-dbcp.jar
#rm $HOME/DICE/jgossipAspect/WEB-INF/lib/commons-beanutils.jar

rm -rf $HOME/DICE/jgossipAspect/WEB-INF/classes
mkdir $HOME/DICE/jgossipAspect/WEB-INF/classes
unzip $HOME/DICE/jgossipAspect/WEB-INF/tracedjgossipClasses.jar -d $HOME/DICE/jgossipAspect/WEB-INF/classes
rm $HOME/DICE/jgossipAspect/WEB-INF/tracedjgossipClasses.jar

jar cvf $HOME/DICE/jgossip.war -C $HOME/DICE/jgossipAspect .
rm -rf $HOME/DICE/jgossipAspect
rm $HOME/DICE/traceLib.jar

