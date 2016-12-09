package action.tableOperation;

import action.Action;
import entity.SearchRecordTable;

public class DeleteAllRecord extends Action{
  public String execute(){
    String result = "success";
    if(!SearchRecordTable.ClearAllRecords(username)){
      result = "failure";
    }
    return result;
  }
  
  public static void main(String args[]){
    DeleteAllRecord dar = new DeleteAllRecord();
    dar.setUsername("lll");
    dar.execute();
  }
  
}
