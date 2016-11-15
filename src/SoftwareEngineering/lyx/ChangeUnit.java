package SoftwareEngineering.lyx;
/**
 * 改变的单元类.
 * @author liuyx
 * 用于存放改变内容所在的位置和改变后的内容
 */
public class ChangeUnit {
  private int[] position;
  private String change;
  ChangeUnit(){
    position = new int[2];
  }
}
