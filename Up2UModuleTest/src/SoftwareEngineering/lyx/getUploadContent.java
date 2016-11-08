package SoftwareEngineering.lyx;

import java.io.*;

public class getUploadContent {
  private File file;
  private static String Encoding = "utf-8";

  public File getFile() {
    return file;
  }

  public void setFile(File file) {
    this.file = file;
  }

  /**
   * 文本文件转换为指定编码的字符串
   * 
   * @param file 文本文件
   * @param Encoding 编码类型
   * @return 转换后的字符串
   * @throws IOException
   */
  public static String toString(File file) {
    InputStreamReader reader = null;
    StringWriter writer = new StringWriter();
    try {
      reader = new InputStreamReader(new FileInputStream(file), Encoding);
      // 将输入流写入输出流
      char[] buffer = new char[1000];
      int pos = 0;
      while (-1 != (pos = reader.read(buffer))) {
        writer.write(buffer, 0, pos);
      }
    } catch (Exception e) {
      e.printStackTrace();
      writer = null;
    } finally {
      if (reader != null)
        try {
          reader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
    }
    // 返回转换结果
    return writer.toString();

  }

  public static void main(String args[]){
//    构造函数File(String pathname)
//    File f1 =new File("c:\\abc\\1.txt");
//    File(String parent,String child)
//    File f2 =new File("c:\\abc","2.txt");
//    File(File parent,String child)
//    File f3 =new File("c:"+File.separator+"abc");//separator 跨平台分隔符
//    File f4 =new File(f3,"3.txt");
//    System.out.println(f1);//c:\abc\1.txt
    File file = new File("D:\\xampp\\htdocs\\index.php");
    getUploadContent gpc = new getUploadContent();
    System.out.println(gpc.toString(file));
  }

}
