@echo off
call mvn clean package
call docker build -t de.rieckpil.learning/nats-streaming-jca .
call docker rm -f nats-streaming-jca
call docker run -d -p 8080:8080 -p 4848:4848 --name nats-streaming-jca de.rieckpil.learning/nats-streaming-jca