#!/bin/sh
mvn clean package && docker build -t de.rieckpil.learning/javaee8-security .
docker rm -f javaee8-security || true && docker run -d -p 8080:8080 -p 4848:4848 --name javaee8-security de.rieckpil.learning/javaee8-security 
