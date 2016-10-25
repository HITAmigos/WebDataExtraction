package SoftwareEngineering.lyx;

/**
 * 注册操作.
 * .
 * @author liuyx 获得一个用户名和密码 分四种情况： 
 * 1注册成功 2注册失败(由于数据库操作失败) 3用户名已存在 4两次密码输入不符.
 */
public class RegisterAction extends TableAction {
  @Override
  public String execute() {
    String result = "failure";
    DBConnection dbHelper = new DBConnection();
    if (Password != RePassword) {
      System.out.println("DifPassword");
      result = "DifPassword";
    } else if (dbHelper.UserIsExist(Username)) {
      System.out.println("Existed User");
      result = "Existed User";
    } else if (dbHelper.Register(Username, Password)) {
      System.out.println("Success");
      result = "success";
    } else {
      System.out.println("Failure");
      result = "failure";
    }
    return result;
  }
  
  public static void main(String args[]){
    RegisterAction ra = new RegisterAction();
    ra.setUsername("lyx");
    ra.setPassword("882776");
    ra.setRePassword("8827");
    ra.execute();
    System.out.println("------------------");
    ra.setRePassword("882776");
    ra.execute();
    System.out.println("------------------");
    ra.setUsername("lyx");
    ra.setPassword("111");
    ra.setRePassword("111");
    ra.execute();
    System.out.println("------------------");
  }
}
