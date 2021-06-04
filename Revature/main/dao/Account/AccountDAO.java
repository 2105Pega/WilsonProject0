package main.dao.Account;

import main.models.Account;
import main.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface AccountDAO {
  //CREATE USER
  void createAccount(User user) throws SQLException;

  ///READ DATABASE

  //READ DB
  List<Account> selectAccountbyid(int id) throws SQLException;

  //READ DB
  List<Account> selectAccountuid(int userid) throws SQLException;

  List<Account> selectAccountPending() throws SQLException;

  List<Account> selectAllAccounts() throws SQLException;
  List<Account> iterateDB(ResultSet resultSet) throws SQLException;

  //UPDATE FULL ROW

  //UPDATE ACCOUNTS
  List<Account> ApproveAccount(int acc) throws SQLException;
  List<Account> AddUser (int user, int account) throws SQLException;
  boolean depositAmount(int account, double balance) throws SQLException;
  boolean withdrawAmount(int account, double balance) throws SQLException;
  boolean transferAmount(Account account) throws SQLException;

  //DELETE BASED ON ID
  List<Account> deleteAccount(int id) throws SQLException;
}
