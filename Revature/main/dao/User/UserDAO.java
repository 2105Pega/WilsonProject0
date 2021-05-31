package main.dao.User;

import main.models.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

  void insertUser(User user) throws SQLException;
  boolean updateUser(User user) throws SQLException;
  void selectUser(String username) throws SQLException;
  List<User> selectAllUsers() throws SQLException;

  User selectUser(User user) throws SQLException;

  boolean deleteUser(int id) throws SQLException;
  boolean deleteUser(String username);
}
