package SoftwareEngineering.lyx;
/**
 * �ı䵥Ԫ����.
 * @author liuyx.
 * ���ڴ�Ÿı��������ڵ�λ�ú͸ı�������.
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
