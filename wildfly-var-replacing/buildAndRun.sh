#!/bin/sh
if [ $(docker ps -a -f name=wildfly-var-replacing | grep -w wildfly-var-replacing | wc -l) -eq 1 ]; then
  docker rm -f wildfly-var-replacing
fi
mvn clean package && docker build -t de.rieckpil.blog/wildfly-var-replacing .
docker run -d -p 8080:8080 -p 9990:9990 --name wildfly-var-replacing de.rieckpil.blog/wildfly-var-replacing
