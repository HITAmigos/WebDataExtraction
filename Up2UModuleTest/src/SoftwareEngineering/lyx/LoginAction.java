package SoftwareEngineering.lyx;

/**
 * 登陆操作.
 * .
 * @author liuyx 获得一个用户名和密码，判断是否相符.
 */
public class LoginAction extends TableAction {
  @Override
  public String execute() {
    String result = "failure";
    DBConnection dbHelper = new DBConnection();
    EncodePassword ep = new EncodePassword();
    String tempPassword = ep.Encode(Password);
    if (dbHelper.Login(Username, tempPassword)) {
      System.out.println("Success");
      result = "success";
    } else {
      System.out.println("Failure");
      result = "failure";
    }
    return result;
  }
  
  public static void main(String args[]){
    LoginAction la = new LoginAction();
    la.setUsername("lyx");
    la.setPassword("882");
    la.execute();
    System.out.println("-------------");
    la.setPassword("882776");
    la.execute();
  }
}
