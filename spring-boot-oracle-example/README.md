# Install Oracle JDBC driver to local Maven repository

```bash
mvn install:install-file -Dfile=ojdbc8.jar  -DgroupId=com.oracle -DartifactId=ojdbc8 -Dversion=18.3 -Dpackaging=jar
```