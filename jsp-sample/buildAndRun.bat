@echo off
call mvn clean package
call docker build -t de.rieckpil.learning/jsp-sample .
call docker rm -f jsp-sample
call docker run -d -p 8080:8080 -p 4848:4848 --name jsp-sample de.rieckpil.learning/jsp-sample