package action.tableOperation;

import action.Action;
import entity.SearchRecordTable;

public class DeleteAllRecord extends Action{
  public String execute(){
    String result = "success";
    if(!SearchRecordTable.ClearAllRecords(username)){
      result = "error";
    }
    return result;
  }
}
