package main.models;

import java.io.Serializable;
import java.sql.Time;

public class Transaction implements Serializable {
  private Time timestamp;
  private String log;

  public Transaction(){
    super();
  }

  public Transaction(String log) {
    this.timestamp = new Time(System.currentTimeMillis());
    this.log = log;
  }

  public Time getTimestamp() {
    return timestamp;
  }

  public String getLog() {
    return log;
  }

  public void setLog(String log) {
    this.log = log;
  }

  @Override
  public String toString() {
    return "Transaction{" +
      "timestamp=" + timestamp +
      ", log='" + log + '\'' +
      '}';
  }
}
