package net.kuangmeng;
public class Const {
	public static final String DB_URL = "jdbc:mysql://localhost:3306/graphgrabtest?useUnicode=true&characterEncoding=utf8&&useSSL=false";
    public static final String USER = "root";
    public static final String PASS = "123456789";
    private int id=0;
    public String getDB_URL(){
   	 return DB_URL;
    }
    public String getUSER(){
   	 return USER;
    }
    public String getPASS(){
   	 return PASS;
    }
    public int getId(){
   	 return id;
    }
    public void setId(int id){
   	 this.id=id+1;
    }
}