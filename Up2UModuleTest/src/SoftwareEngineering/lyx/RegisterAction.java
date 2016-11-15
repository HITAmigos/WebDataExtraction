package SoftwareEngineering.lyx;

/**
 * ע�����. .
 * 
 * @author liuyx ���һ���û��������� ����������� 1ע��ɹ� 2ע��ʧ��(�������ݿ����ʧ��) 3�û����Ѵ��� 4�����������벻��.
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
