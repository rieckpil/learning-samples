#!/bin/sh
mvn clean package && docker build -t de.rieckpil/javaee8 .
docker rm -f javaee8 || true && docker run -d -p 8080:8080 -p 4848:4848 --name javaee8 de.rieckpil/javaee8 
