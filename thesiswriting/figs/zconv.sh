#!/bin/bash

rm *.eps
for file in `ls *.png`
do
	echo "Converting $file..."
	convfile=${file%.png}.eps

	convert $file $convfile
done
echo
echo "Done"
