package SoftwareEngineering.lyx;

/**
 * ��½����
 * 
 * @author liuyx ���һ���û��������룬�ж��Ƿ����
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
