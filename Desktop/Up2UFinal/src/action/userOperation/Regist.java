package action.userOperation;

import action.Action;
import entity.UserTable;
import entity.assistantEntity.User;

public class Regist extends Action  {
  private String password;
  private String email;
  private String username;
  private String pass;
  
  public void setPassword(String password) {
    this.password = password;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getPassword() {
	return password;
}

public String getEmail() {
	return email;
}

@Override
  public String execute(){
    String result = "success";
    System.out.println(password);
    System.out.println(pass);
    System.out.println(username);
    if(UserTable.getUser(username)!=null || !password.equals(pass)){
      result = "exist";
    }else{
      User user = new User();
      user.setUsername(username);
      user.setPassword(password);
      user.setEmail(email);
      user.setLimited(0);
      user.setLevel(0);
      user.setCoins(0);
      System.out.println(username);
      System.out.println(email);
      
      if(!UserTable.Regist(user)){
        result = "error";
      }
    }
    System.out.println(result);
    return result;
  }

public String getPass() {
	return pass;
}

public void setPass(String pass) {
	this.pass = pass;
}
}
