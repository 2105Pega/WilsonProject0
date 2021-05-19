package main.models.sub;

import main.models.Account;
import main.models.User;

import java.util.Map;

public class Admin extends Employee {

  public Admin(String username, String password){
    super(username, password);
    this.setRole(1);
  }


  public void EditCustomer(){};
  public void ViewApplications(){}
  public void EditAccount(){};
  public void CancelAccount();
}
