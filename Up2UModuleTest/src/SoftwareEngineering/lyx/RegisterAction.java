package SoftwareEngineering.lyx;

/**
 * ע�����.
 * 
 * @author liuyx ���һ���û��������� ����������� 1ע��ɹ� 2ע��ʧ��(�������ݿ����ʧ��) 3�û����Ѵ��� 4�����������벻��
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
