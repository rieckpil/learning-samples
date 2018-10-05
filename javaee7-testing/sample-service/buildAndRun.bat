@echo off
call mvn clean package
call docker build -t de.rieckpil.learning/sample-service .
call docker rm -f sample-service
call docker run -d -p 8080:8080 -p 4848:4848 --name sample-service de.rieckpil.learning/sample-service