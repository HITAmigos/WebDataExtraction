package net.kuangmeng.excel;

import java.io.File;
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
		     List<String> listth=new ArrayList<String>();
		     List<String> listtd=new ArrayList<String>();
		     int num = rs.getMetaData().getColumnCount();
		     int coltag[]=new int[100];
		     while(rs.next()){
		    	 if(rs.getInt("id")==1){
		    		 for(int i=3;i<=num;i++){
		    			 coltag[i]=Integer.parseInt(rs.getString(i));
		    		 }
		    	 }else{
		    		 if(Integer.parseInt(rs.getString(2))==1 || Integer.parseInt(rs.getString(2))==101){
		    			 for(int i=3;i<=num;i++){
		    				 if(coltag[i]==0 || coltag[i]==10){
		    					 listth.add(rs.getString(i));
		    				 }
		    			 }
		    		 }else if(Integer.parseInt(rs.getString(2))==0 || Integer.parseInt(rs.getString(2))==100){
		    			 for(int i=3;i<=num;i++){
		    				 if(coltag[i]==0 || coltag[i]==10){
		    					 listtd.add(rs.getString(i));
		    				 }
		    			 }
		    		 }
		    	 }
		     }
		     //调用方法
		     System.out.println(listth);
		     System.out.println(listtd);
		     System.out.println(listth.size());
		     System.out.println(listtd.size());
		     System.out.println(num);
		     exportExcel(tablename,listth,listtd,num);
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
	public static void exportExcel(String fileName,List<String> listth,List<String> listtd,int num) {
		 String excelName =fileName+".xls";
		  try {
			  
			  
			  
		   File excelFile = new File(excelName);
		   // 如果文件存在就删除它
		   if (excelFile.exists())
		    excelFile.delete();
		   // 打开文件
		   WritableWorkbook book = Workbook.createWorkbook(excelFile);
		   // 生成名为“第一页”的工作表，参数0表示这是第一页
		   WritableSheet sheet = book.createSheet("Up2U导出表格 ", 0);
		   // 合并单元格
		   //sheet.mergeCells(5, 5, 6, 6);
		   // 文字样式
		   jxl.write.WritableFont wfc = new jxl.write.WritableFont(
		     WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false,
		     UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
		   jxl.write.WritableCellFormat wcfFC = new jxl.write.WritableCellFormat(
		     wfc);
		   jxl.write.WritableCellFormat wcfF = new jxl.write.WritableCellFormat(wfc);
		   wcfF.setBackground(jxl.format.Colour.BLACK);
		   // 设置单元格样式
		   wcfFC.setBackground(jxl.format.Colour.GRAY_25);// 单元格颜色
		   wcfFC.setAlignment(jxl.format.Alignment.CENTRE);// 单元格居中

		   // 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
		   // 以及单元格内容为
		   for(int i=0;i<listth.size()/(num-2);i++){
			   for(int j=0;j<num-2;j++){
				   sheet.addCell(new Label(j,i,listth.get(i*(num-2)+j),wcfFC));
			   }
		   }
		   for(int i=listth.size()/(num-2);i<(listth.size()+listtd.size())/(num-2);i++){
			   for(int j=0;j<num-2;j++){
				   sheet.addCell(new Label(j,i,listtd.get((i-1)*(num-2)+j),wcfF));
			   }
		   }
		  // 写入数据并关闭文件
		   book.write();
		   book.close();
		   System.out.println("Excel创建成功");
		  } catch (Exception e) {
		   System.out.println(e);
		  }
		 }
}