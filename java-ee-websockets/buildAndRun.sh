#!/bin/sh
mvn clean package && docker build -t de.rieckpil.learning/java-ee-websockets .
docker rm -f java-ee-websockets || true && docker run -d -p 8080:8080 -p 4848:4848 --name java-ee-websockets de.rieckpil.learning/java-ee-websockets