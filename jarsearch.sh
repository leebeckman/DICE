#!/bin/bash
for i in `find -name "*.jar"`; do echo "$i"; jar -tvf "$i" | grep "$1"; done
