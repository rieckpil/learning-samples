#!/bin/sh
mvn clean package && docker build -t de.rieckpil.learning/sample-service-st .
docker rm -f sample-service-st || true && docker run -d -p 8080:8080 -p 4848:4848 --name sample-service-st de.rieckpil.learning/sample-service-st