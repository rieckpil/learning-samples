@echo off
call mvn clean package
call docker build -t de.rieckpil.learning/liberty-vpot-october .
call docker rm -f liberty-vpot-october
call docker run -d -p 8080:8080 -p 4848:4848 --name liberty-vpot-october de.rieckpil.learning/liberty-vpot-october