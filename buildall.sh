#!/bin/sh

cd joinleave
./gradlew build
cd ..
cp joinleave/build/libs/joinleave.jar builds/joinleave.jar

cd chatcalc
./gradlew build
cd ..
cp chatcalc/build/libs/chatcalc.jar builds/chatcalc.jar