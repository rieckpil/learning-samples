#!/bin/sh
mvn clean package && docker build -t de.rieckpil.learning/liberty-vpot-october .
docker rm -f liberty-vpot-october || true && docker run -d -p 8080:8080 -p 4848:4848 --name liberty-vpot-october de.rieckpil.learning/liberty-vpot-october