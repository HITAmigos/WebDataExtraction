package SoftwareEngineering.lyx;

import java.util.List;

public class DisplayAction extends TableAction {

  @Override
  public String execute() {
    String result = "failure";
    DBConnection dbHelper = new DBConnection();
    
    List<String[][]> table = dbHelper.getUserTables(Username);
    
    return result;
  }

  public static void main(String args[]){
    TableAction ta = new DisplayAction();
    ta.setUsername("lyx");
    ta.execute();
  }
  
}
