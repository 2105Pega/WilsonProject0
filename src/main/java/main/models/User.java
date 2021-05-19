package main.models;

import main.models.Users.Admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.*;

public class User implements Serializable {

  private String username;
  private String name;
  private String password;
  private int role;
  private HashMap<String, Account> accounts= new HashMap<>(); //List of accounts
  private final ArrayList<Transaction> transactions= new ArrayList<>();
  private final ArrayList<String[]> options= new ArrayList<>();
  private double balance;
  private static final HashMap<String, User> users=new HashMap<>();

  public User(){
    super();
    User user= new Admin("Shake","nBake");
  }

  public User(String username, String password, String name) {
    super();
    this.username=username;
    this.password = password;
    this.setRole(3); //Default 3 for Customer, 2 for Employee, and 1 for Admin
    this.name=name;
    users.put(username,this);
  }
  public User(String username, String password) {
    super();
    this.username = username;
    this.password = password;
    this.setRole(3); //Default 3 for Customer, 2 for Employee, and 1 for Admin
    users.put(username,this);
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
        String[] useropts=new String[]{"Create Account", "Withdraw", "Deposit", "Transfer to another Member"};
        this.options.add(useropts);
        break;
      case 2:
        String[] emplops=new String[]{"View Customer Details", "View Pending Applications"};
        this.options.add(emplops);
        break;
      case 1:
        String[] adminop=new String[]{"View Customer Details","Edit Customer Details", "View Pending Applications", "Edit Account","Cancel Account"};
        this.options.add(adminop);
        break;
      default:
        System.out.println("Wooooooah");
        break;
    }
  }

  public Account getAccounts() throws IOException {
    Account account= new Account();
    int x=1;
    ArrayList<String> list= new ArrayList<>();
    System.out.println("Which one?");
    for (Map.Entry<String, Account> set : account.getAccounts().entrySet()) {
      System.out.println("Account "+ x+": "+ account.getAccounts().get(set.getKey()).getAccountName()+",\t "+set.getKey()); x++; list.add(set.getKey());}
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    // Reading data using readLine
    String name =reader.readLine();
    int num= Integer.parseInt(name);
    System.out.println(account.getAccounts().get(list.get(num-1)));
    return account.getAccounts().get(list.get(num-1)); }

  public ArrayList<String[]> getOptions() {
    return options;
  }

  public String getTransactions() {
    return transactions.toString();
  }
  public void setTransactions(Transaction latest) {
    this.transactions.add(latest);
    System.out.println(transactions.toString());
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
      "username='" + username + '\'' +
      ", name='" + name + '\'' +
      ", password='" + password + '\'' +
      ", role=" + role +
      ", accounts=" + accounts +
      ", transactions=" + transactions +
      ", options=" + options +
      ", balance=" + balance +
      '}';
  }

  public HashMap<String, User> getUsers() {
    return new HashMap<String, User>(users);
  }
  public User getUser(){
    return this;
  }
}
