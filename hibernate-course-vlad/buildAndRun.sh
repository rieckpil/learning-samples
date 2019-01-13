#!/bin/sh
mvn clean package && docker build -t de.rieckpil.learning/hibernate-course-vlad .
docker rm -f hibernate-course-vlad || true && docker run -d -p 8080:8080 -p 4848:4848 --name hibernate-course-vlad de.rieckpil.learning/hibernate-course-vlad