package net.kuangmeng.table;

import net.kuangmeng.*;

public class RecoverAction  {

  private String username;
  
  
  public String getUsername() {
    return username;
  }


  public void setUsername(String username) {
    this.username = username;
  }


  public String execute() {

    System.out.println("username("+username+")");
    
    String result = "error";
    DBConnection dbHelper = new DBConnection();
    
    if (dbHelper.RecoverAll(username) ){
      result = "success";
    }

    return result;
  }
  
}
