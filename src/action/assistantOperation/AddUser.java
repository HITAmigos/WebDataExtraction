package action.assistantOperation;

import entity.UserTable;
import entity.assistantEntity.User;

public class AddUser {
    private String username;
    private String email;
    private String password;
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
    public String execute(){
        String result = "success";
        System.out.println(password);
        System.out.println(username);
        if(UserTable.getUser(username)!=null){
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

}
