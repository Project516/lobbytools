#!/bin/sh

./buildall.sh
mkdir -p server/plugins
rm -f server/plugins/joinleave.jar
cp build/joinleave.jar server/plugins/joinleave.jar
