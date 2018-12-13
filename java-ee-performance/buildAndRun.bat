@echo off
call mvn clean package
call docker build -t de.rieckpil.learning/java-ee-performance .
call docker rm -f java-ee-performance
call docker run -d -p 8080:8080 -p 4848:4848 --name java-ee-performance de.rieckpil.learning/java-ee-performance