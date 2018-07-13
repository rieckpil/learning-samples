# Build
mvn clean package && docker build -t de.rieckpil.learning/javaee-8-cookbook .

# RUN

docker rm -f javaee-8-cookbook || true && docker run -d -p 8080:8080 -p 4848:4848 --name javaee-8-cookbook de.rieckpil.learning/javaee-8-cookbook 