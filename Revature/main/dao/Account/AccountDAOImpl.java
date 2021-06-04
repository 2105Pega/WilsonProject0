package main.dao.Account;

import main.dao.User.UserDAOImpl;
import main.models.Account;
import main.models.User;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class AccountDAOImpl implements AccountDAO {
  static Logger logger = Logger.getLogger(AccountDAOImpl.class);

  //FIELDS
  private double balance;
  private int id, acc1, count=1;
  private String status, account, name, passwor, usernam;
  private int[] accts;
  private Array userids;
  private String insert, update, selectall,url, sql;

  List<User> users;
  List<Account> accounts;
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

  public AccountDAOImpl(){
    users= new ArrayList<User>();
    //User Bob= new User("Bob", "pwd1237");
   // User Jock=new Admin("Jock1","wellya","Admin Rogers!");
   // users.add(Bob);
  }

  public static void main(String[] args) throws SQLException {
    AccountDAOImpl ao = new AccountDAOImpl();
    UserDAOImpl uo= new UserDAOImpl();

    //User First= uo.selectAllUsers().get(0);
    //First.setUserid(1069209503);
   // User bob=dao.users.get(0);
    //dao.updateUser(bob);
    //ao.createAccount(First);

  // ao.createAccount(First);
   ao.ApproveAccount(1649573132, true);
    //ao.selectAccountPending();
   // ao.depositAmount(1649573132,3.50);
  //  ao.withdrawAmount(1649573132, 5.07);
  }

  ///CREATE ACCOUNT
  @Override
  public void createAccount(User user) throws SQLException {
    Account account= new Account();
    account.addUsers(user.getUserid());
    account.setName("SS"+ String.valueOf(user.getUserid()).charAt(0));

    try { PropertyConfigurator.configure("Revature/resources/properties/log4j.properties"); }
    catch (Exception e){e.printStackTrace(); System.out.println("Seems not found");;} logger.setLevel(Level.WARN);
    Connection connection=getConnection("eqbank");
    Statement stmt=null;
    insert=("INSERT INTO accounts (accountid, status, account, userids, name, balance) values ("+account.getAccountNumber()+",'"+account.getStatus()+"','"+account.getAccount()+"', '"+ account.getUsersSQL() +"','"+account.getName()+"',"+ account.getBalance() +");");

    try { connection= getConnection("eqbank");
      stmt=connection.createStatement();
      int result=stmt.executeUpdate(insert);
      System.out.println(result); }
    catch (Exception e){e.printStackTrace();}
    finally {
      assert stmt != null;
      stmt.close();
      connection.close(); }

    //logger.info("New User added successfully:\n"+user.printUsers());
  }

  //READ DB
  @Override  // GET BY ACCOUNT
  public List<Account> selectAccountbyid(int id) throws SQLException {
    try { PropertyConfigurator.configure("Revature/resources/properties/log4j.properties"); }
    catch (Exception e){e.printStackTrace(); System.out.println("Seems not found");;} logger.setLevel(Level.WARN);
    List<Account> instList=new ArrayList<Account>();
    Connection connection=getConnection("eqbank");
    Statement stmt=null;

    try{
      sql="SELECT * FROM accounts WHERE accountid='"+id+"';";
      stmt=connection.createStatement();
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
  @Override // GET BY USER/ UID
  public List<Account> selectAccountuid(int userid) throws SQLException {
    try { PropertyConfigurator.configure("Revature/resources/properties/log4j.properties"); }
    catch (Exception e){e.printStackTrace(); System.out.println("Seems not found");;} logger.setLevel(Level.WARN);

    List<Account> instList=new ArrayList<Account>();
    Connection connection=getConnection("eqbank");
    Statement stmt=null;
    //System.out.println(userid());
    try{
      sql="SELECT * FROM accounts WHERE userids IN ('{"+userid+"}');";
      stmt=connection.createStatement();
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
  @Override // PENDING LIST
  public List<Account> selectAccountPending() throws SQLException {
    try { PropertyConfigurator.configure("Revature/resources/properties/log4j.properties"); }
    catch (Exception e){e.printStackTrace(); System.out.println("Seems not found");;} logger.setLevel(Level.WARN);
    List<Account> instList=new ArrayList<Account>();
    Connection connection=getConnection("eqbank");
    Statement stmt=null;

    try{
      sql="SELECT * FROM accounts WHERE status='PENDING';";
      stmt=connection.createStatement();
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
  @Override // ALL LIST
  public List<Account> selectAllAccounts() throws SQLException {
    List<Account> instList=new ArrayList<Account>();
    Statement stmt=null;
    Connection connection=getConnection("eqbank");
    try {
      PropertyConfigurator.configure("Revature/resources/properties/log4j.properties");
    } catch (Exception e){e.printStackTrace(); System.out.println("Seems not found");;}
    //Run SQL Query
    try {
      connection=getConnection("eqbank");
      selectall="SELECT * FROM accounts";
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

  //UPDATE ACCOUNTS
  @Override // APPROVE ACCOUNT
  public List<Account> ApproveAccount(int acc, boolean status) throws SQLException {
    String message="'PENDING'";
    switch (status+"..."){
      case "true...":
        message="'MEMBER'";
        break;
      case "false...":
        message="'CLOSED'";
        break;
      default:
        return null; }

    try { PropertyConfigurator.configure("Revature/resources/properties/log4j.properties");
    } catch (Exception e){e.printStackTrace(); System.out.println("Seems not found");;} logger.setLevel(Level.WARN);
    List<Account> instList=new ArrayList<Account>();
    Connection connection=getConnection("eqbank");
    Statement stmt=null;

    try {
      update="update accounts SET status="+message+ "WHERE accountid="+acc+";";
      instList=null;
      connection= getConnection("eqbank");
      stmt=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
      connection.setAutoCommit(false);
      stmt.addBatch(update);
      stmt.executeBatch();
      connection.commit();
      instList=selectAllAccounts(); }
    catch (Exception e){e.printStackTrace();}
    finally {
      assert stmt != null;
      stmt.close();
      connection.close(); }

    // logger.info("New User updated successfully:\n"+user.printUsers());
    return instList;
  }
  @Override //ADD NEW USER TO ACCOUNT
  public List<Account> AddUser(int user, int account) throws SQLException {
    try { PropertyConfigurator.configure("Revature/resources/properties/log4j.properties");
    } catch (Exception e){e.printStackTrace(); System.out.println("Seems not found");;} logger.setLevel(Level.WARN);
    List<Account> instList=new ArrayList<Account>();
    AccountDAOImpl ao = new AccountDAOImpl();
    Account newaccount=ao.selectAccountbyid(account).get(0);

    UserDAOImpl uo = new UserDAOImpl();
    User newuser=uo.selectUser(user).get(0);
    newaccount.addUsers(newuser.getUserid());

    Connection connection=getConnection("eqbank");
    Statement stmt=null;
    try {
      update="update accounts SET userids='"+ newaccount.getUsersSQL()+"' WHERE accountid="+account+";";
      instList=null;
      connection= getConnection("eqbank");
      stmt=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
      connection.setAutoCommit(false);
      stmt.addBatch(update);
      stmt.executeBatch();
      connection.commit();
      instList=selectAllAccounts(); }
    catch (Exception e){e.printStackTrace();}
    finally {
      assert stmt != null;
      stmt.close();
      connection.close(); }

    // logger.info("New User updated successfully:\n"+user.printUsers());
    return instList;
  }

  @Override
  public boolean depositAmount(int account, double balance) throws SQLException {
    Account reciever=selectAccountbyid(account).get(0);
    Scanner scanner= new Scanner(System.in);
    Connection connection=getConnection("eqbank");
    Statement stmt=null;

    while (true){
      System.out.println("How much to deposit?");
    if (balance==0){
      try { balance=scanner.nextDouble(); }
        catch (Exception e){ System.out.println("Amount is not cash value,please try again."); } }
      else if(balance<0){
      System.out.println("Anything higher than Zero");
    }
      else { reciever.setBalance(balance+reciever.getBalance());

      try {
        update="update accounts SET balance="+reciever.getBalance()+" WHERE accountid="+reciever.getAccountNumber()+";";
        connection= getConnection("eqbank");
        stmt=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        connection.setAutoCommit(false);
        stmt.addBatch(update);
        stmt.executeBatch();
        connection.commit();
        selectAccountbyid(reciever.getAccountNumber());}
      catch (Exception e){e.printStackTrace();}
      finally {
        assert stmt != null;
        stmt.close();
        connection.close(); }
      return true;} }
    }
  @Override
  public boolean withdrawAmount(int account, double balance) throws SQLException {
    Account reciever=selectAccountbyid(account).get(0);
    Scanner scanner= new Scanner(System.in);
    Connection connection=getConnection("eqbank");
    Statement stmt=null;
    System.out.println("How much to Withdraw?");
    boolean available=!(reciever.getBalance() >= balance);
    while (true) {
      if (balance == 0 && available) { System.out.println("How much?");
        try { balance = scanner.nextDouble();
        } catch (Exception e) { logger.error(e.toString());
          System.out.println("Amount is not cash value. Try again.");
          }
      } else if (balance < 0 && available) {
        System.out.println("Anything higher than Zero.");
        try { balance = scanner.nextDouble();
        } catch (Exception e) { logger.error(e.toString());
          System.out.println("Amount is not cash value. Try again."); }
      } else if(balance>reciever.getBalance()){
        System.out.println("Sorry Boss, only "+ reciever.getBalance() + " available. Try again.");
        try { balance = scanner.nextDouble(); }
        catch (Exception e) {
          System.out.println("Amount is not cash value. Try again."); }
      }
      else if (reciever.getBalance() >= balance) { reciever.setBalance(reciever.getBalance() - balance);
        try { update="update accounts SET balance="+reciever.getBalance()+" WHERE accountid="+reciever.getAccountNumber()+";";
          connection= getConnection("eqbank");
          stmt=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
          connection.setAutoCommit(false);
          stmt.addBatch(update);
          stmt.executeBatch();
          connection.commit();
          selectAccountbyid(reciever.getAccountNumber());}
        catch (Exception e){e.printStackTrace();}
        finally {
          assert stmt != null;
          stmt.close();
          connection.close(); }
        return true;
      } else {
        return false; } }
  }


  @Override
  public boolean transferAmount(Account account) throws SQLException {
    return false;
  }

  //DELETE
  @Override
  public List<Account> deleteAccount(int id) throws SQLException {
    return null;
  }

  @Override
  public List<Account> iterateDB(ResultSet rs) throws SQLException {
    Account instance = new Account();
    List<Account> instList=new ArrayList<Account>();

    while(rs.next()){
      id = rs.getInt("accountid");
      status = rs.getString("status");
      account = rs.getString("account");
      userids = rs.getArray("userids");
      name = rs.getString("name");
      balance= rs.getDouble("balance");
      Integer[] b= (Integer[])userids.getArray();
      System.out.println(b);
      //instance.addUsers(b);
      instance.setStatus(status);
      instance.setName(name);
      instance.setAccount(account);
      instance.setBalance(balance);
      instance.setAccountNumber(id);
      instList.add(instance);
      System.out.println("--------------"+(count+1)+"--------------");
      System.out.println( "ID = " + id );
      System.out.println( "STATUS = " + status );
      System.out.println( "ACCOUNT = " + account );
      System.out.println( "NAME = " + name );
      System.out.println( "USERS = " + userids);
      System.out.println( "BALANCE = " + balance );
      count++;}

    return instList;
  }
}
