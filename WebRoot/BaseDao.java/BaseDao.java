package cn.jbit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;

/**
 * @author 任锯东
 */
public class BaseDao {

	protected Connection con = null;
	protected PreparedStatement ps = null;
	protected ResultSet rs = null;

	//获取数据库连接
	protected void openConnection(){
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/easybuy");
			con = ds.getConnection();
		} catch (NamingException e) {
			System.out.println(e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	//更新新据库
	public int executeUpdata(String sql, List<Object> list){		
		openConnection();//打开数据库连接
		try {
			ps = con.prepareStatement(sql);
			if(list == null)
				return ps.executeUpdate();
			int i = 1;
			for(Object obj:list){
				ps.setObject(i, obj);
				i++;
			}
			return ps.executeUpdate();
		} catch (Exception e) {		
			System.out.println(e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeResource();//关闭数据库连接
		}
		return 0;
	}

	//增加  删除   修改
	public int executeSql(String sql){
		int i=0;
		try {
			openConnection();//打开数据库连接
			ps = con.prepareStatement(sql);
			i=ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally{
			closeResource();//关闭数据库连接
		}
		return i;
	}
	//增加  删除   修改  加上事务
	public void TransactionSql(String[] sqls){
		try {
			openConnection();//打开数据库连接
			//开启事务
			con.setAutoCommit(false);
			for (String sql : sqls) {
				ps = con.prepareStatement(sql);
				ps.executeUpdate();
			}

			con.commit();//执行成功  提交事务
		} catch (Exception e) {
			//出现异常
			try {
				con.rollback();//回滚事务
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			closeResource();//关闭数据库连接
		}
	}
	//得到列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getList(Class clazz,String sql){
		List list=new ArrayList();
		Object obj=null;
		try {
			openConnection();//打开数据库连接
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			//得到表里面字段名称   //获取此 ResultSet 对象的列的编号、类型和属性。 
			ResultSetMetaData metaData = rs.getMetaData();
			//getColumnCount() 返回此 ResultSet 对象中的列数。
			int columnCount = metaData.getColumnCount();
			while(rs.next()){
				//装载
				//创建对象
				obj = clazz.newInstance();
				for (int i = 1; i <= columnCount; i++) {
					BeanUtils.copyProperty(obj, metaData.getColumnName(i), rs.getObject(i));
				}
				list.add(obj);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally{
			closeResource();//关闭数据库连接
		}
		return list;
	}
	//通过id查找对象
	@SuppressWarnings("rawtypes")
	public Object getObjectById(Class clazz,String sql){
		Object obj=null;
		try {
			openConnection();//打开数据库连接
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			//得到表里面字段名称   //获取此 ResultSet 对象的列的编号、类型和属性。 
			ResultSetMetaData metaData = rs.getMetaData();
			//getColumnCount() 返回此 ResultSet 对象中的列数。
			int columnCount = metaData.getColumnCount();
			while(rs.next()){
				//装载
				//创建对象
				obj = clazz.newInstance();
				for (int i = 1; i <= columnCount; i++) {
					BeanUtils.copyProperty(obj, metaData.getColumnName(i), rs.getObject(i));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally{
			closeResource();//关闭数据库连接
		}
		return obj;
	}
	//得到count
	public int getCount(String sql){
		int count=0;
		try {
			openConnection();//打开数据库连接
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				count=rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally{
			closeResource();//关闭数据库连接
		}
		return count;
	}
	//关闭数据库连接
	protected void closeResource(){		
		try {
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(con != null)
				con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}