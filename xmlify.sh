#!/bin/bash

touch $1 $2
echo "<root>" >> $2
cat $1 | grep taintlog >> $2
echo "</root>" >> $2
