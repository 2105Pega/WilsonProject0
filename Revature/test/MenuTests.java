package test;

import main.models.User;
import main.models.Users.Admin;
import org.junit.Test;

import java.awt.*;
import java.util.Scanner;

import static org.testng.Assert.assertTrue;

public class MenuTests {
  static Scanner scan = new Scanner(System.in);
  private final Menu mini= new Menu();

  public static class UserExperience extends MenuTests {
    public UserExperience() {
    }

    @Test
    public void getUserX() {
      User TesterX = new User("Testx", "xxxxx", "Power to the Users!");
      System.out.println("Can we get UserName?\n");
      String input = TesterX.getUsername();
      assertTrue(input.length() > 0);
      System.out.println("Test User is \"" + input + "\"");
    }}

    public static class EmployeeExperience extends MenuTests {
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

  public static class AdminExperience extends MenuTests {
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
