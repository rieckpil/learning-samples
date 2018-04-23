/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airhacks;

import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationInfo;

/**
 *
 * @author Philip
 */
@Startup
@Singleton
public class DatabaseMigrator  {
    
	private final Logger log = Logger.getLogger(DatabaseMigrator.class.getName());
 
	@Resource(lookup = "jdbc/__default")
	private DataSource dataSource;
 
	@PostConstruct
	private void onStartup() {
		if (dataSource == null) {
			log.severe("no datasource found to execute the db migrations!");
			throw new EJBException(
					"no datasource found to execute the db migrations!");
		}
 
		Flyway flyway = new Flyway();
                flyway.baseline();
		flyway.setDataSource(dataSource);
		for (MigrationInfo i : flyway.info().all()) {
			log.info("migrate task: " + i.getVersion() + " : "
					+ i.getDescription() + " from file: " + i.getScript());
		}
		flyway.migrate();
	}
}