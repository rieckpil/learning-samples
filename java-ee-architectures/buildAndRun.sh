#!/bin/sh
mvn clean package && docker build -t de.rieckpil.learning/java-ee-architectures .
docker rm -f java-ee-architectures || true && docker run -d -p 8080:8080 -p 4848:4848 --name java-ee-architectures de.rieckpil.learning/java-ee-architectures