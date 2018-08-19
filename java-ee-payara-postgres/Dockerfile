FROM payara/server-full

COPY target/payara-postgres.war $DEPLOY_DIR
COPY postgresql-42.2.4.jar $PAYARA_PATH/glassfish/domains/domain1/lib

RUN $PAYARA_PATH/bin/asadmin start-domain domain1 && \
    $PAYARA_PATH/bin/asadmin --user=admin --passwordfile=/opt/pwdfile create-jdbc-connection-pool \
    --datasourceclassname org.postgresql.ds.PGConnectionPoolDataSource \
    --restype javax.sql.ConnectionPoolDataSource \
    --property user=postgres:password=example:DatabaseName=postgres:ServerName=postgres:port=5432 PostgreSQLPool && \
    $PAYARA_PATH/bin/asadmin stop-domain domain1

RUN $PAYARA_PATH/bin/asadmin start-domain domain1 && \
    $PAYARA_PATH/bin/asadmin --user=admin --passwordfile=/opt/pwdfile create-jdbc-resource --connectionpoolid \
      PostgreSQLPool jdbc/__postgres && \
    $PAYARA_PATH/bin/asadmin stop-domain domain1
