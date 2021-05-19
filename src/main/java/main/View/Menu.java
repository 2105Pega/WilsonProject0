package main.View;

import main.models.Account;
import main.models.User;
import main.models.sub.Admin;
import main.models.sub.Employee;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Menu {
  public Menu(){
    super();
  }
    public User Loginscreen() throws Exception {
    Services services=new Services();
      System.out.println("Welcome to E_QCoin, Please login");
      User user= new User("init", null);
      String input;
      do {
        System.out.println("Press 1 and Enter to Sign-in or 0 and Enter for new Account.");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        input = reader.readLine();
      } while (!(input.equals("1") || input.equals("0")));
      user=input.equals("1") ? services.AccountServices(user): services.NewAccountServices(user);
      return user;
    }

    public User UserMenu(User user, String action) throws Exception {
    switch (user.getRole()){
      case 3:
        user=UserActions(user, action);
        break;
      case 2:
        user=EmployeeActions((Employee) user, action);
        break;
      case 1:
        user=AdminActions((Admin) user, action);
        break;
      default:
        System.out.println("Smile!");
        break;
    }
    return user;
  }

  public User UserActions(User user, String action){
    Account account= new Account();
    switch (action){
      case "1":
        account.CreateAccount(user,null);
        break;
      case "2":
        account.Withdrew(user,null);
        break;
      case "3":
        account.Deposit(user,null);
        break;
      case "4":
      account.Transfer(user, null);
      break;
      default:
        System.out.println("Smile!");
        break;
    }
    return user;
  }

  public Employee EmployeeActions(Employee user, String action ){
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