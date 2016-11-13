package net.kuangmeng;

public class ConstPost{
     private String tablename;
     private int num;
     private String[] col;
	
     ConstPost(){
    	 col = new String[100];
     }
     public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String[] getCol() {
		return col;
	}
	public void setCol(String[] col) {
		this.col = col;
	}
}
