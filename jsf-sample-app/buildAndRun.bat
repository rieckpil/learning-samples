@echo off
call mvn clean package
call docker build -t de.rieckpil.learning/jsf-sample-app .
call docker rm -f jsf-sample-app
call docker run -d -p 8080:8080 -p 4848:4848 --name jsf-sample-app de.rieckpil.learning/jsf-sample-app