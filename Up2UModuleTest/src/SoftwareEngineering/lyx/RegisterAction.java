package SoftwareEngineering.lyx;

/**
 * 注册操作. .
 * 
 * @author liuyx 获得一个用户名和密码 分四种情况： 1注册成功 2注册失败(由于数据库操作失败) 3用户名已存在 4两次密码输入不符.
 */
public class RegisterAction extends TableAction {
  @Override
  public String execute() {
    String result = "failure";
    DBConnection dbHelper = new DBConnection();
    if (Password != RePassword) {
      System.out.println("DifPassword");
      result = "DifPassword";
    } else if (Tel == null && Email == null) {
      System.out.println("lack of tel or email.");
      result = "lack of information";
    } else if (dbHelper.UserIsExist(Username)) {
      System.out.println("Existed User");
      result = "Existed User";
    } else {
      EncodePassword ep = new EncodePassword();
      String tempPassword = ep.Encode(Password);
      boolean regSuccess = dbHelper.Register(Username, tempPassword, Email, Tel);
      if (regSuccess) {
        System.out.println("Success");
        dbHelper.CreateResource(Username);
        result = "success";
      } else {
        System.out.println("Failure");
        result = "failure";
      }
    }
    return result;
  }

  public static void main(String args[]) {
    RegisterAction ra = new RegisterAction();
    ra.setUsername("lyx");
    ra.setPassword("882776");
    ra.setRePassword("882776");
    // ra.execute();
    // System.out.println("------------------");
    ra.setTel("13804563679");
    ra.setEmail("1486587187@qq.com");
    ra.execute();
  }
}
