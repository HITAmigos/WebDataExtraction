package action;

public class Action {
  protected String username;
  protected String tablename;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getTablename() {
    return tablename;
  }

  public void setTablename(String tablename) {
    this.tablename = tablename;
  }

  public String execute() {
    return "failure";
  }
}
