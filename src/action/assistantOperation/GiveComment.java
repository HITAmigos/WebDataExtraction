package action.assistantOperation;

import java.util.Date;

import entity.CommentTable;
import entity.assistantEntity.BeijingTime;
import entity.assistantEntity.Comment;

public class GiveComment {
  private String message;
  private String name;
  private String email;
  private String username;
  private Date date;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String execute() {
    String result = "success";
    date = (Date) BeijingTime.getWebsiteDatetime();
    Comment comment = new Comment();
    comment.setUsername(username);
    comment.setName(name);
    comment.setEmail(email);
    comment.setMessage(message);
    comment.setDate(date);
    if (!CommentTable.insert(comment)) {
      result = "error";
    }
    return result;
  }

}
