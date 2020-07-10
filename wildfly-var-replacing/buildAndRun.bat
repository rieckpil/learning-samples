@echo off
call mvn clean package
call docker build -t de.rieckpil.blog/wildfly-var-replacing .
call docker rm -f wildfly-var-replacing
call docker run -d -p 8080:8080 -p 9990:9990 --name wildfly-var-replacing de.rieckpil.blog/wildfly-var-replacing
