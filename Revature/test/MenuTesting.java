package test;

import main.models.User;
import main.models.Users.Admin;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class MenuTesting {

  private final Menu mini= new Menu();

  public static void main(String[] args) throws IOException {
    }

  public static class UserExperience extends MenuTesting {
    public UserExperience(){}

    @Test
    public void us() throws IOException {

    }
    /*
    while(true){
        try {
          System.out.println(Message);
          deposit=Double.parseDouble(input.trim()); break;
        } catch (Exception e){e.printStackTrace();
          System.out.println("Please enter in $$ or \"00.00\" format"); }
          input=scan.nextLine();
      }*/

    @Test
    public void getUserX() {
      User TesterX = new User("Testx", "xxxxx", "Power to the Users!");
      System.out.println("Can we get UserName?\n");
      String input = TesterX.getUsername();
      assertTrue(input.length() > 0);
      System.out.println("Test User is \"" + input + "\"");
    }}

    public static class EmployeeExperience extends MenuTesting {
    public EmployeeExperience(){}
    @Test
    public void getEmplUser(){
      User TesterE = new Admin("Teste", "esese", "Employee me!");
      System.out.println("Can we get UserName?\n");
      String input=TesterE.getUsername();
      assertTrue(input.length()>0);
      System.out.println("Test User is \""+input+"\"");
    }
  }

  public static class AdminExperience extends MenuTesting {
    public AdminExperience(){}
    @Test
    public void getAdminUser(){
    User TesterA = new Admin("Testa", "asasa", "Admin Experience!");
    System.out.println("Can we get UserName?\n");
    String input=TesterA.getUsername();
    assertTrue(input.length()>0);
    System.out.println("Test User is \""+input+"\"");
  }}
}
