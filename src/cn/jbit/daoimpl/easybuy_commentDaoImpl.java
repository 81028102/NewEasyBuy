package cn.jbit.daoimpl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import cn.jbit.dao.BaseDao;
import cn.jbit.dao.easybuy_commentDao;
import cn.jbit.entity.easybuy_comment;

/**
 * @author 任锯东
 */
@Repository
public class easybuy_commentDaoImpl extends BaseDao implements easybuy_commentDao {

	//创建List集合对象将easybuy_comment表放进List集合中
	List<easybuy_comment> list=new ArrayList<easybuy_comment>();
	/**
	 * 功能:分页查询留言信息
	 */
	@Override
	public List<easybuy_comment> getComments(int cpage, int pageSize,String ec_nick_name) {
		try {
			//打开数据库连接
			this.openConnection();
			//sql语句
			String sql="select top "+pageSize+" * from easybuy_comment where ec_nick_name like ? and  ec_id not in(select top "+(cpage-1)*pageSize+" ec_id from easybuy_comment order by ec_create_time desc) order by ec_create_time desc";
			//sql语句分页前几页查询留言信息
			ps=con.prepareStatement(sql);
			ps.setString(1, "%"+ec_nick_name+"%");
			rs=ps.executeQuery();
			//格式化时间为yyyy-MM-dd HH:mm:ss
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while (rs.next()) {
				easybuy_comment ec=new easybuy_comment();
				ec.setEc_Id(rs.getInt("ec_id"));
				ec.setEc_Content(rs.getString("ec_content"));
				ec.setEc_Create_time(sf.format(rs.getDate("ec_create_time")));
				ec.setEc_Reply(rs.getString("ec_reply"));
				if(rs.getDate("ec_reply_time")!=null){
					ec.setEc_Reply_time(sf.format(rs.getDate("ec_reply_time")));
				}
				ec.setEc_Nick_name(rs.getString("ec_nick_name"));
				list.add(ec);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated method stub
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally{
			//关闭数据库连接
			this.closeResource();
		}
		return list;
	}
	/**
	 * 功能:获取单条留言信息
	 * @return
	 */
	@Override
	public easybuy_comment getComment(int ec_id) {
		easybuy_comment ec=new easybuy_comment();
		try {
			//打开数据库连接
			this.openConnection();
			//sql语句根据id查询单条留言信息
			ps=con.prepareStatement("select * from easybuy_comment where ec_id=?");
			ps.setInt(1, ec_id);
			rs=ps.executeQuery();
			//格式化时间为yyyy-MM-dd HH:mm:ss
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (rs.next()) {
				ec.setEc_Id(rs.getInt("ec_id"));
				ec.setEc_Content(rs.getString("ec_content"));
				ec.setEc_Create_time(sf.format(rs.getDate("ec_create_time")));
				ec.setEc_Reply(rs.getString("ec_reply"));
				if(rs.getDate("ec_reply_time")!=null){
					ec.setEc_Reply_time(sf.format(rs.getDate("ec_reply_time")));
				}
				ec.setEc_Nick_name(rs.getString("ec_nick_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated method stub
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally{
			//关闭数据库连接
			this.closeResource();
		}
		return ec;
	}
	/**
	 * 功能:添加留言信息
	 * @return
	 */
	@Override
	public int addComment(easybuy_comment ec) {
		// TODO Auto-generated method stub
		//sql语句添加留言信息
		String sql="insert easybuy_comment values(?,?,?,?,?)";
		List<Object> list = new ArrayList<Object>();
		list.add(ec.getEc_Content());
		list.add(ec.getEc_Create_time());
		list.add(ec.getEc_Reply());
		list.add(ec.getEc_Reply_time());
		list.add(ec.getEc_Nick_name());
		return this.executeUpdata(sql, list);
	}
	/**
	 * 功能:更新留言信息
	 * @return
	 */
	@Override
	public int updateComment(easybuy_comment ec) {
		// TODO Auto-generated method stub
		String sql="update easybuy_comment set ec_content=?,ec_create_time=?,ec_reply=?,ec_reply_time=?,ec_nick_name=? where ec_id=?";
		List<Object> list = new ArrayList<Object>();
		list.add(ec.getEc_Content());
		list.add(ec.getEc_Create_time());
		list.add(ec.getEc_Reply());
		list.add(ec.getEc_Reply_time());
		list.add(ec.getEc_Nick_name());
		list.add(ec.getEc_Id());
		return this.executeUpdata(sql, list);
	}
	/**
	 * 功能:根据id删除留言信息
	 */
	@Override
	public int delComment(String ec_id) {
		// TODO Auto-generated method stub
		//sql语句
		String sql="delete from easybuy_comment where ec_id in("+ec_id+")";
		return this.executeSql(sql);
	}
	/**
	 * 功能:留言总数量
	 */
	@Override
	public int countcomments(String ec_nick_name) {
		// TODO Auto-generated method stub
		//sql语句
		String sql="select count(1) from easybuy_comment where ec_nick_name like '%"+ec_nick_name+"%'";
		return this.getCount(sql);
	}
	/**
	 * 功能:删除全部留言信息
	 */
	@Override
	public int delCommentsAll() {
		// TODO Auto-generated method stub
		String sql="delete from easybuy_comment";
		return this.executeSql(sql);
	}
}
