@echo off
call mvn clean package
call docker build -t de.rieckpil.learning/rest-client-redirect .
call docker rm -f rest-client-redirect
call docker run -d -p 9080:9080 -p 9443:9443 --name rest-client-redirect de.rieckpil.learning/rest-client-redirect