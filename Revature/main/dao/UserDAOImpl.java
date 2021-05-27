package main.dao;

import main.models.User;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class UserDAOImpl implements UserDAO {
  static Logger logger = Logger.getLogger(UserDAOImpl.class);
  private String url,usernam, pwd;
  private String insert, update, sql;
  List<User> users;
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
      usernam = prop.getProperty("USER");
      password = prop.getProperty("PWD");
      hostname = prop.getProperty("RDS_HOSTNAME");
      port = prop.getProperty("RDS_PORT");
      jdbcUrl = "jdbc:postgresql://" + hostname + ":" +
        port + "/" + dbName + "?user=" + usernam + "&password=" + password;
    } catch (IOException io) {
      io.printStackTrace();
    }
    logger.info("Testing JDBC Connection: "+ jdbcUrl);assert jdbcUrl != null;
    return DriverManager.getConnection(jdbcUrl);
  }

  public UserDAOImpl(){
    users= new ArrayList<User>();
    User Bob= new User("Bob", "pwd1234");
    users.add(Bob);
  }

  public static void main(String[] args) throws SQLException {
    UserDAOImpl dao = new UserDAOImpl();
    User bob=dao.users.get(0);
    dao.insertUser(bob);
  }
  @Override
  public void insertUser(User user) throws SQLException {
    Statement stmt=null;
    try {
      PropertyConfigurator.configure("Revature/resources/log4jtest.properties");
    } catch (Exception e){e.printStackTrace(); System.out.println("Seems not found");;}
    Connection connection=getConnection("eqbank");
    insert=("INSERT INTO users (userid, username,NAME,password,account1, accounts, role) values ("+user.getUserid()+",'"+user.getUsername()+"','"+user.getName()+"', '"+user.getPassword()+"',"+null+","+ Arrays.toString(user.getAccountz()) +", '"+user.getRole()+"');");
    //Run SQL Query
    try {
      connection= getConnection("eqbank");
      stmt=connection.createStatement();
      int result=stmt.executeUpdate(insert);
      System.out.println(result); } catch (Exception e){e.printStackTrace();}
    finally {
      assert stmt != null;
      stmt.close();
      connection.close(); }

    logger.info("New User added successfully:\n"+user.printUsers());
  }

  @Override
  public boolean updateUser(User user) {
    update="update users SET accounts="+ Arrays.toString(user.getAccountz()) +" WHERE username='"+user.getUsername()+"';";

    return false;
  }

  @Override
  public User selectUser(int id) {
    return null;
  }

  @Override
  public User selectUser(String username) {
    return null;
  }

  @Override
  public List<User> selectAllUsers() {
    return null;
  }

  @Override
  public boolean deleteUser(int id) {
    return false;
  }

  @Override
  public boolean deleteUser(String username) {
    return false;
  }
}
