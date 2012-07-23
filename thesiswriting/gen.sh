#!/bin/bash

if [ "$1" = "doc" ]
then
	latex ubcsample
	bibtex ubcsample
	latex ubcsample
	latex ubcsample
fi

if [ "$1" = "wc" ]
then
	perl wordcount/texcount.pl ubcsample.tex
fi

