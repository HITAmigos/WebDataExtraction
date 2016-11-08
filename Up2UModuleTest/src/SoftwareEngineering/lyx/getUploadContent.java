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
   * �ı��ļ�ת��Ϊָ��������ַ���
   * 
   * @param file �ı��ļ�
   * @param Encoding ��������
   * @return ת������ַ���
   * @throws IOException
   */
  public static String toString(File file) {
    InputStreamReader reader = null;
    StringWriter writer = new StringWriter();
    try {
      reader = new InputStreamReader(new FileInputStream(file), Encoding);
      // ��������д�������
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
    // ����ת�����
    return writer.toString();

  }

  public static void main(String args[]){
//    ���캯��File(String pathname)
//    File f1 =new File("c:\\abc\\1.txt");
//    File(String parent,String child)
//    File f2 =new File("c:\\abc","2.txt");
//    File(File parent,String child)
//    File f3 =new File("c:"+File.separator+"abc");//separator ��ƽ̨�ָ���
//    File f4 =new File(f3,"3.txt");
//    System.out.println(f1);//c:\abc\1.txt
    File file = new File("D:\\xampp\\htdocs\\index.php");
    getUploadContent gpc = new getUploadContent();
    System.out.println(gpc.toString(file));
  }

}
