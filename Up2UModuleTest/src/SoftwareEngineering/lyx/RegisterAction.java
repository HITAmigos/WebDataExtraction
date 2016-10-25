package SoftwareEngineering.lyx;

/**
 * ע�����.
 * .
 * @author liuyx ���һ���û��������� ����������� 
 * 1ע��ɹ� 2ע��ʧ��(�������ݿ����ʧ��) 3�û����Ѵ��� 4�����������벻��.
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
