package main.model;

import java.util.Arrays;

public class User {
  private String name;
  private String password;
  private int role;
  private Account[] accounts;
  private Transaction[] transactions;

  public User(){
    super();
  }

  public User(String name, String password, int role) {
    this.name = name;
    this.password = password;
    this.role = role;
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
