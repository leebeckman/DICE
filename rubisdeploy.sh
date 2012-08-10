#!/bin/bash
rm *.log*
rm *.log
rm *.lck
rm *.1
rm ext*

rm tomcat/logs/*
cp tomcat/prelib/*.jar tomcat/lib
rm -rf RUBiS/Servlets/build
rm RUBiS/Servlets/rubis_servlets.jar
ant -buildfile RUBiS/Servlets/build.xml
ant -buildfile aspect/ajrubisbuild.xml
