#!/bin/sh
mvn clean package && docker build -t de.rieckpil.learning/microprofile-workshop .
docker rm -f microprofile-workshop || true && docker run -d -p 8080:8080 -p 4848:4848 --name microprofile-workshop de.rieckpil.learning/microprofile-workshop