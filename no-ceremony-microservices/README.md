# Build
mvn clean package && docker build -t de.rieckpil.learning/no-ceremony-microservices .

# RUN

docker rm -f no-ceremony-microservices || true && docker run -d -p 8080:8080 -p 4848:4848 --name no-ceremony-microservices de.rieckpil.learning/no-ceremony-microservices 