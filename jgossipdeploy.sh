#!/bin/bash
rm *.log*
rm *.log
rm *.lck
rm *.1
rm ext*

rm tomcat/logs/*
cp tomcat/prelib/*.jar tomcat/lib
ant -buildfile jgossip/build.xml dist -Ddeploy_env=default
ant -buildfile aspect/ajjgossipbuild.xml
