package SoftwareEngineering.lyx;

/**
 * ×¢Ïú¹¦ÄÜ.
 * @author liuyx.
 * .
 */
public class CancellationAction extends GrabAction {

  public String execute(){
    String result = "failure";
    DBConnection dbHelper = new DBConnection();
    if(dbHelper.Cancellation(Username)){
      result = "success";
    }
    return result;    
  }
  
  public static void main(String[] args) {
    CancellationAction ca = new CancellationAction();
    ca.setUsername("lyx");
    ca.execute();
  }

}
