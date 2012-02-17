#!/bin/bash

rm $HOME/DICE/weavelog.log
rm $HOME/DICE/weavelog_connector.log
rm $HOME/DICE/weavelog_beanutils.log
rm $HOME/DICE/aspectcompile.log

export CLASSPATH="$HOME/DICE/src/lib/*:$HOME/DICE/tomcat/lib/*:$HOME/DICE/jgossip/database/oracle/lib/*:$HOME/DICE/jgossip/database/mysql/lib/*:$HOME/DICE/jgossip/lib/*"
export JAVA_HOME="$HOME/DICE/ibm-java"


ajc -sourceroots $HOME/DICE/src -1.5 -log $HOME/DICE/aspectcompile.log -outjar $HOME/DICE/traceLib.jar
cp $HOME/DICE/traceLib.jar $HOME/DICE/tomcat/lib

rm -rf $HOME/DICE/jgossipAspect
mkdir $HOME/DICE/jgossipAspect
unzip $HOME/DICE/jgossip/dist/default/jgossip.war -d $HOME/DICE/jgossipAspect
jar cfM $HOME/DICE/jgossipAspect/WEB-INF/jgossipClasses.jar -C $HOME/DICE/jgossipAspect/WEB-INF/classes .

ajc -inpath $HOME/DICE/jgossipAspect/WEB-INF/jgossipClasses.jar -aspectpath $HOME/DICE/traceLib.jar -1.5 -showWeaveInfo -log $HOME/DICE/weavelog.log -outjar $HOME/DICE/jgossipAspect/WEB-INF/tracedjgossipClasses.jar
rm $HOME/DICE/jgossipAspect/WEB-INF/jgossipClasses.jar

cd $HOME/DICE/jgossipAspect/WEB-INF/lib
for jarfile in *.jar
do
	ajc -inpath $HOME/DICE/jgossipAspect/WEB-INF/lib/$jarfile -aspectpath $HOME/DICE/traceLib.jar -1.5 -showWeaveInfo -log $HOME/DICE/weavelog_$jarfile.log -outjar $HOME/DICE/jgossipAspect/WEB-INF/lib/aspects-$jarfile
	rm $HOME/DICE/jgossipAspect/WEB-INF/lib/$jarfile
done
#ajc -inpath $HOME/DICE/jgossipAspect/WEB-INF/lib/commons-dbcp.jar -aspectpath $HOME/DICE/traceLib.jar -1.5 -showWeaveInfo -log $HOME/DICE/weavelog_connector.log -outjar $HOME/DICE/jgossipAspect/WEB-INF/lib/commons-dbcp-aspects.jar
#ajc -inpath $HOME/DICE/jgossipAspect/WEB-INF/lib/commons-beanutils.jar -aspectpath $HOME/DICE/traceLib.jar -1.5 -showWeaveInfo -log $HOME/DICE/weavelog_beanutils.log -outjar $HOME/DICE/jgossipAspect/WEB-INF/lib/commons-beanutils-aspects.jar
#rm $HOME/DICE/jgossipAspect/WEB-INF/lib/commons-dbcp.jar
#rm $HOME/DICE/jgossipAspect/WEB-INF/lib/commons-beanutils.jar

echo "Removing old classes dir"
rm -rf $HOME/DICE/jgossipAspect/WEB-INF/classes
echo "Creating new classes dir"
mkdir $HOME/DICE/jgossipAspect/WEB-INF/classes
ls $HOME/DICE/jgossipAspect/WEB-INF
echo "Extracting new jgossip classes"
unzip $HOME/DICE/jgossipAspect/WEB-INF/tracedjgossipClasses.jar -d $HOME/DICE/jgossipAspect/WEB-INF/classes
ls $HOME/DICE/jgossipAspect/WEB-INF/classes
rm $HOME/DICE/jgossipAspect/WEB-INF/tracedjgossipClasses.jar

echo "Packaging war"
jar cvf $HOME/DICE/jgossip.war -C $HOME/DICE/jgossipAspect .
rm -rf $HOME/DICE/jgossipAspect
rm $HOME/DICE/traceLib.jar
#!/bin/bash

for file in *.sh
do
	echo $file
done

cd $HOME/DICE/jgossipAspect/WEB-INF/lib

