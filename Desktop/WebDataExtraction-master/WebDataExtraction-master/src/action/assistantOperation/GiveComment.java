package action.assistantOperation;

import java.sql.Date;

import action.Action;
import entity.CommentTable;
import entity.assistantEntity.BeijingTime;
import entity.assistantEntity.Comment;

public class GiveComment extends Action {
  private int selection;
  private String message;

  public int getSelection() {
    return selection;
  }

  public void setSelection(int selection) {
    this.selection = selection;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String execute() {
    String result = "success";
    Date date = (Date) BeijingTime.getWebsiteDatetime();
    Comment comment = new Comment();
    comment.setUsername(username);
    comment.setSelection(selection);
    comment.setMessage(message);
    comment.setDate(date);
    if (!CommentTable.insert(comment)) {
      result = "error";
    }
    return result;
  }

}
