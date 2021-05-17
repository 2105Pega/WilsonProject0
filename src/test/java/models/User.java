package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class User {

  private String username;
  private String name;
  private String password;
  private int role;
  private HashMap<String, Account> accounts= new HashMap<>(); //List of accounts
  private ArrayList<Transaction> transactions= new ArrayList<>();
  private String[] options;
  private double balance;

  public User(){
    super();
  }

  public User(String username, String password, String name){
    this.username=username;
    this.password = password;
    this.setRole(3); //Default 3 for Customer, 2 for Employee, and 1 for Admin
    this.name=name;
  }
  public User(String username, String password) {

    this.username = username;
    this.password = password;
    this.setRole(3); //Default 3 for Customer, 2 for Employee, and 1 for Admin
  }

  public void pwdcheck(String pwd){

  }

  public String getUsername() {
    return username;
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }

  public int getRole() {
    return role;
  }
  public void setRole(int role) {
    this.role = role;
    switch (role){
      case 3:
        this.options=new String[]{"Withdraw", "Deposit", "Transfer to another Member"};
        break;
      case 2:
        this.options=new String[]{"View Customer Details", " View Pending Applications"};
        break;
      case 1:
        this.options=new String[]{"View Customer Details","Edit Customer Details", "View Pending Applications", "Edit Account","Cancel Account"};
        break;
      default:
        System.out.println("Wooooooah");
        break;
    }
  }

  public Account getAccounts() throws IOException {
    int x=1;
    ArrayList<String> list= new ArrayList<>();
    System.out.println("Which one?");
    for (Map.Entry<String, Account> set : this.accounts.entrySet()) {
      System.out.println("Account "+x+":\t"+set.getKey()); x++; list.add(set.getKey());}
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    // Reading data using readLine
    String name = reader.readLine();
    int num= new Integer(name);
    return this.accounts.get(list.get(num-1));
  }

  public void CreateAccount(double balance) throws Exception {
    Account account= new Account(this,balance);
    this.accounts.put(account.getAccountNumber(),account);
    this.setTransactions(new Transaction("Created new account: "+ account.getAccountNumber()));
  }
  public String getTransactions() {
    return transactions.toString();
  }
  public void setTransactions(Transaction latest) {
    this.transactions.add(latest);
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  @Override
  public String toString() {
    return "User{" +
      "name='" + name + '\'' +
      ", password='" + password + '\'' +
      ", role=" + role +
      ", ListofAccounts=" + accounts.toString() +
      ", ListofTransactions=" + transactions.toString() +
      '}';
  }

  public User getUser(){
    return this;
  }
}
