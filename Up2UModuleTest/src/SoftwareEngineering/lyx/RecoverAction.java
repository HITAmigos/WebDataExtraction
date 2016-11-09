package SoftwareEngineering.lyx;

public class RecoverAction extends TableAction {

  @Override
  public String execute() {
    String result = "failure";
    DBConnection dbHelper = new DBConnection();

    if (dbHelper.RecoverAll(Username)) {
      result = "success";
    }

    return result;
  }

  public static void main(String args[]){
    RecoverAction ra= new RecoverAction();
    ra.setUsername("lyx");
    ra.execute();
  }
  
}
