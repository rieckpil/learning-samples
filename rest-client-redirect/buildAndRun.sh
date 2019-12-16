#!/bin/sh
mvn clean package && docker build -t de.rieckpil.learning/rest-client-redirect .
docker rm -f rest-client-redirect || true && docker run -d -p 9080:9080 -p 9443:9443 --name rest-client-redirect de.rieckpil.learning/rest-client-redirect