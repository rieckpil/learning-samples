#!/bin/sh
mvn clean package && docker build -t de.rieckpil.learning/jsf-ee8-samples .
docker rm -f jsf-ee8-samples || true && docker run -d -p 8080:8080 -p 4848:4848 --name jsf-ee8-samples de.rieckpil.learning/jsf-ee8-samples 
