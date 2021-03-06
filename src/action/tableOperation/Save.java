package action.tableOperation;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionContext;

import action.Action;
import entity.*;
import entity.assistantEntity.*;

public class Save extends Action {
  private String url = null;
  private int type = 0;
  private File myFile = null;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public File getMyFile() {
    return myFile;
  }

  public void setMyFile(File myFile) {
    this.myFile = myFile;
  }

  private boolean IsExistSearchRecord() {
    return SearchRecordTable.UrlIsExist(username, url);
  }

  private WebText grabTable() {
    ContentReader cr = new ContentReader();
    WebText wt = new WebText();
    cr.setUrl(url);
    if (cr.varifyInput().equals("upload")) {
      cr.setFile(myFile);
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
    if (url == null) {
      url = new String(tablename);
    }
    searchRecord.setUsername(username);
    searchRecord.setTablename(tablename);
    searchRecord.setLink(url);
    searchRecord.setType(type);
    searchRecord.setDate(BeijingTime.getWebsiteDatetime());
    SearchRecordTable.insert(searchRecord);
    return tablename;
  }

  private boolean createTable(String tablename, String[] tableTitle, int[] colMaxLength) {
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
      if (colMaxLength[j] * 2 <= 100) {
        temp.setColumnType("varchar(100)");
      } else {
        temp.setColumnType("varchar(" + (colMaxLength[j] * 2) + ")");
      }
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

    for (int j = 1; j < table[0].length; j++) {
      columnName.add(table[0][j]);
    }
    for (int i = 1; i < table.length; i++) {
      for (int j = 1; j < table[i].length; j++) {
        value.add(table[i][j]);
      }
      if (!db.insert(columnName, value)) {
        return false;
      }
      value.clear();
    }
    return true;
  }

  private boolean accumulateCoins() {
    int coins = 0;
    if(url.equals(tablename)){
      coins = 10;
    }else{
      coins = 3;
    }
    return UserTable.accumulateCoins(username, coins);
  }

  @Override
  public String execute() {
    String result = "success";
    System.out.println(url);
    if (IsExistSearchRecord()) {
      result = "exist";
      System.out.println(result);
    } else {
      boolean flag = true;
      WebText wt = grabTable();
      String tablename;
      int[] colMaxLength = null;
      if (wt.getTable().size() == 0) {
        result = "error";
      } else {
        for (int i = 0; i < wt.getTable().size() && flag; i++) {
          String[][] table = wt.getTable().get(i);
          tablename = insertSearchRecord();
          colMaxLength = new int[table[0].length];
          for (int m = 0; m < table.length; m++) {
            for (int n = 0; n < table[0].length; n++) {
              if (colMaxLength[n] < table[m][n].length()) {
                colMaxLength[n] = table[m][n].length();
              }
            }
          }
          if (!createTable(tablename, table[0], colMaxLength)) {
            flag = false;
          }
          if (!insertTable(tablename, table)) {
            flag = false;
          }
        }
      }
      if (!flag) {
        result = "error";
      } else {
        if (!accumulateCoins()) {
          result = "exist";
        }
      }
    }
    System.out.println(result);
    Map request = (Map)ActionContext.getContext().get("request");
    request.put("url", url);
    return result;
  }


  public static void main(String args[]) {
    Save s = new Save();
    s.setUsername("lyx");
    // http://www.w3school.com.cn/html/html_tables.asp
    // http://software.hit.edu.cn/dsb.html
    s.setUrl("http://www.jq22.com/demo/mmGrid-master20150916/examples/index.html");
    s.execute();
  }

}
