#!/bin/bash

touch $1
echo "<root>" >> $1
cat taintlog.log >> $1
echo "</root>" >> $1
