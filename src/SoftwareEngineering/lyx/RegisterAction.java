package SoftwareEngineering.lyx;

/**
 * 注册操作.
 * 
 * @author liuyx 获得一个用户名和密码 分四种情况： 1注册成功 2注册失败(由于数据库操作失败) 3用户名已存在 4两次密码输入不符
 */
public class RegisterAction extends TableAction {
  @Override
  public String execute() {
    String result = "failure";
    DBConnection dbHelper = new DBConnection();
    if (Password != RePassword) {
      result = "DifPassword";
    } else if (dbHelper.UserIsExist(Username)) {
      result = "Existed User";
    } else if (dbHelper.Register(Username, Password)) {
      result = "success";
    } else {
      result = "failure";
    }
    return result;
  }
}
