#!/bin/bash

web="classes"
fileName="framework"
lib="src/lib/*"
build="build"
testApp="/mnt/1d147fb2-afa6-4acc-9a5f-c749307cb988/github project/itu.S4.WebDynamique.java.test_framework"

# make web-inf and compile java files
mkdir -p "$build/$web"
javac -parameters -cp "$lib" -d "$build/$web" $(find src -name "*.java")

# remove .jar file
rm -f "$fileName.jar"
# create a new jar file from all classes directory
jar -cvf "$fileName.jar" -C build/classes/ .

# remove .jar in test application
rm -f "$testApp/src/webapps/WEB-INF/lib/$fileName.jar"
# copy new .jar file in test application
cp "$fileName.jar" "$testApp/src/webapps/WEB-INF/lib/"