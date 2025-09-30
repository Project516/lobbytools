#!/bin/sh

./buildall.sh
mkdir -p server/plugins
rm -f server/plugins/joinleave.jar
cp builds/joinleave.jar server/plugins/joinleave.jar
