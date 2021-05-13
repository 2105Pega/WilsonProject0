package main.model;

import java.sql.Time;

public class Transaction {
  private Time timestamp;
  private String log;

  public Transaction(){
    super();
  }

  public Transaction(Time timestamp, String log) {
    this.timestamp = timestamp;
    this.log = log;
  }

  public Time getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Time timestamp) {
    this.timestamp = timestamp;
  }

  public String getLog() {
    return log;
  }

  public void setLog(String log) {
    this.log = log;
  }
}
