package action.assistantOperation;

import entity.Database;
import entity.SearchRecordTable;

public class DeleteTable {
  private String tablename;

  public String getTablename() {
    return tablename;
  }

  public void setTablename(String tablename) {
    this.tablename = tablename;
  }

  public String execute() {
    String result = "success";
    Database dbHelper = new Database(tablename);
    if (!SearchRecordTable.DeleteSearchRecord(SearchRecordTable.getSearchRecord(tablename))
        || !dbHelper.delete()) {
      result = "error";
    }
    return result;
  }
}
