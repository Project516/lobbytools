#!/bin/sh

cd joinleave
./gradlew build
cd ..
cp joinleave/build/libs/joinleave.jar build/joinleave.jar