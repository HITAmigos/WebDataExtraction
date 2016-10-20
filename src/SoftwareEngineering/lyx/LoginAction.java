package SoftwareEngineering.lyx;

/**
 * 登陆操作
 * 
 * @author liuyx 获得一个用户名和密码，判断是否相符
 */
public class LoginAction extends TableAction {
  @Override
  public String execute() {
    String result = "failure";
    DBConnection dbHelper = new DBConnection();
    if (dbHelper.Login(Username, Password)) {
      result = "success";
    } else {
      result = "failure";
    }
    return result;
  }
}
