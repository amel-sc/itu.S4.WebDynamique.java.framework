#!/bin/bash

web="classes"
fileName="framework"
lib="src/lib/*"
build="build"

#make web-inf and compile java files
mkdir -p "$build/$web"
javac -parameters -cp "$lib" -d "$build/$web" $(find src -name "*.java")

#remove .jar file
rm -f "$fileName".jar
#create a new jar file from all classes directory
jar -cvf "$fileName".jar -C build/classes/ .