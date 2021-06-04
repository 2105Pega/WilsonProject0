package main.models;

import org.apache.commons.math3.util.Precision;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.sql.Array;
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
  public static int[] addtoArray(int n, int[] arr, int x) {
    int i;
    int[] newarr = new int[n + 1];

    if (n==0){
      newarr=new int[1];
      newarr[n] = x;
    } else {
      for (i = 0; i < n; i++)
        newarr[i] = arr[i];
      newarr[n] = x;
    }
    return newarr;
  }

  static Logger logger = Logger.getLogger(Account.class);

  //fields
  private int accountNumber;
  private String Status;
  private String account;
  private List<Integer> Users= new ArrayList<>();
  private String UsersSQL;
  private int[] UsersArray;
  private String name;
  private double Balance;

  //Other
  private ArrayList<Transaction> transactions= new ArrayList<>();

  //List
  private  HashMap<Integer, Account> Accountmap = new HashMap<>(); //list of users who can access
  private static int counter;

  public Account(){
    super();
    this.setUsersSQL("");
    this.setAccountNumber(generateUniqueId());
    this.setAccount("Savings");
    this.setStatus("PENDING");
    this.setName("SS1");
  }

  public int getAccountNumber() {
    return accountNumber;
  }
  public void setAccountNumber(int accountNumber) {
    this.accountNumber = accountNumber;
  }

  public String getStatus() {
    return Status;
  }
  public void setStatus(String status) {
    Status = status;
  }

  public String getAccount() {
    return account;
  }
  public void setAccount(String account) {
    this.account = account;
  }

  public List<Integer> getUsers() {
    return Users;
  }
  public String addUsers(int user) {
    Users.add(user);
    StringBuilder urps = new StringBuilder("{");
    int x = 0;
    for (int urp : Users) {
      if (!urps.toString().equals("{")) {
        urps.append(","); }
      urps.append(urp); }
    urps.append("}");
    this.UsersSQL =urps.toString();
    return urps.toString();
  }
  public String addUsers(int[] user) {
    int x=0;
    while (x<user.length) {
      Users.add(user[x]);
      x++; }
    StringBuilder urps = new StringBuilder("{");
    for (int urp : Users) {
      if (!urps.toString().equals("{")) {
        urps.append(","); }
      urps.append(urp); }
    urps.append("}");
    this.UsersSQL =urps.toString();
    return urps.toString(); }

  public String deleteUser(int user){
    if (this.Users.get(user)==null || this.Users.get(user)==0 ){
      return "User not found.";
    }   else { this.Users.remove(user);  return  user +" successfully removed.";}
  }

  public String getUsersSQL() {
    return UsersSQL;
  }

  public void setUsersSQL(String usersSQL) {
    this.UsersSQL = usersSQL;
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public double getBalance() {
    return Balance;
  }
  public void setBalance(double balance) {
    Balance = Precision.round(balance,2);
  }

  public void addAccount(int account) {

    //this.Users=addtoArray(this.Users.length, this.Users, account);
  }

  @Override
  public String toString() {
    return "Account{" +
      "accountNumber=" + accountNumber +
      ", Status='" + Status + '\'' +
      ", account='" + account + '\'' +
      ", Users=" + UsersSQL +
      ", name='" + name + '\'' +
      ", Balance=" + Balance +
      '}';
  }

  public void addUsers(Array userids) {

  }
}
  


