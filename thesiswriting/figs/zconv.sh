#!/bin/bash

for file in `ls *.png *.PNG`
do
	echo "Converting $file..."
	convfile=${file%.png}.eps

	convert $file $convfile
done
echo "Done"
