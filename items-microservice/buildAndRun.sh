#!/bin/sh
mvn clean package && docker build -t de.rieckpil.learning/items-microservice .
docker rm -f items-microservice || true && docker run -d -p 8080:8080 -p 4848:4848 --name items-microservice de.rieckpil.learning/items-microservice 
