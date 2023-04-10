#!/bin/bash
java -Dconf=src/main/resources/opensea.conf -jar target/parallel-leverage-test-1.0-SNAPSHOT.jar $1 $2 $3
echo "$0"
echo "$1"
echo "$2"
echo "$3"
