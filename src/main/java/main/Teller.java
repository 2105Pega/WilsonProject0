package models;


import main.View.Menu;
import main.models.Account;
import main.models.Transaction;
import main.models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Teller {

  public static void main(String[] args) throws Exception {
    HashMap<String, User> users = new HashMap<>();
    HashMap<String, Account> accounts = new HashMap<>();
    HashMap<String, Transaction> transactions = new HashMap<>();
    HashMap<String, String> ops= new HashMap<>();
    Integer counter=1;
    ArrayList<Integer> counters = new ArrayList<>();
    String input;
    //Create and add user
    //Create (User
    User user = new Menu().Loginscreen(); //----To Menu for user instance
    users.put(user.getUsername(), user);

    System.out.println(user);
    System.out.println("Welcome "+user.getUsername() +" How can we help you today?");
    for (String option:user.getOptions().get(0)){
      System.out.println(counter+"\t"+option);
      ops.put(counter.toString(), option);
      counters.add(counter);
      counter++; } //Show Menu Options

    while (true) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      input = reader.readLine();
      if (ops.containsKey(input)) {
            Menu usermenu= new Menu();
            usermenu.UserMenu(user, input);
      } else {
        System.out.println("Please choice from above options");
      }
      System.out.println("DONE");


      Account account = user.getAccounts();
      accounts.put(account.getAccountNumber(), account);

      try {
        byte[] Account = accounts.toString().getBytes();
        byte[] User = users.toString().getBytes();

        FileOutputStream userdisc = new FileOutputStream("src/test/java/resources/userdisc.txt");
        BufferedOutputStream boutuser = new BufferedOutputStream(userdisc);
        boutuser.write(User);
        boutuser.flush();
      } catch (Exception e) {
        System.out.println("Nope! " + e);
      } } }}
/*
  protected String NewAccountServices() throws IOException {
    String name=null,username=null,password=null, message= null;

    System.out.println("Could you provide your name: ");
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    // Reading data using readLine
    username = reader.readLine();
    do { if (username==null){ System.out.println("Alright now new Username. Please make sure longer than 5 characters:");} else if (username!=null && username.length()<5){
      System.out.println("Please make sure longer than 5 characters:");
    } else { System.out.println("User already exist! Please try again"); }
      username = reader.readLine();
    } while(username.length()<5 && (users==null || users.containsKey(username)));

    do { if (password==null) {System.out.println("And finally Password. Please make sure is longer than 5 characters! ");} else if (password!=null && password.length()<5){
      System.out.println("Please make sure longer than 5 characters:");
    }  else { System.out.println("Please create password longer than 5 characters: "); }
      password = reader.readLine();
    }while(password.length()<5);

    User user= new User(username,password,name);
    System.out.println(user);
    try {
      users.put(username,user);
    } catch (Exception e){
      System.out.println(users + "\nStill got this error: "+ e);
    }
    username=AccountServices(username + " Welcome to your Bank!");
    return username;
  }

  protected  String AccountServices(String user){
    if (user!=null){System.out.println(user);}
    do {
      if (counter==0) { System.out.println("Please enter username [space] followed by your password");}
      else { System.out.println("Please enter correct combination of: username [space] password"); }

      user= scan.nextLine();
      session=user.split(" ");
      System.out.println(users);
      if (user.equals("New Account")){ NewAccountServices();}
      else if (users ==null || !users.containsKey(session[0])){
        System.out.println("User \""+session[0]+ "\" doesn't exist, please try again or enter \"New Account\" to create New account.\nMax 7 attempts, current="+ counter);
      }else if (counter>=3 && users.containsKey(session[0])){
        System.out.println("It seems your password is incorrect. Max 7 attempts, current= "+ counter);
      } else if (counter==7){ }
      counter++;
    } while (users ==null || !users.containsKey(session[0]) && users.get(session[0]).getPassword().equals(session[1]));

    return session[0]; //Return User
  } */
