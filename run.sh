#!/bin/bash

web="classes"
fileName="framework"
lib="lib/*"

#make web-inf and compile java files
mkdir -p "$web"
javac -cp "$lib" -d "$web" $(find src -name "*.java")

#remove .jar file
rm -f "$fileName".jar
#create a new jar file from all classes directory
jar -cvf "$fileName".jar -C classes/ .