package main.model;

import java.util.Arrays;

public class User {
  private String name;
  private String password;
  private int role;
  private Account[] accounts;
  private Transaction[] transactions;
  private String[] options;

  public User(){
    super();
  }

  public User(String name, String password) {
    this.name = name;
    this.password = password;
    this.setRole(3); //Default 3 for Customer, 2 for Employee, and 1 for Admin
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

  public Account[] getAccounts() {
    return accounts;
  }
  public void setAccounts(Account[] accounts) {
    this.accounts = accounts;
  }

  public Transaction[] getTransactions() {
    return transactions;
  }
  public void setTransactions(Transaction[] transactions) {
    this.transactions = transactions;
  }

  @Override
  public String toString() {
    return "User{" +
      "name='" + name + '\'' +
      ", password='" + password + '\'' +
      ", role=" + role +
      ", ListofAccounts=" + Arrays.toString(accounts) +
      ", ListofTransactions=" + Arrays.toString(transactions) +
      '}';
  }
}
