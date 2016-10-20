package SoftwareEngineering.lyx;

public class DleteAction extends TableAction {

  @Override
  public String execute() {
    String result = "failure";
    DBConnection dbHelper = new DBConnection();
    if(dbHelper.Delete(Username)){
      result = "success";
    }
    return result;
  }

}
