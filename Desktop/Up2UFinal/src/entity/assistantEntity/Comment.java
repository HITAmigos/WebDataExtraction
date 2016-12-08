package entity.assistantEntity;

import java.sql.Date;

public class Comment {
  private int id;
  private String username;
  private int selection;
  private String message;
  private Date date;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

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

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

}
