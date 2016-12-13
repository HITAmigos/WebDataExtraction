package action.userOperation;

import action.Action;
import entity.UserTable;
import entity.assistantEntity.User;

public class Login extends Action {
  private String password;
  private String username;
  public String getUsername(){
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getPassword() {
	return password;
}
public void setPassword(String password) {
    this.password = password;
  }
  public String execute() {
    String result = "success";
    username = username.toLowerCase();
    User user = UserTable.getUser(username);
    System.out.println(username);
    System.out.println(password);
    System.out.println(user.getPassword());
    if (!user.getPassword().equals(password)){
      result = "back";
    }else if(user.getLimited()==1){
      result = "admin";
    }else if(user.getLimited()==0){	
    }else{
      result = "error";
    }
    System.out.println(result);
    return result;
  }

}
