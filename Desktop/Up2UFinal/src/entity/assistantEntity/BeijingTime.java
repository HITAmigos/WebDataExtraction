package entity.assistantEntity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BeijingTime {
  // private static String webUrl = "http://www.bjtime.cn";//bjTime
  private static String webUrl = "http://www.baidu.com";// 百度
  // private static String webUrl = "http://www.taobao.com";//淘宝
  // private static String webUrl = "http://www.ntsc.ac.cn";//中国科学院国家授时中心
  // private static String webUrl = "http://www.360.cn";//360
  // private static String webUrl = "http://www.beijing-time.org";//beijing-time

  /**
   * 获取指定网站的日期时间
   * 
   * @param webUrl
   * @return
   **/
  public static Date getWebsiteDatetime() {
    Date date = null;
    try {
      URL url = new URL(webUrl);// 取得资源对象
      URLConnection uc = url.openConnection();// 生成连接对象
      uc.connect();// 发出连接
      long ld = uc.getDate();// 读取网站日期时间
      date = new Date(ld);// 转换为标准时间对象
      // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);// 输出北京时间
      return date;
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return date;
  }

  public static void main(String args[]) throws IOException {
    System.out.println(BeijingTime.getWebsiteDatetime());
  }

}
