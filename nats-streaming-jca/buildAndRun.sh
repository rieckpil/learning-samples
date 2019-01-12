#!/bin/sh
mvn clean package && docker build -t de.rieckpil.learning/nats-streaming-jca .
docker rm -f nats-streaming-jca || true && docker run -d -p 8080:8080 -p 4848:4848 --name nats-streaming-jca de.rieckpil.learning/nats-streaming-jca