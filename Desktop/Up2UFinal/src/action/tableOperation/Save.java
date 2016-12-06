package action.tableOperation;

import java.util.ArrayList;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import action.Action;
import entity.*;
import entity.assistantEntity.*;

public class Save extends Action {
  private String url = null;
  private int type = 0;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  private WebText grabTable() {
    ContentReader cr = new ContentReader();
    WebText wt = new WebText();
    cr.setUrl(url);
    if (cr.varifyInput().equals("upload")) {
      type = 1;
    } else {
      type = 0;
    }
    cr.readAsString();
    wt.setContent(cr.getContent());
    wt.grabTable();
    return wt;
  }

  private String insertSearchRecord() {
    String tablename = username + "-" + new Integer(SearchRecordTable.getLastId() + 1);
    SearchRecord searchRecord = new SearchRecord();
    searchRecord.setUsername(username);
    searchRecord.setTablename(tablename);
    searchRecord.setLink(url);
    searchRecord.setType(type);
    searchRecord.setDate(BeijingTime.getWebsiteDatetime());
    SearchRecordTable.insert(searchRecord);
    return tablename;
  }

  private boolean createTable(String tablename, String[] tableTitle) {
    Database db = new Database(tablename);
    ArrayList<ColumnUnit> column = new ArrayList<ColumnUnit>();

    ColumnUnit id = new ColumnUnit();
    id.setColumnName("id");
    id.setColumnType("int");
    id.setDefaultValue("auto_increment");
    id.setPrimaryKey(true);
    column.add(id);
    for (int j = 1; j < tableTitle.length; j++) {
      ColumnUnit temp = new ColumnUnit();
      temp.setColumnName(tableTitle[j]);
      temp.setColumnType("varchar(225)");
      temp.setDefaultValue("");
      temp.setPrimaryKey(false);
      column.add(temp);
    }
    db.setTablename(tablename);
    if (!db.create(column)) {
      return false;
    }
    return true;
  }

  private boolean insertTable(String tablename, String[][] table) {
    Database db = new Database(tablename);
    ArrayList<String> columnName = new ArrayList<String>();
    ArrayList value = new ArrayList();

    for (int j = 0; j < table[0].length; j++) {
      columnName.add(table[0][j]);
    }
    for (int i = 1; i < table.length; i++) {
      for (int j = 0; j < table[i].length; j++) {
        value.add(table[i][j]);
      }
      if (!db.insert(columnName, value)) {
        return false;
      }
      value.clear();
    }
    return true;
  }

  @Override
  public String execute() {
    String result = "success";
    boolean flag = true;
    WebText wt = grabTable();
    String tablename;
    for (int i = 0; i < wt.getTable().size() && flag; i++) {
      String[][] table = wt.getTable().get(i);
      tablename = insertSearchRecord();
      if (!createTable(tablename, table[0])) {
        flag = false;
      }
      if (!insertTable(tablename, table)) {
        flag = false;
      }
    }
    if (!flag) {
      result = "failure";
    }
    /*
     * ArrayList<String[][]> table = wt.getTable(); for (int i = 0; i < table.size(); i++) {
     * System.out.println("table No." + (i + 1)); for (int j = 0; j < table.get(i).length; j++) {
     * for (int k = 0; k < table.get(i)[j].length; k++) { System.out.print(table.get(i)[j][k] +
     * "|\t"); } System.out.println(); } }
     */
    return result;
  }

  public static void main(String args[]) {
    Save s = new Save();
    s.setUsername("lyx");
    //http://www.w3school.com.cn/html/html_tables.asp
    //http://software.hit.edu.cn/dsb.html
    s.setUrl("http://software.hit.edu.cn/dsb.html");
    s.execute();
  }

}
