package action.tableOperation;

import java.util.ArrayList;
import action.Action;
import entity.*;

public class Save extends Action {
  private String url = null;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override
  public String execute() {
    ContentReader cr = new ContentReader();
    WebText wt = new WebText();
    cr.setUrl(url);
    cr.varifyInput();
    cr.readAsString();
    wt.setContent(cr.getContent());
    wt.grabTable();
    //
    ArrayList<String[][]> table = wt.getTable();
    for (int i = 0; i < table.size(); i++) {
      System.out.println("table No." + (i + 1));
      for (int j = 0; j < table.get(i).length; j++) {
        for (int k = 0; k < table.get(i)[j].length; k++) {
          System.out.print(table.get(i)[j][k] + "|\t");
        }
        System.out.println();
      }
    }
    //
    return "failure";
  }

  public static void main(String args[]){
    Save s = new Save();
    s.setUrl("http://www.w3school.com.cn/html/html_tables.asp");
    s.execute();
  }
  
}
