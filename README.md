# WilsonProject0
Bank App!

Requirements:
 X ●	Build the application as a Maven project using Java 8
  ●	All interaction with the user should be done through the console using the Scanner class
  ●	Customers of the bank should be able to register with a username and password, and apply to open an account.
    o	Customers should be able to apply for joint accounts
  ●	Once the account is open, customers should be able to withdraw, deposit, and transfer funds between accounts
    o	All basic validation should be done, such as trying to input negative amounts, overdrawing from accounts etc.
  ●	Employees of the bank should be able to view all of their customers information
    o	This includes, account information
    o	Account balances
    o	Personal information
  ●	Employees should be able to approve/deny open applications for accounts
  ●	Bank admins should be able to view and edit all accounts
    o	This includes:
    o	Approving/denying accounts
    o	withdrawing, depositing, transferring from all accounts
    o	canceling accounts
  ●	All information should be persisted using text files and serialization via Object Input/Output Stream
  ●	100% test coverage is expected using J-Unit
    o	You should be using TDD
  ●	Logging should be accomplished using Log4J
    o	All transactions should be logged