FROM jboss/wildfly:20.0.0.Final

ENV DATABASE_PASSWORD duke
ENV DATABASE_USERNAME postgres
ENV DATABASE_HOST 172.17.0.1
ENV DATABASE_DATABASENAME postgres

ADD target/wildfly-var-replacing.war /opt/jboss/wildfly/standalone/deployments/ROOT.war
ADD standalone-custom.xml /opt/jboss/wildfly/standalone/configuration/

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-c", "standalone-custom.xml", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
