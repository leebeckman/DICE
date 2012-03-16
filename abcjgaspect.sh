#!/bin/bash

export JAVA_HOME="$HOME/jdk1.5.0_22"

rm -rf $HOME/DICE/jgossipAspect
mkdir $HOME/DICE/jgossipAspect
unzip $HOME/DICE/jgossip/dist/default/jgossip.war -d $HOME/DICE/jgossipAspect
jar cfM $HOME/DICE/jgossipAspect/WEB-INF/jgossipClasses.jar -C $HOME/DICE/jgossipAspect/WEB-INF/classes .

#$HOME/DICE/jgossip/lib/activation.jar \
java -jar $HOME/abc/abc-ja-exts-complete.jar -sourceroots $HOME/DICE/src -cp \
$HOME/DICE/jgossip/lib/jstl.jar:\
$HOME/DICE/jdk1.5.0_22/jre/lib/rt.jar:\
$HOME/DICE/jgossip/database/mysql/lib/mysql-connector-java-3.0.14-production-bin.jar:\
$HOME/DICE/jgossip/database/oracle/lib/ojdbc14.jar:\
$HOME/abc/abc-runtime.jar:\
$HOME/abc/abc-ja-exts-complete.jar:\
$HOME/DICE/jgossip/lib/acme.jar:\
$HOME/DICE/jgossip/lib/commons-beanutils.jar:\
$HOME/DICE/jgossip/lib/commons-collections.jar:\
$HOME/DICE/jgossip/lib/commons-dbcp.jar:\
$HOME/DICE/jgossip/lib/commons-digester.jar:\
$HOME/DICE/jgossip/lib/commons-fileupload.jar:\
$HOME/DICE/jgossip/lib/commons-lang.jar:\
$HOME/DICE/jgossip/lib/commons-logging.jar:\
$HOME/DICE/jgossip/lib/commons-pool.jar:\
$HOME/DICE/jgossip/lib/commons-resources.jar:\
$HOME/DICE/jgossip/lib/commons-validator.jar:\
$HOME/DICE/jgossip/lib/evelopers-common.jar:\
$HOME/DICE/jgossip/lib/jakarta-oro-2.0.8.jar:\
$HOME/DICE/jgossip/lib/je.jar:\
$HOME/DICE/jgossip/lib/jssplitter.jar:\
$HOME/DICE/jgossip/lib/logkit-1.2.2.jar:\
$HOME/DICE/jgossip/lib/mailapi.jar:\
$HOME/DICE/jgossip/lib/memo.jar:\
$HOME/DICE/jgossip/lib/quartz.jar:\
$HOME/DICE/jgossip/lib/standard.jar:\
$HOME/DICE/jgossip/lib/struts.jar:\
$HOME/DICE/src/lib/ant.jar:\
$HOME/DICE/src/lib/antlr-3.4-complete.jar:\
$HOME/DICE/src/lib/avalon-framework-4.1.4.jar:\
$HOME/DICE/src/lib/bcel-5.2.jar:\
$HOME/DICE/src/lib/commons-discovery-0.5.jar:\
$HOME/DICE/src/lib/commons-lang.jar:\
$HOME/DICE/src/lib/dom4j-1.6.1.jar:\
$HOME/DICE/src/lib/j2ee-1.4.jar:\
$HOME/DICE/src/lib/jaxen.jar:\
$HOME/DICE/src/lib/jboss-3.2.1.jar:\
$HOME/DICE/src/lib/jboss-common-client-3.2.3.jar:\
$HOME/DICE/src/lib/jboss-jmx-4.0.2.jar:\
$HOME/DICE/src/lib/jboss-system-4.0.2.jar:\
$HOME/DICE/src/lib/jdom-1.1.2.jar:\
$HOME/DICE/src/lib/log4j-1.2.16.jar:\
$HOME/DICE/src/lib/resolver.jar:\
$HOME/DICE/src/lib/saxpath.jar:\
$HOME/DICE/src/lib/simmetrics_jar_v1_6_2_d07_02_07.jar:\
$HOME/DICE/src/lib/weblogic.jar:\
$HOME/DICE/src/lib/xalan.jar:\
$HOME/DICE/src/lib/xerces.jar:\
$HOME/DICE/src/lib/xml-apis.jar:\
$HOME/DICE/src/lib/xom-1.2.7.jar\
 -injars $HOME/DICE/jgossipAspect/WEB-INF/jgossipClasses.jar -outjar $HOME/DICE/jgossipAspect/WEB-INF/tracedjgossipClasses.jar
rm $HOME/DICE/jgossipAspect/WEB-INF/jgossipClasses.jar

#cd $HOME/DICE/jgossipAspect/WEB-INF/lib
#mkdir temp
#for jarfile in *.jar
#do
#	ajc -Xlint:ignore -nowarn -g:none -inpath $HOME/DICE/jgossipAspect/WEB-INF/lib/$jarfile -aspectpath $HOME/DICE/traceLib.jar -1.5 -outjar $HOME/DICE/jgossipAspect/WEB-INF/lib/temp/$jarfile
#	echo $jarfile
#done
#rm $HOME/DICE/jgossipAspect/WEB-INF/lib/*.jar
#mv $HOME/DICE/jgossipAspect/WEB-INF/lib/temp/* $HOME/DICE/jgossipAspect/WEB-INF/lib/
#rm -rf $HOME/DICE/jgossipAspect/WEB-INF/lib/temp

rm -rf $HOME/DICE/jgossipAspect/WEB-INF/classes
mkdir $HOME/DICE/jgossipAspect/WEB-INF/classes
unzip $HOME/DICE/jgossipAspect/WEB-INF/tracedjgossipClasses.jar -d $HOME/DICE/jgossipAspect/WEB-INF/classes
rm $HOME/DICE/jgossipAspect/WEB-INF/tracedjgossipClasses.jar

jar cvf $HOME/DICE/jgossip.war -C $HOME/DICE/jgossipAspect .
rm -rf $HOME/DICE/jgossipAspect
