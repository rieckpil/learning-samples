#!/bin/sh
mvn clean package && docker build -t de.rieckpil.learning/primefaces-omnifaces-examples .
docker rm -f primefaces-omnifaces-examples || true && docker run -d -p 9080:9080 -p 9443:9443 --name primefaces-omnifaces-examples de.rieckpil.learning/primefaces-omnifaces-examples