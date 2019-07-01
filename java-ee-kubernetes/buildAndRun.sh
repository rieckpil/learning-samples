#!/bin/sh
mvn clean package && docker build -t de.rieckpil.learning/java-ee-kubernetes .
docker rm -f java-ee-kubernetes || true && docker run -d -p 8080:8080 -p 4848:4848 --name java-ee-kubernetes de.rieckpil.learning/java-ee-kubernetes