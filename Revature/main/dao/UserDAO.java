package main.dao;

import main.models.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

  void insertUser(User user) throws SQLException;
  boolean updateUser(User user) throws SQLException;
  User selectUser(int id);
  User selectUser(String username);
  List<User> selectAllUsers() throws SQLException;
  boolean deleteUser(int id);
  boolean deleteUser(String username);
}
