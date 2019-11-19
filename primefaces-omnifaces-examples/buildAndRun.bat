@echo off
call mvn clean package
call docker build -t de.rieckpil.learning/primefaces-omnifaces-examples .
call docker rm -f primefaces-omnifaces-examples
call docker run -d -p 9080:9080 -p 9443:9443 --name primefaces-omnifaces-examples de.rieckpil.learning/primefaces-omnifaces-examples