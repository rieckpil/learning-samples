#!/bin/sh
mvn clean package && docker build -t de.rieckpil.learning/jsp-sample .
docker rm -f jsp-sample || true && docker run -d -p 8080:8080 -p 4848:4848 --name jsp-sample de.rieckpil.learning/jsp-sample