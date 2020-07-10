# Project de.rieckpil.blog/wildfly-var-replacing

Steps to run this project:

1. Start your Docker daemon
2. Start a PostgreSQL database `docker run --name some-postgres -e POSTGRES_PASSWORD=duke -p 5432:5432  postgres`
3. Execute `./buildAndRun.sh` (Linux/MacOs) or `buildAndRun.bat` (Windows)
4. Check logs with `docker logs -f CONTAINER_ID`
