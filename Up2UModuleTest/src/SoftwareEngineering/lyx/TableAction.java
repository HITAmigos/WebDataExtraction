package SoftwareEngineering.lyx;

public class TableAction {

  protected String Username;
  protected String Password;
  protected String RePassword;
  protected String Url;
  protected String[][] Table;

  public String[][] getTable() {
    return Table;
  }

  public void setTable(String[][] table) {
    Table = table;
  }

  public String getRePassword() {
    return RePassword;
  }

  public void setRePassword(String rePassword) {
    RePassword = rePassword;
  }

  public String getUrl() {
    return Url;
  }

  public void setUrl(String url) {
    Url = url;
  }

  public String getUsername() {
    return Username;
  }

  public void setUsername(String username) {
    Username = username;
  }

  public String getPassword() {
    return Password;
  }

  public void setPassword(String password) {
    Password = password;
  }

  public String execute() {
    return "other";
  }
}
