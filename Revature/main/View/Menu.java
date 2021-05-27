package main.View;


import main.models.Account;
import main.models.Transaction;
import main.models.User;
import main.models.Users.Admin;
import main.models.Users.Employee;
import main.service.Services;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Menu {
  static Logger logger = Logger.getLogger(Menu.class);

  public static void main(String[] args) throws Exception {
    User Bob= new User("Bob", "pwd1234");
    new Account(Bob);
  }
  public Menu(){
    super();
  }
  public User Loginscreen() throws Exception {
    User user=new User();
      logger.info("Created Admin User \""+user.getUsername()+"\", LEGGO!");
    Scanner scan = new Scanner(System.in);

    Services services=new Services(); //------Services

    System.out.println("Welcome to E_QCoin, Please login");
      String input;
      logger.debug("Testing logger, who is admin? "+user.printUsers());
      do {
        System.out.println("Press 1 and Enter to Sign-in or 0 and Enter for new Account.");
        try {
          BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
          logger.debug("+"+reader);
        } catch (Exception e){
          e.printStackTrace();
        }
        input=scan.nextLine();
      } while (!(input.equals("1") || input.equals("0")));
      user=input.equals("1") ? services.AccountServices(user): services.NewAccountServices(user);
      return user;
    }

    public String DisplayOptions(User user, int flow) throws Exception {
    String Message;
    try { PropertyConfigurator.configure("Revature/resources/properties/log4j.properties"); }
      catch (Exception e){e.printStackTrace(); System.out.println("Seems not found");;}
      Integer counter=1;
      HashMap<String, String> ops= new HashMap<>();
      ArrayList<Integer> counters = new ArrayList<>();
      String input;
      if (flow==0) {
        Message=("Welcome " + user.getUsername() + ", How can we help you today?");

        logger.info("User " + user.getUsername() + " has logged in.");
      } else{Message= "Anything else?";}
      System.out.println(Message);
      for (String option:user.getOptions().get(0)){
        System.out.println(counter+"\t"+option);
        ops.put(counter.toString(), option);
        counters.add(counter);
        counter++; } //Show Menu Options
      while (true) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        input = reader.readLine();
        if (ops.containsKey(input)) {
         break;
        } else {
          System.out.println("Please choice from above options");
        }
        System.out.println("DONE");
    }
      if (flow!=0){ UserMenu(user,input); }
      return input;
  }

    public User UserMenu(User user, String action) throws Exception {
    switch (user.getRole()){
      case "CUSTOMER":
        user=UserActions(user, action);
        break;
       case "EMPLOYEE":
        user=EmployeeActions((Employee) user, action);
        break;
      case "ADMIN":
        user=AdminActions((Admin) user, action);
        break;
      default:
        System.out.println("Smile!");
        break;
    }
    return user;
  }

  public User UserActions(User user, String action) throws Exception {
    Account account= new Account();
    switch (action){
      case "1":
        new Account(user);
        break;
      case "2":
        Transaction save=account.Withdrew(user);
        System.out.println(save.getLog());
        DisplayOptions(user,2);
        break;
      case "3":
        account.Deposit(user,5);
        DisplayOptions(user,2);
        break;
      case "4":
      account.Transfer(user, 3.50);
        DisplayOptions(user,2);
      break;
      default:
        System.out.println("Smile!");
        break;
    }
    return user;
  }

  public Employee EmployeeActions(Employee user, String action ) throws IOException {
    Account account= new Account();
    switch (action){
      case "1":
          user.ViewCustomers();
        break;
      case "2":
       user.ViewApplications();
        break;
      default:
        System.out.println("Smile!");
        break;
    }
    return user;
  }

  public Admin AdminActions(Admin user, String action){
    Account account= new Account();
    switch (action){
      case "1":
        user.ViewCustomers();
        break;
      case "2":
        user.EditCustomer();
        break;
      case "3":
        user.ViewApplications();
        break;
      case "4":
        user.EditAccount();
        break;
      case "5":
        user.CancelAccount();
        break;
      default:
        System.out.println("Smile!");
        break;
    }
    return user;
  }

    //public void Actions(User user){
    //user.getRole()
   // }
}