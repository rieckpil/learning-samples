#!/bin/sh
mvn clean package && docker build -t de.rieckpil.blog/wildfly-hyperistence .
docker rm -f wildfly-hyperistence || true && docker run -p 8080:8080 -p 9990:9990 -it de.rieckpil.blog/wildfly-hyperistence /opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0 -bmanagement 0.0.0.0
