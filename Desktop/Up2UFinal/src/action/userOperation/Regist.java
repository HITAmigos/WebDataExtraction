package action.userOperation;

import action.Action;
import entity.UserTable;
import entity.assistantEntity.User;

public class Regist extends Action {
  private String password;
  private String email;
  
  public void setPassword(String password) {
    this.password = password;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String execute() {
    String result = "success";
    if(UserTable.getUser(username)!=null){
      result = "exist";
    }else{
      User user = new User();
      user.setUsername(username);
      user.setPassword(password);
      user.setEmail(email);
      user.setLimit(0);
      user.setLevel(0);
      user.setCoins(0);
      if(!UserTable.Regist(user)){
        result = "false";
      }
    }
    return result;
  }

  public static void main(String args[]){
    Regist r = new Regist();
    r.setEmail("eeee");
    r.setPassword("111");
    r.setUsername("aaa");
    r.execute();
  }
}
