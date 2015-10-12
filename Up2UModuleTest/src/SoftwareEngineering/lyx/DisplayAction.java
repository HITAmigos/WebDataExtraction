package SoftwareEngineering.lyx;

/**
 * 从前端传递一个用户名，找到其表格并返回.
 * @author liuyx
 */
public class DisplayAction extends TableAction {

  /**
   * @param  用户名
   * @return request 表格内容
   * @return 操作是否成功
   */
  @Override
  public String execute() {
    String result = "failure";
    DBConnection dbHelper = new DBConnection();
    
    String[][] table = dbHelper.Search(Username);
    
    return result;
  }

}
