package action.userOperation;

import action.Action;
import entity.UserTable;
import entity.assistantEntity.User;

public class Login extends Action {
  private String password;

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String execute() {
    String result = "success";
    User user = UserTable.getUser(username);
    if (!user.getPassword().equals(password)) {
      result = "back";
    }else if(user.getLevel()==1){
      result = "admin";
    }else if(user.getLevel()==0){}
    else{
      result = "error";
    }
    return result;
  }

  public static void main(String args[]) {
    Login l = new Login();
    l.setUsername("lyx");
    l.setPassword("882776");
    l.execute();
  }

}
