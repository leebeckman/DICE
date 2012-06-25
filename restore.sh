#!/bin/bash
rm *.log*
rm *.log
rm *.lck
rm *.1
rm ext*

rm tomcat/logs/*
cd tomcat/lib
ls | grep -v aspect | xargs rm
cp ../prelib/*.jar ./
