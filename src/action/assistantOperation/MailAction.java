package action.assistantOperation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import entity.assistantEntity.*;

public class MailAction {
	SqlConst c=new SqlConst();
    final String DB_URL = c.getDB_URL();
    final String USER = c.getUSER();
    final String PASS = c.getPASS();
    static final String tableName = "user"; 
	private String email;
	public String execute(){  
        //这个类主要是设置邮件  
     MailSenderInfo mailInfo = new MailSenderInfo();   
     mailInfo.setMailServerHost("smtp.yeah.net");   
     mailInfo.setMailServerPort("25");   
     mailInfo.setValidate(true);   
     mailInfo.setUserName("kuangmengmeng@yeah.net");   
     mailInfo.setPassword("k123456789K");//您的邮箱密码   
     mailInfo.setFromAddress("kuangmengmeng@yeah.net");   
     mailInfo.setToAddress(getEmail());   
     mailInfo.setSubject("你的Up2U密码");  
     String pw=SearchPassword(email);
     mailInfo.setContent("该邮箱绑定的账号的密码为:"+pw);
        //这个类主要来发送邮件  
     SimpleMailSender sms = new SimpleMailSender();  
        boolean state1=sms.sendTextMail(mailInfo);//发送文体格式   
        @SuppressWarnings("static-access")
		boolean state2=sms.sendHtmlMail(mailInfo);//发送html格式 
        if(state1 || state2){
        	return "success";
        }else{
        	return "error";
        }
   }
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}  
	public String SearchPassword(String email){
		 Connection conn = null;
		 Statement stmt = null;
		 try{
			   Class.forName("com.mysql.jdbc.Driver").newInstance();
		     conn = DriverManager.getConnection(DB_URL,USER,PASS);
		     stmt = conn.createStatement();
		     String sql = "SELECT * FROM " + tableName + " WHERE email = "+ "\'"+email+"\'";  
		     ResultSet rs = stmt.executeQuery(sql); 
		     if(rs.next()){
		    	 return rs.getString("password");
		     }else{
		    	 return null;
		     }
		 }catch(SQLException s){
			   return null;
		 }catch(Exception e){
			   return null;
		 }
	}
}