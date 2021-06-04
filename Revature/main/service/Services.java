package main.service;

import main.models.User;
import org.apache.log4j.Logger;

public class Services {
  static Logger logger = Logger.getLogger(Services.class);
  private static int counter=1;
  public Services(){}

  public static void main(String[] args) {

  }

  public User AccountServices(User user) throws Exception {
    /*
    Menu menu= new Menu();
    HashMap<String, User> users= user.getUsers();
    String[] session;
    do {   //get user and password
      if (counter==1) { System.out.println("Please enter username [space] followed by your password");}
      else{ System.out.println("Please enter correct combination of: username [space] password"); }
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      String input = reader.readLine();
      session=input.split(" ");
      //OR create new Account and reset^
      if (input.equals("New Account")){ user=NewAccountServices(user);}
      else if (counter==7){menu.Loginscreen();}
      else if (!users.containsKey(session[0])){
        System.out.println("User \""+session[0]+ "\" doesn't exist, please try again or enter \"New Account\" to create New account.\nMax 7 attempts, current="+ counter);
      }else if (counter>=3 && users.containsKey(session[0])){
        System.out.println("It seems your password is incorrect. Max 7 attempts, current= "+ counter);
      } else {
        System.out.println("Something something...");
        break;
      } counter++;
    } while (!(users.containsKey(session[0]) && users.get(session[0]).getPassword().equals(session[1])));

    user=new User(session[0],session[1]);
    return user; //Return User
  }

  public User NewAccountServices(User user) throws IOException {
    try { PropertyConfigurator.configure("Revature/resources/properties/log4j.properties"); }
    catch (Exception e){e.printStackTrace(); System.out.println("Seems not found");}

    HashMap<String, User> users= user.getUsers();
    String username=" ", password = " ";
    do {
      if (username.equals(" ")) { System.out.println("First a new Username: "); }
      else if (username!=null && username.length()<5) {
        System.out.println("Please make sure longer than 5 characters:");
      } else { System.out.println("User already exist! Please try again"); }
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      username = reader.readLine().trim();
    } while (username.length() < 5 && users.containsKey(username));

    do {
      if (password.equals(" ")) {System.out.println("And finally Password.");} else if (password!=null && password.length()<5){
      System.out.println("Please make sure longer than 5 characters:");
    }  else { System.out.println("Please create password longer than 5 characters: "); }
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      password = reader.readLine();
    }while(password.length()<5);

    user= new User(username,password);
    logger.info("\nNew List of Users:\n"+user.printUsers());
    return user;
  }
  */
    return null;
  }
}