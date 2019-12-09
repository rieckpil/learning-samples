@echo off
call mvn clean package
call docker build -t de.rieckpil.blog/wildfly-hyperistence .
call docker rm -f wildfly-hyperistence
call docker run -d -p 8080:8080 -p 9990:9990 --name wildfly-hyperistence de.rieckpil.blog/wildfly-hyperistence