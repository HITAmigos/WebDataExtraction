package action.assistantOperation;

import entity.assistantEntity.*;

public class CommentBack {
	private String topic;
	private String date;
	private String message;
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	SqlConst c=new SqlConst();
    final String DB_URL = c.getDB_URL();
    final String USER = c.getUSER();
    final String PASS = c.getPASS();
  
	private String email;
	public String execute(){  
		System.out.println(email);
		System.out.println(message);
        //这个类主要是设置邮件  
     MailSenderInfo mailInfo = new MailSenderInfo();   
     mailInfo.setMailServerHost("smtp.yeah.net");   
     mailInfo.setMailServerPort("25");   
     mailInfo.setValidate(true);   
     mailInfo.setUserName("kuangmengmeng@yeah.net");   
     mailInfo.setPassword("k123456789K");//您的邮箱密码   
     mailInfo.setFromAddress("kuangmengmeng@yeah.net");   
     mailInfo.setToAddress(email);   
     mailInfo.setSubject(topic);  
     mailInfo.setContent("该邮件发送时间为:"+date+"\n邮件内容为："+message+"\n");
        //这个类主要来发送邮件  
     SimpleMailSender sms = new SimpleMailSender();  
        boolean state1=sms.sendTextMail(mailInfo);//发送文体格式   
        if(state1){
        	return "success";
        }else{
        	 @SuppressWarnings("static-access")
     	 boolean state2=sms.sendHtmlMail(mailInfo);//发送html格式 
          if(state2){
        	  return "success";
          }else{
        	  return "error";
          }
        }
   }
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}  
}