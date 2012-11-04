#!/bin/bash

rm *.eps
for file in `ls *.png *.PNG`
do
	echo "Converting $file..."
	convfile=${file%.png}.eps

	convert $file $convfile
done
for file in `ls *.svg *.SVG`
do
	echo "Converting $file..."
	convfile=${file%.svg}.eps

	convert $file $convfile
done
echo
echo "Done"
