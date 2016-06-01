package cn.jbit.daoimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import cn.jbit.dao.BaseDao;
import cn.jbit.dao.easybuy_userDao;
import cn.jbit.entity.easybuy_user;

/**
 * @author 任锯东
 */
@Repository
public class easybuy_userDaoImpl extends BaseDao implements easybuy_userDao {

	//创建集合对象将easybuy_user表放进List集合中
	List<easybuy_user> list=new ArrayList<easybuy_user>();
	/**
	 * 功能:判断用户是否存在
	 */
	@Override
	public boolean getUser(String eu_user_id) {
		//标识变量
		boolean flag=false;
		//sql语句
		String sql = "select * from easybuy_user where eu_user_id=?";
		try {
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps=con.prepareStatement(sql);
			ps.setString(1, eu_user_id);
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
	/**
	 * 功能:用户注册
	 */
	@Override
	public int addUser(easybuy_user eu) {
		// TODO Auto-generated catch block
		//sql语句
		String sql="insert into easybuy_user values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		//创建一个List集合
		List<Object> list = new ArrayList<Object>();
		list.add(eu.getEu_User_id());
		list.add(eu.getEu_User_name());
		list.add(eu.getEu_Password());
		list.add(eu.getEu_Sex());
		list.add(eu.getEu_Birthday());
		list.add(eu.getEu_Identity_code());
		list.add(eu.getEu_Email());
		list.add(eu.getEu_Mobile());
		list.add(eu.getEu_Address());
		list.add(eu.getEu_Login());
		list.add(eu.getEu_Status());
		list.add(eu.getEu_question());
		list.add(eu.getEu_answer());
		list.add(eu.getEu_question1());
		list.add(eu.getEu_answer1());
		list.add(eu.getEu_question2());
		list.add(eu.getEu_answer2());
		list.add(eu.getEu_Create_time());
		list.add(eu.getEu_Cost());
		list.add(eu.getEu_Score());
		return this.executeUpdata(sql, list);
	}
	/**
	 * 功能:分页获取所有用户信息
	 */
	@Override
	public List<easybuy_user> getAllUser(int cpage, int pageSize,String eu_user_id) {
		//sql语句
		/*stuff(eu_mobile,3,6,'******') as eu_mobile*/
		String sql = "select top "+pageSize+" eu_user_id,eu_user_name,eu_password,eu_sex,eu_birthday,eu_identity_code,eu_email,eu_mobile,eu_address,eu_login,eu_status,sum(eu_score) as eu_score from easybuy_user where eu_user_id like ? and  eu_user_id not in(select top "+(cpage-1)*pageSize+" eu_user_id from easybuy_user group by eu_user_id,eu_user_name,eu_password,eu_sex,eu_birthday,eu_identity_code,eu_email,eu_mobile,eu_address,eu_login,eu_status) group by eu_user_id,eu_user_name,eu_password,eu_sex,eu_birthday,eu_identity_code,eu_email,eu_mobile,eu_address,eu_login,eu_status";
		try {
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps=con.prepareStatement(sql);
			ps.setString(1, "%"+eu_user_id+"%");
			rs=ps.executeQuery();
			while (rs.next()) {
				easybuy_user user=new easybuy_user();
				user.setEu_User_id(rs.getString("eu_user_id"));
				user.setEu_User_name(rs.getString("eu_user_name"));
				user.setEu_Password(rs.getString("eu_password"));
				user.setEu_Sex(rs.getString("eu_sex"));
				user.setEu_Birthday(rs.getString("eu_birthday"));
				user.setEu_Identity_code(rs.getString("eu_identity_code"));
				user.setEu_Email(rs.getString("eu_email"));
				user.setEu_Mobile(rs.getString("eu_mobile"));
				user.setEu_Address(rs.getString("eu_address"));
				user.setEu_Login(rs.getInt("eu_login"));
				user.setEu_Status(rs.getInt("eu_status"));
				user.setEu_Score(rs.getFloat("eu_score"));
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally{
			//关闭数据库连接
			this.closeResource();
		}
		return list;
	}
	/**
	 * 功能:根据Id获取指定用户信息
	 * @param eu_user_id
	 */
	@Override
	public easybuy_user getEasyBuyUser(String eu_user_id) {
		// TODO Auto-generated method stub
		easybuy_user eu=new easybuy_user();
		//sql语句
		/*stuff(eu_mobile,3,6,'******') as */
		String sql = "select eu_user_id,eu_user_name,eu_sex,eu_birthday,eu_email,eu_mobile,eu_address,eu_login,eu_status from easybuy_user where eu_user_id=?";
		try {
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps=con.prepareStatement(sql);
			ps.setString(1, eu_user_id);
			rs=ps.executeQuery();
			if(rs.next()){
				eu.setEu_User_id(rs.getString("eu_user_id"));
				eu.setEu_User_name(rs.getString("eu_user_name"));
				eu.setEu_Sex(rs.getString("eu_sex"));
				/*	SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
				sf.format();*/
				eu.setEu_Birthday(rs.getString("eu_birthday"));
				eu.setEu_Email(rs.getString("eu_email"));
				eu.setEu_Mobile(rs.getString("eu_mobile"));
				eu.setEu_Address(rs.getString("eu_address"));
				eu.setEu_Login(rs.getInt("eu_login"));
				eu.setEu_Status(rs.getInt("eu_status"));
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
		return eu;
	}
	/**
	 * 功能:根据id删除用户
	 */
	@Override
	public int delUser(String eu_user_id) {
		// TODO Auto-generated method stub
		//sql语句
		String sql="delete from easybuy_user where eu_user_id='"+eu_user_id+"'";
		return this.executeSql(sql);
	}
	/**
	 * 功能:修改用户信息
	 */
	@Override
	public int updateUser(easybuy_user eu) {
		// TODO Auto-generated catch block
		//,eu_file_name=?
		//sql语句
		String sql="update easybuy_user set eu_user_name=?,eu_sex=?,eu_email=?,eu_birthday=?,eu_address=?,eu_login=? where eu_user_id=?";
		//创建一个List对象集合
		List<Object> list = new ArrayList<Object>();
		list.add(eu.getEu_User_name());
		list.add(eu.getEu_Sex());
		list.add(eu.getEu_Email());
		list.add(eu.getEu_Birthday());
		list.add(eu.getEu_Address());
		list.add(eu.getEu_Login());
		list.add(eu.getEu_User_id());
		//list.add(eu.getEu_File_name());
		return this.executeUpdata(sql, list);
	}
	/**
	 * 功能:用户登录
	 */
	@Override
	public boolean login(String eu_user_id, String eu_password) {
		//标识变量
		boolean flag=false;
		//sql语句
		String sql = "select * from easybuy_user where eu_user_id=? and eu_password=?";
		try {
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps=con.prepareStatement(sql);
			ps.setString(1, eu_user_id);
			ps.setString(2, eu_password);
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
		return flag;//返回结果
	}
	/**
	 * 功能:获取用户总数
	 */
	@Override
	public int getUserCount(String eu_user_id) {
		// TODO Auto-generated method stub
		//sql语句
		String sql = "select count(1) from easybuy_user where eu_user_id like '%"+eu_user_id+"%'";
		return this.getCount(sql);
	}

	/**
	 * 功能:获取用户本人信息
	 */
	@Override
	public List<easybuy_user> getMyInfo(String eu_user_id) {
		// TODO Auto-generated method stub
		//sql语句
		String sql = "select eu_user_id,eu_user_name,eu_password,eu_sex,eu_birthday,eu_identity_code,eu_email,eu_mobile,eu_address,eu_login,eu_status,sum(eu_score) as eu_score from easybuy_user where eu_user_id=? group by eu_user_id,eu_user_name,eu_password,eu_sex,eu_birthday,eu_identity_code,eu_email,eu_mobile,eu_address,eu_login,eu_status";
		try {
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps=con.prepareStatement(sql);
			ps.setString(1, eu_user_id);
			rs=ps.executeQuery();
			while (rs.next()) {
				easybuy_user user=new easybuy_user();
				user.setEu_User_id(rs.getString("eu_user_id"));
				user.setEu_User_name(rs.getString("eu_user_name"));
				user.setEu_Password(rs.getString("eu_password"));
				user.setEu_Sex(rs.getString("eu_sex"));
				user.setEu_Birthday(rs.getString("eu_birthday"));
				user.setEu_Identity_code(rs.getString("eu_identity_code"));
				user.setEu_Email(rs.getString("eu_email"));
				user.setEu_Mobile(rs.getString("eu_mobile"));
				user.setEu_Address(rs.getString("eu_address"));
				user.setEu_Login(rs.getInt("eu_login"));
				user.setEu_Status(rs.getInt("eu_status"));
				user.setEu_Score(rs.getFloat("eu_score"));
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally{
			//关闭数据库连接
			this.closeResource();
		}
		return list;
	}
	/**
	 * 功能:获取用户消费总数
	 */
	@Override
	public int getConsumeCount(String eu_user_id) {
		// TODO Auto-generated method stub
		//sql语句
		String sql = "select count(distinct eu_user_id) as eu_user_id from easybuy_order eo,easybuy_user eu where eo.eo_user_id=eu.eu_user_id and eu_user_id like '%"+eu_user_id+"%'";
		return this.getCount(sql);
	}
	/**
	 * 功能:分页获取用户消费信息
	 */
	@Override
	public List<easybuy_user> getConsumeAllUser(int cpage, int pageSize,String eu_user_id) {
		// TODO Auto-generated method stub
		//sql语句
		String sql = "select top "+pageSize+" eu_user_id,eu_user_name,eu_sex,eu_birthday,eu_mobile,sum(eo_cost) as eo_cost,sum(eo_score) as eo_score from easybuy_order eo,easybuy_user eu where eu.eu_user_id like ? and eo.eo_user_id=eu.eu_user_id and eu.eu_user_id not in(select top "+(cpage-1)*pageSize+" eu.eu_user_id from easybuy_order eo,easybuy_user eu group by eu_user_id,eu_user_name,eu_sex,eu_birthday,eu_mobile order by sum(eo_cost) desc) group by eu_user_id,eu_user_name,eu_sex,eu_birthday,eu_mobile order by sum(eo_cost) desc";
		try {
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps=con.prepareStatement(sql);
			ps.setString(1,"%"+eu_user_id+"%");
			rs=ps.executeQuery();
			while (rs.next()) {
				easybuy_user user=new easybuy_user();
				user.setEu_User_id(rs.getString("eu_user_id"));
				user.setEu_User_name(rs.getString("eu_user_name"));
				user.setEu_Sex(rs.getString("eu_sex"));
				user.setEu_Birthday(rs.getString("eu_birthday"));
				user.setEu_Mobile(rs.getString("eu_mobile"));
				user.setEo_Cost(rs.getFloat("eo_cost"));
				user.setEo_Score(rs.getFloat("eo_score"));
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally{
			//关闭数据库连接
			this.closeResource();
		}
		return list;
	}
	/**
	 * 功能:根据Id获取更多信息
	 * @param eu_user_id
	 */
	@Override
	public easybuy_user getSelectMore(String eu_user_id) {
		easybuy_user eu=new easybuy_user();
		//sql语句
		String sql = "select distinct eu_user_id,eu_email,eu_address,eu_create_time,sum(eo_cost) as eo_cost,sum(eo_score) as eo_score from easybuy_order eo,easybuy_user eu where eu_user_id=? and eo.eo_user_id=eu.eu_user_id group by eu_user_id,eu_email,eu_address,eu_create_time";
		try {
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps=con.prepareStatement(sql);
			ps.setString(1, eu_user_id);
			rs=ps.executeQuery();
			if(rs.next()){
				eu.setEu_User_id(rs.getString("eu_user_id"));
				eu.setEu_Email(rs.getString("eu_email"));
				eu.setEu_Address(rs.getString("eu_address"));
				eu.setEu_Create_time(rs.getString("eu_create_time"));
				eu.setEo_Cost(rs.getFloat("eo_cost"));
				eu.setEo_Score(rs.getFloat("eo_score"));
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
		return eu;
	}
	/**
	 * 功能:修改用户信息
	 */
	@Override
	public int SelectMores(easybuy_user eu) {
		// TODO Auto-generated catch block
		//sql语句
		String sql="update easybuy_user set eu_cost=?,eu_score=? where eu_user_id=?";
		//创建一个List对象集合
		List<Object> list = new ArrayList<Object>();
		list.add(eu.getEu_Cost());
		list.add(eu.getEu_Score());
		list.add(eu.getEu_User_id());
		return this.executeUpdata(sql, list);
	}
}
