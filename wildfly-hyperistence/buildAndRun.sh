#!/bin/sh
mvn clean package && docker build -t de.rieckpil.blog/wildfly-hyperistence .
docker rm -f wildfly-hyperistence || true && docker run -d -p 8080:8080 -p 9990:9990 --name wildfly-hyperistence de.rieckpil.blog/wildfly-hyperistence