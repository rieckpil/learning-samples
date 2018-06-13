# Build
mvn clean package && docker build -t de.rieckpil.learning/javaee8-security .

# RUN

docker rm -f javaee8-security || true && docker run -d -p 8080:8080 -p 4848:4848 --name javaee8-security de.rieckpil.learning/javaee8-security 