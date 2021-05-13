package main;

import main.model.User;

import java.util.HashMap;

public class Bank {

  public static void main(String[] args) {

    HashMap<String,User> users= new HashMap<String,User>();
    User user= new User("Jacquese", "pwd101");
    users.put(user.getName(), user);


    System.out.println("Welcome to E-Quality "+user.getName()+"!\n What actions would you like to take?");


  }
}
