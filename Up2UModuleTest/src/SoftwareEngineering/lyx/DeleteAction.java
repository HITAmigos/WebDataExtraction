package SoftwareEngineering.lyx;

/**
 * 删除数据库中的表.
 * @author liuyx。
 * 。
 */
public class DeleteAction extends TableAction {

  @Override
  public String execute() {
    String result = "failure";
    DBConnection dbHelper = new DBConnection();
    if(dbHelper.Delete(Username)){
      result = "success";
    }
    return result;
  }
  
  public static void main(String args[]){
    DeleteAction da = new DeleteAction();
    da.setUsername("lyx");
    da.execute();
  }
  
}
