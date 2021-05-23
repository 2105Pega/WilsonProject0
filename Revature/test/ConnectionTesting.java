package test;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.Assert.assertTrue;

public class ConnectionTesting {
  static Logger logger = Logger.getLogger(ConnectionTesting.class);
  private String url;
  private String username;
  private String pwd;

  public String[] getProps() {
    Properties prop = new Properties();
    try {
      url = prop.getProperty("URL");
      username = prop.getProperty("USER");
      pwd = prop.getProperty("PWD");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new String[]{url, username, pwd};
  }

  //GETTERS
  public String getUrl() {
    return url;
  }

  public String getUsername() {
    return username;
  }

  public String getPwd() {
    return pwd;
  }

  public Connection getConnection() throws SQLException {
    String jdbcUrl = null, password, dbName, hostname, port;
    Properties prop = new Properties();
    try {
      Class.forName("org.postgresql.Driver");
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      FileInputStream stream = new FileInputStream("/Users/quese/Git/WilsonProject0/Revature/resources/aws.properties");
      prop.load(stream);
      username = prop.getProperty("USER");
      password = prop.getProperty("PWD");
      dbName = prop.getProperty("DB_NAME");
      hostname = prop.getProperty("RDS_HOSTNAME");
      port = prop.getProperty("RDS_PORT");
      jdbcUrl = "jdbc:postgresql://" + hostname + ":" +
        port + "/" + dbName + "?user=" + username + "&password=" + password;
      logger.info("\nProp.URL=" + url + "\nProp.PWD=" + pwd);
    } catch (IOException io) {
      io.printStackTrace();
    }
    return DriverManager.getConnection(jdbcUrl);
  }

  @Test
  public void TestConnection() throws ClassNotFoundException, SQLException {
    Connection conn = getConnection();
    System.out.println(this.url);
    try {
      conn = getConnection();
      if (conn != null) {
        System.out.println("Connection Success!");
      } else {
        System.out.println("Failed Connection\n");
      }
    } catch (SQLException e) {
      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
    } catch (Exception e2) {
      System.out.println("Failed Connection\n");
      e2.printStackTrace();
    }
    assertTrue(conn != null);
  }}


