#!/bin/sh
mvn clean package && docker build -t de.rieckpil.learning/jsf-sample-app .
docker rm -f jsf-sample-app || true && docker run -d -p 8080:8080 -p 4848:4848 --name jsf-sample-app de.rieckpil.learning/jsf-sample-app