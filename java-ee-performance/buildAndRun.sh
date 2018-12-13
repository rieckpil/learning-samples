#!/bin/sh
mvn clean package && docker build -t de.rieckpil.learning/java-ee-performance .
docker rm -f java-ee-performance || true && docker run -d -p 8080:8080 -p 4848:4848 --name java-ee-performance de.rieckpil.learning/java-ee-performance