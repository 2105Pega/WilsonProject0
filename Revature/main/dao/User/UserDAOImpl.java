package main.dao.User;

import main.models.User;
import org.apache.log4j.Level;
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
  private static List<User> users=new ArrayList<User>();
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

  public UserDAOImpl(){ }

  public static void main(String[] args) throws SQLException {
    UserDAOImpl dao = new UserDAOImpl();
    //System.out.println( dao.selectUser("Bob"));
    System.out.println(dao.selectAllUsers());
  }

  ///CREATE USERS
  @Override
  public void insertUser(User user) throws SQLException {
    try {
      PropertyConfigurator.configure("Revature/resources/properties/log4j.properties");
    } catch (Exception e){e.printStackTrace(); System.out.println("Seems not found");;}
    logger.setLevel(Level.WARN);
    Connection connection=getConnection("eqbank");
    Statement stmt=null;

    try {
      insert=("INSERT INTO users (userid, username,NAME,password,account1, accounts, role) values ("+user.getUserid()
        +",'"+user.getUsername() +"','"+user.getName()+"', '"+user.getPassword()+"',"+ Arrays.toString(user.getAccounts()) +","+
        Arrays.toString(user.getAccounts()) +", '"+user.getRole()+"');");

      connection= getConnection("eqbank");
      stmt=connection.createStatement();
      int result=stmt.executeUpdate(insert);System.out.println(result); }
    catch (Exception e){e.printStackTrace();}
    finally {
      assert stmt != null;
      stmt.close();connection.close(); }
    //logger.info("New User added successfully:\n"+user.printUsers());
  }

  ///READ DATABASE
  @Override
  public List<User> selectUser(String user) throws SQLException {
    try { PropertyConfigurator.configure("Revature/resources/properties/log4j.properties"); }
    catch (Exception e){e.printStackTrace(); System.out.println("Seems not found");;} logger.setLevel(Level.WARN);

    List<User> instList=new ArrayList<User>();
    Connection connection=getConnection("eqbank");
    Statement stmt=null;
    try{
      sql="SELECT * FROM USERS WHERE username='"+user+"';";
      stmt=connection.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
      ResultSet rs=stmt.executeQuery(sql);
      instList=null;
      instList=iterateDB(rs); }
    catch (Exception e){e.printStackTrace();}
    finally {
      assert stmt != null;
      stmt.close();
      connection.close(); }
    logger.info("Success");
    count=0;
    return instList;
  }

  @Override
  public List<User> selectUser(int user) throws SQLException {
    try { PropertyConfigurator.configure("Revature/resources/properties/log4j.properties"); }
    catch (Exception e){e.printStackTrace(); System.out.println("Seems not found");;} logger.setLevel(Level.WARN);

    List<User> instList=new ArrayList<User>();
    Connection connection=getConnection("eqbank");
    Statement stmt=null;
    try{
      sql="SELECT * FROM USERS WHERE userid="+user+";";
      stmt=connection.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
      ResultSet rs=stmt.executeQuery(sql);
      instList=null;
      instList=iterateDB(rs); }
    catch (Exception e){e.printStackTrace();}
    finally {
      assert stmt != null;
      stmt.close();
      connection.close(); }
    logger.info("Success");
    count=0;
    return instList;
  }
  @Override
  public List<User> selectAllUsers() throws SQLException {
    List<User> instList=new ArrayList<User>();
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
      instList=null;
      instList=iterateDB(rs); }
    catch (Exception e){e.printStackTrace();}
    finally {
      assert stmt != null;
      stmt.close();
      connection.close(); }
    logger.info("Success");
    count=0;
    return instList;
  }

  ///UPDATE DATABASE
  @Override
  public List<User> updateUser(User user) throws SQLException {
    try { PropertyConfigurator.configure("Revature/resources/properties/log4j.properties");
    } catch (Exception e){e.printStackTrace(); System.out.println("Seems not found");;} logger.setLevel(Level.WARN);
    List<User> instList=new ArrayList<User>();
    Connection connection=getConnection("eqbank");
    Statement stmt=null;

    try {
      update="update users SET userid="+ user.getUserid()+", username='"+user.getUsername()+"', name='"+user.getName()+"', password='"+user.getPassword()+"',account1="+null+", accounts="+ Arrays.toString(user.getAccounts()) +",role= '"+user.getRole()+"' WHERE userid="+user.getUserid()+";";
      instList=null;
      connection= getConnection("eqbank");
      stmt=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
      connection.setAutoCommit(false);
      stmt.addBatch(update);
      stmt.executeBatch();
      connection.commit();
      instList=selectAllUsers(); }
    catch (Exception e){e.printStackTrace();}
    finally {
      assert stmt != null;
      stmt.close();
      connection.close(); }

   // logger.info("New User updated successfully:\n"+user.printUsers());
    return instList;
  }

 ///DELETE USER FROM DATABASE
 @Override
 public List<User> deleteUser(int id) throws SQLException {
   try { PropertyConfigurator.configure("Revature/resources/properties/log4j.properties");
   } catch (Exception e){e.printStackTrace(); System.out.println("Seems not found");;}logger.setLevel(Level.WARN);

   List<User> instList=new ArrayList<User>();
   Connection connection=getConnection("eqbank");
   Statement stmt=null;
   try {
     update="DELETE FROM users WHERE userid="+id+";";
     selectall="SELECT * FROM USERS;";
     instList=null;
     connection= getConnection("eqbank");
     stmt=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
     connection.setAutoCommit(false);
     stmt.addBatch(update);
     stmt.executeBatch();
     connection.commit();
     instList=selectAllUsers();
   } catch (Exception e){e.printStackTrace();}
   finally {
     assert stmt != null;
     stmt.close();
     connection.close(); }
     //logger.info("New User updated successfully:\n"+use.printUsers());
   return instList;
 }

 @Override
 public List<User> iterateDB(ResultSet rs) throws SQLException {
    User instance = new User();
    List<User> instList=new ArrayList<User>();

    while(rs.next()){
      id = rs.getInt("userid");
      usernam = rs.getString("username");
      nam = rs.getString("name");
      passwor = rs.getString("password");
      acc1= rs.getInt("account1");
      accts = (int[]) rs.getObject("accounts");
      role=rs.getString("role");
      instance.setUserid(id);
      instance.setUsername(usernam);
      instance.setName(nam);
      instance.setPassword(passwor);
      instance.setAccounts(accts);
      instance.setRole(role);
      instList.add(instance);
      System.out.println("--------------"+count+"--------------");
      System.out.println( "ID = " + id );
      System.out.println( "USERNAME = " + usernam );
      System.out.println( "NAME = " + nam );
      System.out.println( "PASSWORD = " + passwor );
      System.out.println( "MAIN ACCOUNT = " +acc1 );
      System.out.println( "ACCOUNTS = " + Arrays.toString(accts));
      System.out.println( "ROLE = " + role );
      count++;}

    return instList;
  }
  protected void CheckTable(ResultSet rs) throws SQLException {
    ResultSetMetaData metadata = rs.getMetaData();
    int columnCount = metadata.getColumnCount();
    for (int i = 1; i <= columnCount; i++) {
      System.out.println(metadata.getColumnName(i) + ", ");
    }
    System.out.println();
    while (rs.next()) {
      String row = "";
      for (int i = 1; i <= columnCount; i++) {
        row += rs.getString(i) + ", ";
      }
      System.out.println();
      System.out.println(row);

    }
  }
  protected void garage(){
    //User Bob= new User("Bob1", "pwd1234");
    // User Jock=new Admin("Jock1","wellya","Admin Rogers!");
    // User Bob= new User("Bob6", "pwd1234");
    //users.add(Bob);
    //dao.insertUser(Bob);
    //dao.selectAllUsers();
    //users.get(0).setName("OG Bobby");
    //dao.updateUser(users.get(0));
    //dao.selectAllUsers();
    //users.add(Bob);
  }
}

