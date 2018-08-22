package de.rieckpil.learning.springbootmultidatasources;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class DatabaseConnector {

    private List<DataSource> dataSourceList;

    @PostConstruct
    public void init() throws SQLException {

        DataSource dataSource1 = DataSourceBuilder.create().password("postgres").username("postgres").url("jdbc" +
                ":postgresql://localhost:5432/postgres-1").driverClassName("org.postgresql.Driver").build();

        System.out.println(dataSource1.getConnection().getMetaData().getDatabaseProductVersion());

        DataSource dataSource2 = DataSourceBuilder.create().password("postgres").username("postgres").url("jdbc" +
                ":postgresql://localhost:5432/postgres-2").driverClassName("org.postgresql.Driver").build();

        System.out.println(dataSource2.getConnection().getMetaData().getDatabaseProductVersion());

        DataSource dataSource3 = DataSourceBuilder.create().password("postgres").username("postgres").url("jdbc" +
                ":postgresql://localhost:5432/postgres-3").driverClassName("org.postgresql.Driver").build();

        ResultSet resultSet = dataSource3.getConnection().createStatement().executeQuery("SELECT * FROM test");

        while (resultSet.next()) {
            System.out.println("ID: " + resultSet.getLong(1) + " Text: " + resultSet.getString(2));
        }

        System.out.println(dataSource3.getConnection().getMetaData().getDatabaseProductVersion());

        DataSource dataSource4 = DataSourceBuilder.create().password("postgres").username("postgres").url("jdbc" +
                ":postgresql://localhost:5432/postgres-4").driverClassName("org.postgresql.Driver").build();

        System.out.println(dataSource4.getConnection().getMetaData().getDatabaseProductVersion());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource4);
        List query = jdbcTemplate.query("SELECT* FROM test", new TestRowMapper());

        System.out.println("With row mapping \n");
        for (Object object : query) {
            Test t = (Test) object;
            System.out.println(t.toString());
        }

        DataSource dataSource5 = DataSourceBuilder.create().password("postgres").username("postgres").url("jdbc" +
                ":postgresql://localhost:5432/postgres-5").driverClassName("org.postgresql.Driver").build();

        System.out.println(dataSource5.getConnection().getMetaData().getDatabaseProductVersion());

    }
}
