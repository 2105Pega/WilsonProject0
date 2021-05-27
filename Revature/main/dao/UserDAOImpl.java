package main.dao;

import main.models.User;
import main.models.Users.Admin;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class UserDAOImpl implements UserDAO {
  static Logger logger = Logger.getLogger(UserDAOImpl.class);
  private String insert, update, selectall,url, sql;
  private int id, acc1, count=1;
  private String usernam, nam, passwor, role;
  private int[] accts;
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
    User Bob= new User("Bob", "pwd1237");
    User Jock=new Admin("Jock1","wellya","Admin Rogers!");
    users.add(Bob);
  }

  public static void main(String[] args) throws SQLException {
    UserDAOImpl dao = new UserDAOImpl();
    //User bob=dao.users.get(0);
    //dao.updateUser(bob);
    dao.selectAllUsers();
  }

  @Override
  public void insertUser(User user) throws SQLException {
    Statement stmt=null;
    try {
      PropertyConfigurator.configure("Revature/resources/properties/log4j.properties");
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
  public boolean updateUser(User user) throws SQLException {
    //set new value then send here
    Statement stmt=null;
    try {
      PropertyConfigurator.configure("Revature/resources/properties/log4j.properties");
    } catch (Exception e){e.printStackTrace(); System.out.println("Seems not found");;}
    Connection connection=getConnection("eqbank");
    update="update users WHERE username='"+user.getUsername()+"';";
    //Run SQL Query
    try {
      connection= getConnection("eqbank");
      stmt=connection.createStatement();
      int result=stmt.executeUpdate(update);
      System.out.println(result); } catch (Exception e){e.printStackTrace(); return false;}
    finally {
      assert stmt != null;
      stmt.close();
      connection.close(); }

    logger.info("New User updated successfully:\n"+user.printUsers());
    return true;
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
  public List<User> selectAllUsers() throws SQLException {
    Statement stmt=null;
    Connection connection=getConnection("eqbank");
    try {
      PropertyConfigurator.configure("Revature/resources/properties/log4j.properties");
    } catch (Exception e){e.printStackTrace(); System.out.println("Seems not found");;}
    //Run SQL Query
    try {
      connection=getConnection("eqbank");
      selectall="SELECT * FROM USERS";
      stmt=connection.createStatement();
      ResultSet rs=stmt.executeQuery(selectall);

      while(rs.next()){
        id = rs.getInt("userid");
        usernam = rs.getString("username");
        nam = rs.getString("name");
        passwor = rs.getString("password");
        acc1= rs.getInt("account1");
        accts = (int[]) rs.getObject("accounts");
        role=rs.getString("role");
        System.out.println("--------------"+count+"--------------");
        System.out.println( "ID = " + id );
        System.out.println( "USERNAME = " + usernam );
        System.out.println( "NAME = " + nam );
        System.out.println( "PASSWORD = " + passwor );
        System.out.println( "MAIN ACCOUNT = " +acc1 );
        System.out.println( "ACCOUNTS = " + Arrays.toString(accts));
        System.out.println( "ROLE = " + role );
        count++;
      } } catch (Exception e){e.printStackTrace();}
    finally {
      assert stmt != null;
      stmt.close();
      connection.close(); }
    logger.info("Success");
    count=0;
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
