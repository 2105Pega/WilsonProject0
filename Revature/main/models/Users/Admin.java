package main.models.Users;

public class Admin extends Employee {

  public Admin(String username, String password, String name ){
    super(username, password, name);
    super.setRole("ADMIN");
  }


  public void EditCustomer(){};
  public void ViewApplications(){}
  public void EditAccount(){};
  public void CancelAccount(){};
}
