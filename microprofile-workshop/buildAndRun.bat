@echo off
call mvn clean package
call docker build -t de.rieckpil.learning/microprofile-workshop .
call docker rm -f microprofile-workshop
call docker run -d -p 8080:8080 -p 4848:4848 --name microprofile-workshop de.rieckpil.learning/microprofile-workshop