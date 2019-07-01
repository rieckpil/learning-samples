@echo off
call mvn clean package
call docker build -t de.rieckpil.learning/java-ee-kubernetes .
call docker rm -f java-ee-kubernetes
call docker run -d -p 8080:8080 -p 4848:4848 --name java-ee-kubernetes de.rieckpil.learning/java-ee-kubernetes