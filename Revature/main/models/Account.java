package main.models;

import main.View.Menu;
import main.models.Users.Admin;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Account implements Serializable {
  public static int generateUniqueId() {
    UUID idOne = UUID.randomUUID();
    String str=""+idOne;
    int uid=str.hashCode();
    String filterStr=""+uid;
    str=filterStr.replaceAll("-", "");
    return Integer.parseInt(str);
  }
  public static int[] addtoArray(int n, int arr[], int x)
  {
    int i;
    int[] newarr = new int[n + 1];

    if (arr==null){
      newarr=new int[1];
    } else {
      // insert the elements from
      // the old array into the new array
      // insert all elements till n
      // then insert x at n+1
      for (i = 0; i < n; i++)
        newarr[i] = arr[i];

      newarr[n] = x;
    }
    return newarr;
  }

  static Logger logger = Logger.getLogger(Account.class);
  private String accountName;
  private Integer accountNumber=0;
  private int[] accountz;
  private  HashMap<String, User> users = new HashMap<>(); //list of users who can access
  private ArrayList<Transaction> transactions= new ArrayList<>();
  private double balance;
  private String status;
  private static HashMap<Integer, Account> accounts = new HashMap<>();
  private static int counter = 0;

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
    String username;
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
  //Default values Constructor
  public Account() {
    //this.accountNumber=generateUniqueId();
    //this.status = "PENDING";
    //this.balance = 0.00;
    //addtoArray(accounts.size(),accountz, this.getAccountNumber());
    //System.out.println("NEW ACCOUNT: " + this.accountNumber);
  }

  //Main Constructor
  public Account(User user) throws Exception {
    super();
    Menu mini= new Menu();
    String name, input;
    double bbalance=0;
    ArrayList<String> names = new ArrayList<String>();
    System.out.println("Is this a joint account? Y or N?");
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    // Reading data using readLine
    name = reader.readLine();

    if (name.equals("Yes") || name.equals("yes") || name.equals("YES") || name.equals("Y") || name.equals("y")) {
      System.out.println("We ask that you leave usernames of co-owners for further evaluation." +
        "\n Please space between each user, for example: Username1 [SPACE] Username2 [SPACE] Username3");
      names.addAll(Arrays.asList(name.split(" "))); }

    this.users.put(user.getUsername(), user);
    System.out.println(this.users.toString());
    for (String person : names) {
      if (this.users.containsKey(person)) {
        this.users.put(person, this.users.get(person)); } else {this.users.put(person+"?", null);} }

    System.out.println("Would this be a checking or savings account?");
    name=reader.readLine();
    String Message=("What will be the deposit?");
    while(true){
      System.out.println(Message);
      input=reader.readLine();
      try {
        bbalance=Double.parseDouble(input.trim());
        break;
      } catch (Exception e){
        Message="Not a real amount, please try again.";
      } if (bbalance!=0){break;} else {Message="Please try again";}}

    for (Map.Entry<Integer, Account> set :
      accounts.entrySet()) {
      if (set.getValue().accountName.equals(name)){ name=name+ counter; }}
    this.accountNumber=generateUniqueId();
    this.status = "PENDING";
    this.accountName = name;
    this.balance = this.balance + bbalance;
    Transaction transaction = new Transaction("Account: " + this.accountNumber + " created.");
    this.transactions.add(transaction);
    accounts.put(this.accountNumber, this);
    user.setAccountsh(this.accountNumber, this);
    user.addaccount(this);
    counter++;
    addtoArray(accounts.size(),accountz, this.getAccountNumber());
    System.out.println("\nNew Account alert!\n"+ this.toString());

    Connection connection=getConnection("eqbank");
    String insert=("INSERT INTO users (userid, username,NAME,password,account1, accounts, role) values ("+user.getUserid()+",'"+user.getUsername()+"','"+user.getName()+"', '"+user.getPassword()+"',"+null+","+ user.getAccountz().toString() +", '"+user.getRole()+"');");
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
    mini.DisplayOptions(user,2);
  }
  public Transaction Withdrew(User user) throws IOException {
    System.out.println("EXTRA?");
    String input;
    double bbalance=0;
    int counter=1;
    Account accounts1=user.getAccounts();
    System.out.println("\t\t\tAccount\nOption\t\tName\t\tNumber\t\tBalance");
    for (Map.Entry<Integer, Account> set : user.getAccountsh().entrySet()) {
      System.out.printf("%2d %12s %10d %12f",counter,set.getValue().accountName,set.getValue().accountNumber, set.getValue().balance);
      counter++; }
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int choice=0;
    while (true) {
      String option = reader.readLine();
      try {
        choice = Integer.parseInt(option.trim());
      } catch (Exception e) {
        System.out.println("Choose an available option.");}
      if (choice<=counter){break;} }

    String Message="\nCurrently "+ accounts1.getBalance() +" Available.\nHow much  Withdraw?";
    while(true){
      System.out.println(Message);
      input=reader.readLine();
      try {
        bbalance=Double.parseDouble(input.trim());
      } catch (Exception e){
        Message="Not a real amount, please try again.";
      } if (bbalance<accounts1.getBalance()){break;} else {Message="Insufficient funds. Please try again";}}
    double newbalance=accounts1.getBalance()-bbalance;
    accounts1.setBalance(newbalance);
    Transaction transaction = new Transaction(user.getUsername() + " Withdraw " + bbalance + ".\nNew Balance: "+accounts1.getBalance());
    user.setTransactions(transaction);
    this.transactions.add(transaction);
    return transaction;
  }
  //Getters
  public String getAccountName() {
    return accountName;
  }

  public HashMap<String, User> getUsers() {
    return users;
  }

  public int getAccountNumber() {
    return accountNumber;
  }

  public double getBalance() {
    return balance;
  }

  public void setStatus(Admin status) {
    System.out.println("Current Status: " + this.status + "Change? Y/N?");

  }

  //Actions
  public void CreateAccount(User user) throws Exception {
    System.out.println(this.users);
    Account account = new Account(user);
    accounts.put(account.accountNumber, account);
    this.transactions.add(new Transaction("Created new account " + account.getAccountNumber() + "was created by \t" + user.getUsername()));
    System.out.println("New List of Users:\n" + this.printAccounts());
  }

  public Transaction RemoveUser(String requestor, String name) throws IOException {
    Transaction transaction;
    if (this.users.containsKey("Boss " + requestor)) {
      System.out.println("Who would you like to remove from list?");
      while (true) {
        int x = 1;
        for (Map.Entry<String, User> set : this.users.entrySet()) {
          System.out.println("User " + x + ":\t" + set.getKey());
          x++;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // Reading data using readLine
        name = reader.readLine();
        if (this.users.containsKey(name)) {
          this.users.remove(name);
          transaction = new Transaction(name + " removed from Joint List by " + requestor);
          x = 1;
          for (Map.Entry<String, User> set : this.users.entrySet()) {
            System.out.println("User " + x + ":\t" + set.getKey());
            x++;
          }
          break;
        } else {
          System.out.println("Please retry, not on list:");
        }
      }
    } else {
      System.out.println("You cannot preform action.");
      transaction = new Transaction(requestor + " attempted removal of user.");
    }
    System.out.println(transaction);
    this.transactions.add(transaction);
    return transaction;
  }



  public void setBalance(double balance) {
    this.balance = balance;
  }

  public Transaction Deposit(User user, double amount) {
    Transaction transaction;
    if (this.users.containsKey(user.getUsername())) {
      this.setBalance(this.balance + amount);
      this.balance = this.balance + amount;
      transaction = new Transaction(user.getUsername() + " Deposited " + amount + ".\nNew Balance: " + this.balance);
      user.setTransactions(transaction);
      this.users.replace(user.getUsername(), user);
    } else if (!this.users.containsKey(user.getUsername())) {
      System.out.println("You dont have access.");
      transaction = new Transaction("Failed attempt by " + user.getUsername());
    } else {
      transaction = null;
    }
    this.transactions.add(transaction);
    return transaction;
  }

  public Transaction Transfer(User user, double amount) {
    Transaction transaction;
    transaction = new Transaction(user.getUsername() + " Deposited " + amount + ".\nNew Balance: " + this.balance);

    this.transactions.add(transaction);
    return transaction;
  }

  public HashMap<Integer, Account> getAccounts() {
      //logger.info("Printing accounts as Hashmap\n "+ this.printAccounts());
    return accounts;
  }
  public int[] getAccountz() {
    return accountz;
  }

  @Override
  public String toString() {
    return "Account{" +
      "accountName='" + accountName + '\'' +
      ", accountNumber=" + accountNumber +
      ", users=" + users +
      ", transactions=" + transactions +
      ", balance=" + balance +
      ", status='" + status + '\'' +
      '}';
  }

  public StringBuilder printAccounts() {
    StringBuilder buffer = new StringBuilder("---------------------------------\n");
    for (Map.Entry<Integer, Account> set :
      accounts.entrySet()) {
      // Printing all elemnts of a Map
      buffer.append(set.getKey()).append(" = ").append(set.getValue()).append("\n");
    }
    return buffer;
  }
}
  


