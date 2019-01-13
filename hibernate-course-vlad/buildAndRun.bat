@echo off
call mvn clean package
call docker build -t de.rieckpil.learning/hibernate-course-vlad .
call docker rm -f hibernate-course-vlad
call docker run -d -p 8080:8080 -p 4848:4848 --name hibernate-course-vlad de.rieckpil.learning/hibernate-course-vlad