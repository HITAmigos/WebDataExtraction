package net.kuangmeng.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.opensymphony.xwork2.ActionSupport;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import net.kuangmeng.Const;

@SuppressWarnings("serial")
public class ExcelAction extends ActionSupport{
	Const c=new Const();
    final String DB_URL = c.getDB_URL();
    final String USER = c.getUSER();
    final String PASS = c.getPASS();
   	private String tablename;
	 public String execute() throws Exception {
		 Connection conn = null;
		 Statement stmt = null;
		 try{
			 Class.forName("com.mysql.jdbc.Driver").newInstance();
		     conn = DriverManager.getConnection(DB_URL,USER,PASS);
		     stmt = conn.createStatement();
		     String sqlupdate = "select * from `" + tablename+"`";  
		     ResultSet rs=stmt.executeQuery(sqlupdate);
		     List<String> list=new ArrayList<String>();
		     int num = rs.getMetaData().getColumnCount();
		     int coltag[]=new int[100];
		     while(rs.next()){
		    	 if(rs.getInt(1)==1){
		    		 for(int i=3;i<num;i++){
		    			 coltag[i]=Integer.parseInt(rs.getString(i));
		    		 }
		    	 }else{
		    		 if(Integer.parseInt(rs.getString(2))==0){
		    			 for(int i=3;i<num;i++){
		    				 if(coltag[i]==0){
		    					 list.add(rs.getString(i));
		    				 }
		    			 }
		    		 }
		    	 }
		     }
		     //调用方法
		     String filename="C:\\Users\\Meng Kuang\\Desktop\\text.xls";
		     exportExcel(filename,list,num);
		     return SUCCESS;
		 }catch(SQLException s){
			   return ERROR;
		 }catch(Exception e){
			   return ERROR;
		 }
	   }
	public String getTablename(){
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public static void exportExcel(String fileName,List<String> content,int num) {
	        WritableWorkbook wwb;
	        FileOutputStream fos;
	        try {    
	            fos = new FileOutputStream(fileName);
	            wwb = Workbook.createWorkbook(fos);
	            WritableSheet ws = wwb.createSheet("Up2U表格导出", 1);        // 创建一个工作表
	            //    设置单元格的文字格式
	            WritableFont wf = new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD,false,
	                    UnderlineStyle.NO_UNDERLINE,Colour.BLUE);
	            WritableCellFormat wcf = new WritableCellFormat(wf);
	            wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
	            wcf.setAlignment(Alignment.CENTRE);
	            ws.setRowView(1, 500);
	            //    填充数据的内容
	            for (int i = 0; i <content.size()/num; i++){
	            	for(int j=0;j<num-2;j++){
	            		 ws.addCell(new Label(i+1, j + 1,content.get(i*(content.size()/num)+j+1), wcf));
	            	}
	            if(i == 0)
	            		wcf = new WritableCellFormat();
	            }
	            wwb.write();
	            wwb.close();

	        } catch (IOException e){
	        } catch (RowsExceededException e){
	        } catch (WriteException e){}
	    }
}