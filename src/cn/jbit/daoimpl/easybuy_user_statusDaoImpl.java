package cn.jbit.daoimpl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import cn.jbit.dao.BaseDao;
import cn.jbit.dao.easybuy_user_statusDao;
import cn.jbit.entity.easybuy_user_status;

/**
 * @author 任锯东  Easybuy_user_status(分层)Layering
 */
public class easybuy_user_statusDaoImpl extends BaseDao implements easybuy_user_statusDao{

	//创建List集合将easybuy_user_status表放进List集合中
	List<easybuy_user_status> list=new ArrayList<easybuy_user_status>();
	/**
	 * 功能:模糊查询分页获取用户的所有状态名称
	 */
	@Override
	public List<easybuy_user_status> getUser_Status(int cpage, int pageSize,
			String eus_StatusName) {
		// TODO Auto-generated method stub
		try {
			//打开数据库连接
			this.openConnection();
			//sql语句创建一个prepareStatement对象
			ps=con.prepareStatement("select top "+pageSize+" * from easybuy_user_status where eus_statusname like ? and eus_id not in(select top "+(cpage-1)*pageSize+" eus_id from easybuy_user_status order by eus_id) order by eus_id");
			ps.setString(1,"%"+eus_StatusName+"%" );
			rs=ps.executeQuery();
			while(rs.next()){
				easybuy_user_status eus=new easybuy_user_status();
				eus.setEus_Id(rs.getInt("eus_id"));
				eus.setEus_StatusName(rs.getString("eus_statusname"));
				eus.setEus_Create_time(rs.getString("eus_create_time"));
				list.add(eus);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally{
			//关闭数据库连接
			this.closeResource();
		}
		return list;
	}

	/**
	 * 功能:获取状态的总数量
	 */
	@Override
	public int getUser_StatusCount(String eus_StatusName) {
		// TODO Auto-generated method stub
		//sql语句
		String sql="select count(1) from easybuy_user_status where eus_statusname like '%"+eus_StatusName+"%'";
		return this.getCount(sql);
	}

	/**
	 * 功能:根据Id删除状态
	 */
	@Override
	public int delUser_StatusById(String eus_Id) {
		// TODO Auto-generated method stub
		//sql语句
		String sql="delete from easybuy_user_status where eus_id in("+eus_Id+")";
		return this.executeSql(sql);
	}

	/**
	 * 功能:删除全部状态
	 */
	@Override
	public int delUser_StatusAll() {
		// TODO Auto-generated method stub
		//sql语句
		String sql="delete from easybuy_user_status";
		return this.executeSql(sql);
	}

	/**
	 * 功能:添加状态信息
	 */
	@Override
	public int addUser_Status(easybuy_user_status eus) {
		// TODO Auto-generated method stub
		//sql语句
		String sql="insert easybuy_user_status values(?,?)";
		//创建List集合对象
		List<Object> list = new ArrayList<Object>();
		list.add(eus.getEus_StatusName());
		list.add(eus.getEus_Create_time());
		return this.executeUpdata(sql, list);
	}

	/**
	 * 功能:获取指定ID状态
	 */
	@Override
	public easybuy_user_status getUser_StatusById(int eus_Id) {
		// TODO Auto-generated method stub
		easybuy_user_status eus=new easybuy_user_status();
		try {
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps=con.prepareStatement("select * from easybuy_user_status where eus_id=?");
			ps.setInt(1,eus_Id);
			rs=ps.executeQuery();
			while(rs.next()){
				eus.setEus_Id(rs.getInt("eus_id"));
				eus.setEus_StatusName(rs.getString("eus_statusname"));
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化时间
				eus.setEus_Create_time(sf.format(rs.getDate("eus_create_time")));
			}
		} catch (SQLException e){
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally{
			//关闭数据库连接
			this.closeResource();
		}
		return eus;
	}

	/**
	 * 功能:更新状态信息
	 */
	@Override
	public int updateUser_Status(easybuy_user_status eus) {
		// TODO Auto-generated method stub
		//sql语句
		String sql="update easybuy_user_status set eus_statusname=?,eus_create_time=? where eus_id=?";
		//创建List集合对象
		List<Object> list = new ArrayList<Object>();
		list.add(eus.getEus_StatusName());
		list.add(eus.getEus_Create_time());
		list.add(eus.getEus_Id());
		return this.executeUpdata(sql, list);
	}

	/**
	 * 功能:判断是否存在指定状态信息
	 */
	@Override
	public boolean boolStatus(String eus_StatusName) {
		// TODO Auto-generated method stub
		//标记一下
		boolean flag=false;
		//sql语句
		String sql = "select * from easybuy_user_status where eus_statusname=?";
		try {
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps = con.prepareStatement(sql);
			ps.setString(1, eus_StatusName);
			rs=ps.executeQuery();
			if(rs.next()){
				flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally{
			//关闭数据库连接
			this.closeResource();
		}
		return flag;
	}

}

