package main.models;

import main.models.sub.Admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.*;

enum Status {
  PENDING, APPROVED, REJECTED;
}

public class Account implements Serializable {
  private String accountName;
  private  final String accountNumber;
  private final HashMap<String, User> users = new HashMap<>(); //list of users who can access
  private final ArrayList<Transaction> transactions = new ArrayList<>();
  private double balance;
  private Status status;
  private static HashMap<String, Account> accounts= new HashMap<>();
  private static int counter = 0;

  //Default values Constructor
  public Account() {
    super();
    UUID uuid = UUID.randomUUID();
    this.accountNumber = uuid.toString();
    this.balance = 0.00;
    System.out.println("NEW ACCOUNT: " + this.accountNumber);
  }
//Main Constructor
  public Account(User user, double balance) throws IOException {
    super();
    this.status=Status.PENDING;
    UUID uuid = UUID.randomUUID();
    this.accountNumber = uuid.toString();
    ArrayList<String> names = new ArrayList<String>();
    System.out.println("Is this a joint account? Yes or No?");
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    // Reading data using readLine
    String name = reader.readLine();
    if (name.equals("Yes") || name.equals("yes") || name.equals("Y") || name.equals("y")) {
      System.out.println("We ask that you leave usernames of co-owners for further evaluation." +
        "\n Please space between each user, for example: Username1 [SPACE] Username2 [SPACE] Username3");
      names.addAll(Arrays.asList(name.split(" ")));
    } else {
      user.setBalance(balance);
      names.add(user.getUsername());
    }
    for (String person : names) {
      if (!this.users.containsKey(person))
      {this.users.put(person, null);}
    }
    this.users.replace(user.getUsername(), user, null);
    this.accountName = "Account " + counter;
    this.balance=this.balance+balance;
    Transaction transaction= new Transaction("Account: "+ this.accountNumber+ " created.");
    this.transactions.add(transaction);
    counter++;
  }
//Getters
  public String getAccountName() {
    return accountName;
  }

  public HashMap<String, User> getUsers() {
    return users;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public String getBalance() {
    return "Current balance: "+ balance;
  }

  public void setStatus(Admin status) {
    System.out.println("Current Status: "+ this.status+"Change? Y?");

  }

  //Actions
  public Transaction CreateAccount(User user, double balance) throws Exception {
    System.out.println(this.users);
    Account account= new Account(user,balance);
    return new Transaction("Created new account: "+ account.getAccountNumber()+":\t"+account.toString());
  }

  public Transaction RemoveUser(String requestor, String name) throws IOException {
    Transaction transaction;
    if (this.users.containsKey("Boss "+ requestor)){
      System.out.println("Who would you like to remove from list?");
      while(true){
      int x=1;
      for (Map.Entry<String, User> set : this.users.entrySet()) {
        System.out.println("User "+x+":\t"+set.getKey()); x++; }
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      // Reading data using readLine
       name = reader.readLine();
      if (this.users.containsKey(name)) {
        this.users.remove(name);
        transaction= new Transaction(name+ " removed from Joint List by "+ requestor);
        x=1;
        for (Map.Entry<String, User> set : this.users.entrySet()) {
          System.out.println("User "+x+":\t"+set.getKey()); x++; }
        break;
      }else {
        System.out.println("Please retry, not on list:");
      } } } else {
      System.out.println("You cannot preform action.");
      transaction= new Transaction(requestor + " attempted removal of user.");}
    System.out.println(transaction);
    this.transactions.add(transaction);
    return transaction;}

public Transaction Withdrew(User user, double amount){
  Transaction transaction;
  if (this.users.containsKey(user.getUsername()) && amount<this.balance){
    user.setBalance(this.balance-amount);
    this.balance=this.balance-amount;
    transaction= new Transaction(user.getUsername()+ " Withdraw "+ amount+".\nNew Balance: "+ this.balance);
    user.setTransactions(transaction);
    this.users.replace(user.getUsername(), user);
  } else if (!this.users.containsKey(user.getUsername())){
    System.out.println("You dont have access.");
    transaction= new Transaction( "Failed attempt by "+ user.getUsername());
  }else if (amount>this.balance){
    System.out.println("You dont have enough.");
    transaction= new Transaction(user.getUsername()+ " Insufficient funds. Current balance: "+ this.balance);
  } else {transaction=null;}
  this.transactions.add(transaction);
  return transaction;}

  public Transaction Deposit(User user, double amount){
    Transaction transaction;
    if (this.users.containsKey(user.getUsername())){
      user.setBalance(this.balance+amount);
      this.balance=this.balance+amount;
      transaction= new Transaction(user.getUsername()+ " Deposited "+ amount+".\nNew Balance: "+ this.balance);
      user.setTransactions(transaction);
      this.users.replace(user.getUsername(), user);
          } else if (!this.users.containsKey(user.getUsername())){
      System.out.println("You dont have access.");
      transaction= new Transaction( "Failed attempt by "+ user.getUsername());
    } else {transaction=null;}
    this.transactions.add(transaction);
    return transaction;}

  public Transaction Transfer(User user, double amount){
    Transaction transaction;
    transaction= new Transaction(user.getUsername()+ " Deposited "+ amount+".\nNew Balance: "+ this.balance);

    this.transactions.add(transaction);
    return transaction;
  }

  public HashMap<String, Account> getAccounts() {
    return accounts;
  }
  @Override
  public String toString() {
    return "Account{" +
      "accountName='" + accountName + '\'' +
      ", users=" + users +
      ", transactions=" + transactions +
      ", accountNumber='" + accountNumber + '\'' +
      ", balance=" + balance +
      '}';
  }
}
  


