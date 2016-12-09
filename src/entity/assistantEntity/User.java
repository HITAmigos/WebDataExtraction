package entity.assistantEntity;

public class User {
  private int id;
  private String username;
  private String password;
  private String email;
  private int limited;
  private int level;
  private int coins;
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
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public int getLimited() {
    return limited;
  }
  public void setLimited(int limited) {
    this.limited = limited;
  }
  public int getLevel() {
    return level;
  }
  public void setLevel(int level) {
    this.level = level;
  }
  public int getCoins() {
    return coins;
  }
  public void setCoins(int coins) {
    this.coins = coins;
  }

}
