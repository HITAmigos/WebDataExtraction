package SoftwareEngineering.lyx;
/**
 * 改变单元的类.
 * @author liuyx.
 * 用于存放改变内容所在的位置和改变后的内容.
 */
public class ChangeUnit {
  private int[] position;
  private String change;

  public int[] getPosition() {
    return position;
  }
  public void setPosition(int[] position) {
    this.position = position;
  }
  public String getChange() {
    return change;
  }
  public void setChange(String change) {
    this.change = change;
  }
  
  ChangeUnit(){
    position = new int[2];
  }
}
