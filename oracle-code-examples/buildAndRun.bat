@echo off
call mvn clean package
call docker build -t de.rieckpil.learning/oracle-code-examples .
call docker rm -f oracle-code-examples
call docker run -d -p 8080:8080 -p 4848:4848 --name oracle-code-examples de.rieckpil.learning/oracle-code-examples