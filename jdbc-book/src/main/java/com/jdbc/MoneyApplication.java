package com.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.time.LocalDateTime;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;

public class MoneyApplication {

  public static void main(String[] args) throws SQLException {
    DataSource ds = createDataSource();

    Connection connection = ds.getConnection();

    try (connection) {

      connection.setAutoCommit(false);

      int senderId = createUser(connection);
      int receiverId = createUser(connection);

      Savepoint savepoint = connection.setSavepoint();

      int transferId = sendMoney(connection, senderId, receiverId, 50);
      if (transferId < 0) connection.rollback(savepoint);

      connection.commit();

      System.out.println("Created users with senderId=" + senderId + "," +
        "receiverId=" + receiverId + " and transfer with id = " +
        transferId);
    }
    catch (SQLException e) {
      connection.rollback();
    }
  }

  private static int createUser(Connection connection) throws SQLException {
    try (PreparedStatement stmt = connection.prepareStatement("insert into " +
        "users (first_name, last_name, registration_date) values " +
        "(?,?,?)"
      , Statement.RETURN_GENERATED_KEYS)) {
      stmt.setString(1, "[Some FirstName]");
      stmt.setString(2, "[Some LastName]");
      stmt.setObject(3, LocalDateTime.now());
      stmt.executeUpdate();
      final ResultSet keysResultSet = stmt.getGeneratedKeys();
      keysResultSet.next();
      return keysResultSet.getInt(1);
    }
  }

  private static int sendMoney(Connection connection, int senderId,
    int receiverId, int amount) throws SQLException {
    try (PreparedStatement stmt = connection.prepareStatement("update users " +
      "set balance = (balance - ?) where id = ?")) {
      stmt.setInt(1, amount);
      stmt.setInt(2, senderId);
      stmt.executeUpdate();
    }
    try (PreparedStatement stmt = connection.prepareStatement("update users " +
      "set balance = (balance + ?) where id = ?")) {
      stmt.setInt(1, amount);
      stmt.setInt(2, receiverId);
      stmt.executeUpdate();
    }
    try (PreparedStatement stmt = connection.prepareStatement("insert into " +
        "transfers (sender, receiver, amount) values (?,?,?)"
      , Statement.RETURN_GENERATED_KEYS)) {
      stmt.setInt(1, senderId);
      stmt.setInt(2, receiverId);
      stmt.setInt(3, amount);
      stmt.executeUpdate();
      final ResultSet keysResultSet = stmt.getGeneratedKeys();
      keysResultSet.next();
      return keysResultSet.getInt(1);
    }
  }

  private static DataSource createDataSource() {
    HikariDataSource ds = new HikariDataSource();
    ds.setJdbcUrl("jdbc:h2:~/Desktop/money-db;INIT=RUNSCRIPT FROM 'classpath:schema.sql'");
    ds.setUsername("sa");
    ds.setPassword("s3cr3tPassword");
    DataSource dataSource =
      ProxyDataSourceBuilder.create(ds)
        .logQueryToSysOut()
        .build();

    return dataSource;
  }
}
