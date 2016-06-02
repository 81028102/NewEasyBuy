package cn.jbit.test;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;*/
import java.text.SimpleDateFormat;

public class GenLayeringAll {

	private String tablename = "easybuy_user_status";//例如:"emp";//表名
	private String authorName = "任锯东";//作者名字

	private String parse(String s1 ,String s2,String s3) {

		StringBuffer sb = new StringBuffer();
		String ss = s1.replaceAll("/", ".");

		sb.append("package " + ss + ";\r\n");
		sb.append("\r\n");
		//注释部分
		sb.append("/**\r\n");
		sb.append(" * "+"@author "+this.authorName+"  "+initcap(tablename)+"(分层)Layering\r\n");
		sb.append(" * "+"@date "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis())+"\r\n");
		sb.append(" */");
		//实体部分
		sb.append("\r\npublic "+ s3 +" " + initcap(tablename+s2) + "{\r\n");
		sb.append("\r\n");
		sb.append("}\r\n");

		System.out.println(sb.toString());
		return sb.toString();
	}

	public void test(String s1 ,String s2,String s3){
		String content = parse(s1,s2,s3);

		try {
			@SuppressWarnings("unused")
			File directory = new File("");
			String path=this.getClass().getResource("").getPath();

			System.out.println(path);
			System.out.println("src/?/"+path.substring(path.lastIndexOf("/cn/", path.length())) );
			//String outputPath = directory.getAbsolutePath()+ "/src/"+path.substring(path.lastIndexOf("/cn/", path.length()), path.length()) + initcap(tablename) + ".java";
			String outputPath = "src/"+s1+"/"+initcap(tablename+s2) + ".java";
			FileWriter fw = new FileWriter(outputPath);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(content);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			// TODO Auto-generated method stub
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 功能：将输入字符串的首字母改成大写
	 * @param str
	 * @return
	 */
	private String initcap(String str) {

		char[] ch = str.toCharArray();
		if(ch[0] >= 'a' && ch[0] <= 'z'){
			ch[0] = (char)(ch[0] - 32);
		}

		return new String(ch);
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long startTime = System.currentTimeMillis();//开始时间  返回以毫秒为单位的当前时间。注意，当返回值的时间单位是毫秒时，值的粒度取决于底层操作系统，并且粒度可能更大。
		System.out.println("------->>>开始时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime));
		//需要自己手动建包
		String[] packageOutPaths = {"cn/jbit/dao","cn/jbit/daoimpl","cn/jbit/biz","cn/jbit/bizimpl","cn/jbit/action"};
		String[] cs = {"Dao","DaoImpl","Biz","BizImpl","Action"};
		String[] type = {"interface","class","interface","class","class"};

		GenLayeringAll ge = new GenLayeringAll();
		for (int i = 0; i < cs.length; i++) {
			ge.test(packageOutPaths[i],cs[i],type[i]);
		}
		long endTime = System.currentTimeMillis();//结束时间
		System.out.println("------->>>结束时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime));
		long execTime=endTime-startTime;
		System.out.println("------->>>所用时间:"+execTime+" ms"); 
	}
}