package cn.jbit.daoimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import cn.jbit.dao.BaseDao;
import cn.jbit.dao.easybuy_forgetDao;
import cn.jbit.entity.easybuy_user;

/**
 * @author 任锯东
 */
@Repository
public class easybuy_forgetDaoImpl extends BaseDao implements easybuy_forgetDao {

	/**
	 * 功能:根据Id获取指定用户信息
	 */
	@Override
	public String getEasyBuyQuestion(String eu_user_id) {
		// TODO Auto-generated method stub
		String result="";
		try {
			//sql语句
			String sql="select eu_question from easybuy_user where eu_user_id=?";
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps=con.prepareStatement(sql);
			ps.setString(1, eu_user_id);
			rs=ps.executeQuery();
			if(rs.next()){
				result=rs.getString(1);//获取第一列数据
			}else{
				result="您输入的用户名不存在!";//表示输入的用户名不存在
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			result="您输入的用户名不存在!";//表示输入的用户名不存在
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			//关闭数据库连接
			this.closeResource();
		}
		return result;
	}
	/**
	 * 功能:根据Id获取指定用户信息
	 */
	@Override
	public String getEasyBuyQuestion1(String eu_user_id) {
		// TODO Auto-generated method stub
		String result="";
		try {
			//sql语句
			String sql="select eu_question1 from easybuy_user where eu_user_id=?";
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps=con.prepareStatement(sql);
			ps.setString(1, eu_user_id);
			rs=ps.executeQuery();
			if(rs.next()){
				result=rs.getString(1);//获取第一列数据
			}else{
				result="您输入的用户名不存在!";//表示输入的用户名不存在
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			result="您输入的用户名不存在!";//表示输入的用户名不存在
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			//关闭数据库连接
			this.closeResource();
		}
		return result;
	}

	/**
	 * 功能:根据Id获取指定用户信息
	 */
	@Override
	public String getEasyBuyQuestion2(String eu_user_id) {
		// TODO Auto-generated method stub
		String result="";
		try {
			//sql语句
			String sql="select eu_question2 from easybuy_user where eu_user_id=?";
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps=con.prepareStatement(sql);
			ps.setString(1, eu_user_id);
			rs=ps.executeQuery();
			if(rs.next()){
				result=rs.getString(1);//获取第一列数据
			}else{
				result="您输入的用户名不存在!";//表示输入的用户名不存在
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			result="您输入的用户名不存在!";//表示输入的用户名不存在
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			//关闭数据库连接
			this.closeResource();
		}
		return result;
	}
	/**
	 * 功能:用于判断提示问题答案是否正确
	 */
	@Override
	public String getEasyBuyAnswer(String eu_user_id, String eu_question,
			String eu_answer,String eu_question1, String eu_answer1, String eu_question2, String eu_answer2) {
		// TODO Auto-generated method stub
		String result="";
		try {
			//sql语句
			String sql="select eu_password from easybuy_user where eu_user_id=? and eu_question=? and eu_answer=? and eu_question1=? and eu_answer1=? and eu_question2=? and eu_answer2=?";
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps=con.prepareStatement(sql);
			ps.setString(1, eu_user_id);
			ps.setString(2, eu_question);
			ps.setString(3, eu_answer);
			ps.setString(4, eu_question1);
			ps.setString(5, eu_answer1);
			ps.setString(6, eu_question2);
			ps.setString(7, eu_answer2);
			rs=ps.executeQuery();
			if(rs.next()){
				result=rs.getString(1);//获取第一列数据
			}else{
				result="您输入的提示问题答案错误!";//表示输入的用户名不存在
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			//关闭数据库连接
			this.closeResource();
		}
		return result;
	}

	/**
	 * 功能:根据问题答案修改用户密码
	 */
	@Override
	public int updateForget(easybuy_user eu) {
		//sql语句
		String sql="update easybuy_user set eu_password=? where eu_answer=? and eu_answer1=? and eu_answer2=?";
		//创建一个List对象集合
		List<Object> list = new ArrayList<Object>();
		list.add(eu.getEu_Password());
		list.add(eu.getEu_answer());
		list.add(eu.getEu_answer1());
		list.add(eu.getEu_answer2());
		return this.executeUpdata(sql, list);
	}
	/**
	 * 功能:根据用户名查询手机号
	 */
	@Override
	public String getMobile(String eu_user_id) {
		// TODO Auto-generated method stub
		String result="";
		try {
			//sql语句
			String sql="select eu_mobile from easybuy_user where eu_user_id=?";
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps=con.prepareStatement(sql);
			ps.setString(1, eu_user_id);
			rs=ps.executeQuery();
			if(rs.next()){
				result=rs.getString(1);//获取第一列数据
			}else{
				result="您输入的用户名不存在!";//表示输入的用户名不存在
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			result="您输入的用户名不存在!";//表示输入的用户名不存在
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			//关闭数据库连接
			this.closeResource();
		}
		return result;
	}
	/**
	 * 功能:根据用户名查询手机号
	 */
	@Override
	public String getMobiles(String eu_mobile) {
		// TODO Auto-generated method stub
		String result="";
		try {
			//sql语句
			String sql="select eu_mobile from easybuy_user where eu_mobile=?";
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps=con.prepareStatement(sql);
			ps.setString(1, eu_mobile);
			rs=ps.executeQuery();
			if(rs.next()){
				result=rs.getString(1);//获取第一列数据
			}else{
				result="您的手机号不存在!";//表示手机号不存在
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			result="您的手机号不存在!";//表示手机号不存在
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			//关闭数据库连接
			this.closeResource();
		}
		return result;
	}
	/**
	 * 功能:根据手机号及验证码修改用户密码
	 */
	@Override
	public int updateTelForget(easybuy_user eu) {
		//sql语句
		String sql="update easybuy_user set eu_password=? where eu_mobile=?";
		//创建一个List对象集合
		List<Object> list = new ArrayList<Object>();
		list.add(eu.getEu_Password());
		list.add(eu.getEu_Mobile());
		return this.executeUpdata(sql, list);
	}
}
