package main.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Userc implements Serializable {
  public static int generateUniqueId() {
    UUID idOne = UUID.randomUUID();
    String str=""+idOne;
    int uid=str.hashCode();
    String filterStr=""+uid;
    str=filterStr.replaceAll("-", "");
    return Integer.parseInt(str);
  }
  public static int[] addtoArray (int n, int[] arr, int x)
  {
    int i;
    int[] newarr = new int[n + 1];

    if (arr==null){
      newarr=new int[1];
    } else {
      // then insert x at n+1
      for (i = 0; i < n; i++)
        newarr[i] = arr[i];

      newarr[n] = x;
    }
    return newarr;
  }
  //fields
  private int Userid;
  private String username, name, password, role;
  private int[] accounts; //List of accounts

  //Other
  private final ArrayList<String[]> options= new ArrayList<>();
  private ArrayList<Transaction> transactions= new ArrayList<>();

  //List
  private static HashMap<String, Userc> users=new HashMap<>();
  private HashMap<Integer, Account> accountsh=new HashMap<>();
  public HashMap<Integer, Account> getAccountsh() {
    return accountsh;
  }
  public void setAccountsh(int num, Account neq) {
    this.accountsh.put(num, neq);
  }
/*
  public Userc(){
    super();
    this.Userid =generateUniqueId();
    this.username="Shake";
    this.password="nBake";
    this.name="J. Wilson";
    this.role="ADMIN";
    users.put(this.username, this);
  }

  public int getUserid() {
    return Userid;
  }

  public Userc(String username, String password, String name) {
    super();
    this.Userid =generateUniqueId();
    this.username=username;
    this.password = password;
    this.setRole("EMPLOYEE"); //Default 3 for Customer, 2 for Employee, and 1 for Admin
    this.name=name;
    users.put(username,this);
  }
  public Userc(String username, String password) {
    super();
    this.Userid =generateUniqueId();
    this.username = username;
    this.password = password;
    this.setRole("CUSTOMER"); //Default 3 for Customer, 2 for Employee, and 1 for Admin
    users.put(username,this);
  }

  public void setUsername(String username) {
    this.username = username;
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

  public String getRole() {
    return role;
  }
  public void setRole(String role) {
    this.role = role;
    switch (role){
      case "CUSTOMER":
        String[] useropts=new String[]{"Create Account", "Withdraw", "Deposit", "Transfer to another Member"};
        this.options.add(useropts);
        break;
      case "EMPLOYEE":
        String[] emplops=new String[]{"View Customer Details", "View Pending Applications"};
        this.options.add(emplops);
        break;
      case "ADMIN":
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
    ArrayList<Integer> list= new ArrayList<>();
    System.out.println("Which one?");
    for (Map.Entry<Integer, Account> set : account.getAccounts().entrySet()) {
      if (set.getValue().getUsers().containsKey(this.getUsername())){
        System.out.println("Account\nOption\t\tName\t\tNumber\nAccount "+ x+": "+ account.getAccounts().get(set.getKey()).getAccountName()+",\t "+set.getKey()); x++; list.add(set.getKey());}
      }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    // Reading data using readLine
    String name =reader.readLine().trim();
    int num= Integer.parseInt(name);
    System.out.println(account.getAccounts().get(list.get(num-1)));
    return account.getAccounts().get(list.get(num-1)); }

  public int[] getAccountz() {
  return accounts;
  }

  public ArrayList<String[]> getOptions() {
    return options;
  }

  public String getTransactions() {
    return transactions.toString();
  }
  public void setTransactions(Transaction latest) {
    this.transactions.add(latest);
  }

  @Override
  public String toString() {
    return "User{" +
      "Userid=" + Userid +
      ", username='" + username + '\'' +
      ", name='" + name + '\'' +
      ", password='" + password + '\'' +
      ", role='" + role + '\'' +
      ", accounts=" + accounts +
      ", transactions=" + transactions +
      '}';
  }

  public Userc getUser(){
    return this;
  }

  public HashMap<String, Userc> getUsers() {
    return new HashMap<String, Userc>(users); }

  public void addaccount(Account account){
    int[] newarr = new int[0];
    try{newarr=addtoArray(accounts.length, accounts, account.getAccountNumber());} catch (NullPointerException n){
      newarr=addtoArray(0,null,account.getAccountNumber());
    }catch (Exception e){e.printStackTrace();}
    this.accounts=newarr;
    System.out.println(Arrays.toString(getAccountz()));
  }

  public StringBuilder printUsers(){
      StringBuilder buffer=new StringBuilder("---------------------------------\n");
      for (Map.Entry<String, Userc> set :
        users.entrySet()) {
        // Printing all elemnts of a Map
        buffer.append(set.getKey()).append(" = ").append(set.getValue()+ "\n");
      }
      return buffer; }
*/}