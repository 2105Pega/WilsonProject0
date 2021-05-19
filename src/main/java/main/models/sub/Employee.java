package main.models.sub;

import main.models.Account;
import main.models.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Employee extends User {

  public Employee(String username, String password){
    super(username, password);
    this.setRole(2);
  }
  public void ViewCustomers(){
    for (Map.Entry<String, User> set : this.getUsers().entrySet()) {
      System.out.println("User "+": "+ (set.getKey()+",\t "+set));};
  };
  public void ViewApplications() throws IOException {
    Account account= new Account();
    for (Map.Entry<String, Account> set : account.getAccounts()) {
      System.out.println("User "+": "+ (set.getKey()+",\t "+set));};
  };

  public HashMap<String, User> getUsers() {
    return new HashMap<String, User>(users);
  }
}
