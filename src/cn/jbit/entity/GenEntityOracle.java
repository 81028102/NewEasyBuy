package cn.jbit.entity;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;

public class GenEntityOracle {

	private String packageOutPath = "cn.jbit.entity";//指定实体生成所在包的路径
	private String authorName = "任锯东";//作者名字
	private String tablename = "emp";//表名 (可以添加多个表)
	private String[] colnames; // 列名数组
	private String[] colTypes; //列名类型数组
	private int[] colSizes; //列名大小数组
	private boolean f_util = false; // 是否需要导入包java.util.*
	private boolean f_sql = false; // 是否需要导入包java.sql.*

	//Oracle数据库连接
	private static final String driver ="oracle.jdbc.driver.OracleDriver";// 数据库驱动字符串
	private static final String url ="jdbc:oracle:thin:@localhost:1521:ORCL";// 连接URL字符串
	private static final String user = "scott";// 数据库用户名
	private static final String password = "orcl";// 用户密码
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	//创建数据库连接
	public Connection getConnection(){

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return conn;
	}
	//关闭数据库连接
	public void closeAll(ResultSet rs,PreparedStatement pstmt,Connection conn){

		try {
			if(rs!=null){
				rs.close();
			}
			if(pstmt!=null){
				pstmt.close();
			}
			if(conn!=null){
				conn.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 构造函数
	 */
	public GenEntityOracle(){

		try {
			//打开数据库连接
			conn = getConnection();
			//查看要生成实体类的表 sql语句
			String sql = "select * from " + tablename;
			//创建一个prepareStatement对象
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int size = rsmd.getColumnCount();	//统计列
			colnames = new String[size];
			colTypes = new String[size];
			colSizes = new int[size];
			for (int i = 0; i < size; i++) {
				colnames[i] = rsmd.getColumnName(i + 1);
				colTypes[i] = rsmd.getColumnTypeName(i + 1);

				if(colTypes[i].equalsIgnoreCase("date") || colTypes[i].equalsIgnoreCase("timestamp")){
					f_util = true;
				}
				if(colTypes[i].equalsIgnoreCase("blob") || colTypes[i].equalsIgnoreCase("char")){
					f_sql = true;
				}
				colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
			}

			String content = parse(colnames,colTypes,colSizes);

			try {
				File directory = new File("");
				//System.out.println("绝对路径："+directory.getAbsolutePath());
				//System.out.println("相对路径："+directory.getCanonicalPath());
				String path=this.getClass().getResource("").getPath();

				System.out.println(path);
				System.out.println("src/?/"+path.substring(path.lastIndexOf("/cn/", path.length())) );
				//String outputPath = directory.getAbsolutePath()+ "/src/"+path.substring(path.lastIndexOf("/cn/", path.length()), path.length()) + initcap(tablename) + ".java";
				String outputPath = directory.getAbsolutePath()+ "/src/"+this.packageOutPath.replace(".", "/")+"/"+initcap(tablename) + ".java";
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

		} catch (Exception e) {
			// TODO Auto-generated method stub
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally{
			closeAll(rs, pstmt, conn);//关闭数据库连接
		}
	}

	/**
	 * 功能:生成实体类主体代码
	 * @param colnames
	 * @param colTypes
	 * @param colSizes
	 * @return
	 */
	private String parse(String[] colnames, String[] colTypes, int[] colSizes) {
		StringBuffer sb = new StringBuffer();


		sb.append("package " + this.packageOutPath + ";\r\n");
		sb.append("\r\n");
		//判断是否导入工具包
		if(f_util){
			sb.append("import java.util.Date;\r\n");
		}
		if(f_sql){
			sb.append("import java.sql.*;\r\n");
		}

		sb.append("\r\n");
		//注释部分
		sb.append("/**\r\n");
		sb.append(" * "+"@author "+this.authorName+"  "+initcap(tablename)+"实体(Entity)类\r\n");
		sb.append(" * "+"@date "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis())+"\r\n");
		sb.append(" */");
		//实体部分
		sb.append("\r\npublic class " + initcap(tablename) + "{\r\n");
		sb.append("\r\n");
		processAllAttrs(sb);//属性
		sb.append("\r\n");
		processAllMethod(sb);//get set方法
		sb.append("}\r\n");

		//System.out.println(sb.toString());
		return sb.toString();
	}

	/**
	 * 功能:生成所有属性
	 * @param sb
	 */
	private void processAllAttrs(StringBuffer sb) {

		for (int i = 0; i < colnames.length; i++) {
			sb.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " " + colnames[i].toLowerCase() + ";\r\n");
		}

	}

	/**
	 * 功能:生成所有方法
	 * @param sb
	 */
	private void processAllMethod(StringBuffer sb) {

		for (int i = 0; i < colnames.length; i++) {
			sb.append("\tpublic void set" + initcap(colnames[i].toLowerCase()) + "(" + sqlType2JavaType(colTypes[i]) + " " + 
					colnames[i].toLowerCase() + "){\r\n");
			sb.append("\t\tthis." + colnames[i].toLowerCase() + "=" + colnames[i].toLowerCase() + ";\r\n");
			sb.append("\t}\r\n");
			sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get" + initcap(colnames[i].toLowerCase()) + "(){\r\n");
			sb.append("\t\treturn " + colnames[i].toLowerCase() + ";\r\n");
			sb.append("\t}\r\n");
		}

	}

	/**
	 * 功能:将输入字符串的首字母改成大写
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

	/**
	 * 功能:获得列的数据类型
	 * @param sqlType
	 * @return
	 */
	private String sqlType2JavaType(String sqlType) {

		if(sqlType.equalsIgnoreCase("binary_double")){
			return "double";
		}else if(sqlType.equalsIgnoreCase("binary_float")){
			return "float";
		}else if(sqlType.equalsIgnoreCase("blob")){
			return "byte[]";
		}else if(sqlType.equalsIgnoreCase("blob")){
			return "byte[]";
		}else if(sqlType.equalsIgnoreCase("char") || sqlType.equalsIgnoreCase("nvarchar2") 
				|| sqlType.equalsIgnoreCase("varchar2")){
			return "String";
		}else if(sqlType.equalsIgnoreCase("date") || sqlType.equalsIgnoreCase("timestamp")
				|| sqlType.equalsIgnoreCase("timestamp with local time zone") 
				|| sqlType.equalsIgnoreCase("timestamp with time zone")){
			return "Date";
		}else if(sqlType.equalsIgnoreCase("number")){
			return "Integer";
		}

		return "String";
	}

	/**
	 * 出口
	 * TODO
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		long startTime = System.currentTimeMillis();//开始时间  返回以毫秒为单位的当前时间。注意，当返回值的时间单位是毫秒时，值的粒度取决于底层操作系统，并且粒度可能更大。
		System.out.println("------->>>开始时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime));
		new GenEntityOracle();
		long endTime = System.currentTimeMillis();//结束时间
		System.out.println("------->>>结束时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime));
		long execTime=endTime-startTime;
		System.out.println("------->>>所用时间:"+execTime+" ms");
	}
}