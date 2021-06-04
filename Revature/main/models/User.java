package main.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class User implements Serializable {
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
  private String Username, Name, Password;
  private int[] Accounts;
  private String Role;

  //Other
  private final ArrayList<String[]> options= new ArrayList<>();
  private ArrayList<Transaction> transactions= new ArrayList<>();

  //List
  private static HashMap<String, User> users=new HashMap<>();
  private HashMap<Integer, Account> accountsh=new HashMap<>();
  public HashMap<Integer, Account> getAccountsh() {
    return accountsh;
  }
  public void setAccountsh(int num, Account neq) {
    this.accountsh.put(num, neq);
  }

  public User(){super(); this.setUserid(generateUniqueId());}

  public int getUserid() {
    return Userid;
  }
  public void setUserid(int userid) {
    Userid = userid;
  }

  public String getUsername() {
    return Username;
  }
  public void setUsername(String username) {
    Username = username;
  }

  public String getName() {
    return Name;
  }
  public void setName(String name) {
    Name = name;
  }

  public String getPassword() {
    return Password;
  }
  public void setPassword(String password) {
    Password = password;
  }

  public int[] getAccounts() {
    return Accounts;
  }
  public void setAccounts(int[] accounts) {
    Accounts = accounts;
  }

  public String getRole() {
    return Role;
  }
  public void setRole(String role) {
    Role = role;
  }

  @Override
  public String toString() {
    return "User{" +
      "Userid=" + Userid +
      ", Username='" + Username + '\'' +
      ", Name='" + Name + '\'' +
      ", Password='" + Password + '\'' +
      ", Accounts=" + Arrays.toString(Accounts) +
      ", Role='" + Role + '\'' +
      ", options=" + options +
      ", transactions=" + transactions +
      ", accountsh=" + accountsh +
      '}';
  }
}