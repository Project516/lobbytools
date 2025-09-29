#!/bin/sh

./buildall.sh
mkdir -p server/plugins
rm -f server/plugins/joinleave.jar
rm -f server/plugins/chatcalc.jar
cp builds/joinleave.jar server/plugins/joinleave.jar
cp build/chatcalc.jar server/plugins/chatcalc.jar
