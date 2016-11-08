package net.kuangmeng.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Boolean;
import jxl.write.DateFormats;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import net.kuangmeng.Const;

@SuppressWarnings("serial")
public class ExcelAction extends ActionSupport{
	Const c=new Const();
    final String DB_URL = c.getDB_URL();
    final String USER = c.getUSER();
    final String PASS = c.getPASS();
   	private String tablename;
	 public String execute() throws Exception {
		 OutputStream os = null;
		 createExcel(os);
		 return SUCCESS; 
		
	   }
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public void createExcel(OutputStream os) throws WriteException,IOException{
        //创建工作薄
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        //创建新的一页
        WritableSheet sheet = workbook.createSheet("First Sheet",0);
        //创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
        Label xuexiao = new Label(0,0,"学校");
        sheet.addCell(xuexiao);
        Label zhuanye = new Label(1,0,"专业");
        sheet.addCell(zhuanye);
        Label jingzhengli = new Label(2,0,"专业竞争力");
        sheet.addCell(jingzhengli);
        
        Label qinghua = new Label(0,1,"清华大学");
        sheet.addCell(qinghua);
        Label jisuanji = new Label(1,1,"计算机专业");
        sheet.addCell(jisuanji);
        Label gao = new Label(2,1,"高");
        sheet.addCell(gao);
        
        Label beida = new Label(0,2,"北京大学");
        sheet.addCell(beida);
        Label falv = new Label(1,2,"法律专业");
        sheet.addCell(falv);
        Label zhong = new Label(2,2,"中");
        sheet.addCell(zhong);
        
        Label ligong = new Label(0,3,"北京理工大学");
        sheet.addCell(ligong);
        Label hangkong = new Label(1,3,"航空专业");
        sheet.addCell(hangkong);
        Label di = new Label(2,3,"低");
        sheet.addCell(di);
        
        //把创建的内容写入到输出流中，并关闭输出流
        workbook.write();
        workbook.close();
        os.close();
    }
    
}
