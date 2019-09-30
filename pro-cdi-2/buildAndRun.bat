@echo off
call mvn clean package
call docker build -t de.rieckpil.learning/pro-cdi-2 .
call docker rm -f pro-cdi-2
call docker run -d -p 9080:9080 -p 9443:9443 --name pro-cdi-2 de.rieckpil.learning/pro-cdi-2