#!/bin/sh
mvn clean package && docker build -t de.rieckpil.learning/oracle-code-examples .
docker rm -f oracle-code-examples || true && docker run -d -p 8080:8080 -p 4848:4848 --name oracle-code-examples de.rieckpil.learning/oracle-code-examples