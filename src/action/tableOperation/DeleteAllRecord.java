package action.tableOperation;

import action.Action;
import entity.Database;
import entity.SearchRecordTable;
import entity.assistantEntity.SearchRecord;

public class DeleteAllRecord extends Action{
  public String execute(){
    String result = "success";
    Database dbHelper = new Database();
    for(SearchRecord searchRecord:SearchRecordTable.getUserSearchRecord(username)){
      dbHelper.setTablename(searchRecord.getTablename());
      dbHelper.delete();
    }
    if(!SearchRecordTable.ClearAllRecords(username)){
      result = "error";
    }
    return result;
  }
}
