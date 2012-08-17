#!/bin/bash

echo "Current Java Processes:"
ps -A | grep java

echo
echo "Killing..."
echo

for killpid in `ps -A | grep java | grep pts | grep -o "[0-9][0-9][0-9][0-9]*"`
do
	kill -9 $killpid
done
ps -A | grep java

rm *.log
rm *.lck
