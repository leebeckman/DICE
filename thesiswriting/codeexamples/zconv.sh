#!/bin/bash

rm *.lns
for file in `ls *.code`
do
	echo "Converting $file..."
	python ln.py $file
done
echo "Done"
