#!/bin/bash
java -Dconf=src/main/resources/opensea.conf -jar target/parallel-leverage-test-1.0-SNAPSHOT.jar

# 获取 ui detail 数据
#java -Dconf=src/main/resources/opensea.conf -jar target/parallel-leverage-test-1.0-SNAPSHOT.jar -ui details
#获取 ui collection 数据
java -Dconf=src/main/resources/opensea.conf -jar target/parallel-leverage-test-1.0-SNAPSHOT.jar -ui collection
