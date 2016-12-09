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
  private String url;
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
      temp.setColumnType("varchar(" + (colMaxLength[j] * 2) + ")");
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

  @SuppressWarnings("unchecked")
private boolean insertTable(String tablename, String[][] table) {
    Database db = new Database(tablename);
    ArrayList<String> columnName = new ArrayList<String>();
    @SuppressWarnings("rawtypes")
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

  public String execute(){
    String result = "success";
    boolean flag = true;
    WebText wt = grabTable();
    String tablename;
    int[] colMaxLength = null;
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
    if (!flag) {
      result = "error";
    }
    return result;
  }
  }
