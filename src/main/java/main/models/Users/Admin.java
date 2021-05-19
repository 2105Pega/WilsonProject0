package main.models.Users;

public class Admin extends Employee {

  public Admin(String username, String password){
    super(username, password);
    this.setRole(1);
  }


  public void EditCustomer(){};
  public void ViewApplications(){}
  public void EditAccount(){};
  public void CancelAccount(){};
}
