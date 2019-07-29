@echo off
call mvn clean package
call docker build -t de.rieckpil.learning/mp-payara-jaxrs-example .
call docker rm -f mp-payara-jaxrs-example
call docker run -p 8080:8080 -p 4848:4848 --name mp-payara-jaxrs-example de.rieckpil.learning/mp-payara-jaxrs-example