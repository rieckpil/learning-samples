#!/bin/sh
mvn clean package && docker build -t de.rieckpil.learning/pro-cdi-2 .
docker rm -f pro-cdi-2 || true && docker run -d -p 9080:9080 -p 9443:9443 --name pro-cdi-2 de.rieckpil.learning/pro-cdi-2