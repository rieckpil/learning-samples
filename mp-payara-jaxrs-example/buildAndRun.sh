#!/bin/sh
mvn clean package && docker build -t de.rieckpil.learning/mp-payara-jaxrs-example .
docker rm -f mp-payara-jaxrs-example || true && docker run -d -p 8080:8080 -p 4848:4848 --name mp-payara-jaxrs-example de.rieckpil.learning/mp-payara-jaxrs-example