#!/bin/sh

cd joinleave
./gradlew :spotlessApply
./gradlew build
cd ..
cp joinleave/build/libs/joinleave.jar builds/joinleave.jar
