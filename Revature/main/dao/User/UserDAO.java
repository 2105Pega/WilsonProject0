package main.dao.User;

import main.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

  //CREATE USER
  void insertUser(User user) throws SQLException;

  ///READ DATABASE
  List<User> selectUser(String username) throws SQLException;
  List<User> selectUser(int user) throws SQLException;
  List<User> selectAllUsers() throws SQLException;
  List<User> iterateDB(ResultSet resultSet) throws SQLException;

  //UPDATE FULL ROW
  List<User> updateUser(User user) throws SQLException;

  //DELETE BASED ON ID
  List<User> deleteUser(int id) throws SQLException;
}
