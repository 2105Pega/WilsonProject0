package test;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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

  public Connection getConnection(String dbName) throws SQLException {
    try {
      PropertyConfigurator.configure("Revature/resources/log4jtest.properties");
    } catch (Exception e){e.printStackTrace(); System.out.println("Seems not found");;}
    String jdbcUrl = null, password, hostname, port;
    Properties prop = new Properties();
    try {
      Class.forName("org.postgresql.Driver");
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (dbName==null){dbName = prop.getProperty("DB_NAME");}

    try {
      FileInputStream stream = new FileInputStream("/Users/quese/Git/WilsonProject0/Revature/resources/properties/aws.properties");
      prop.load(stream);
      username = prop.getProperty("USER");
      password = prop.getProperty("PWD");
      hostname = prop.getProperty("RDS_HOSTNAME");
      port = prop.getProperty("RDS_PORT");
      jdbcUrl = "jdbc:postgresql://" + hostname + ":" +
        port + "/" + dbName + "?user=" + username + "&password=" + password;
    } catch (IOException io) {
      io.printStackTrace();
    }
    logger.info("Testing JDBC Connection: "+ jdbcUrl);assert jdbcUrl != null;
    return DriverManager.getConnection(jdbcUrl);
  }
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

  @Test
  public void TestConnection() throws ClassNotFoundException, SQLException, IOException {
    String db;
    Connection conn = getConnection(null);
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Which Database would you like to test? default= eqbank");
    db=reader.readLine();

    try {
      conn = getConnection(db);
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
  }

  /*
  @Test
  public void CreateUser() throws SQLException, IOException {

    try {
      PropertyConfigurator.configure("Revature/resources/log4jtest.properties");
    } catch (Exception e){e.printStackTrace(); System.out.println("Seems not found");;}
    String insert, update, sql;
    //User TesterX = new User("Testx", "xxxxx", "Power to the Users!");
    //logger.info("TestX Profile:\n"+TesterX.printUsers());
    Connection connection=getConnection("eqbank");
    insert=("INSERT INTO users (userid, username,NAME,password,account1, accounts, role) values ("+TesterX.getUserid()+",'"+TesterX.getUsername()+"','"+TesterX.getName()+"', '"+TesterX.getPassword()+"',"+null+","+ Arrays.toString(TesterX.getAccountz()) +", '"+TesterX.getRole()+"');");
    update="update users SET accounts="+ Arrays.toString(TesterX.getAccountz()) +" WHERE username='"+TesterX.getUsername()+"';";
    Statement stmt=null;

    //Run SQL Query
    try {
      connection= getConnection("eqbank");
      stmt=connection.createStatement();
      int result=stmt.executeUpdate(insert);
      System.out.println(result);
    } catch (Exception e){e.printStackTrace();}
    finally {
      assert stmt != null;
      stmt.close();
      connection.close();
    }
    System.out.println("Success!");
    assertNotNull(connection);
  }

   */
}


