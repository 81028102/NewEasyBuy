package cn.jbit.daoimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import cn.jbit.dao.BaseDao;
import cn.jbit.dao.easybuy_product_Dao;
import cn.jbit.entity.easybuy_assess;
import cn.jbit.entity.easybuy_product;

/**
 * @author 任锯东
 */
@Repository
public class easybuy_product_DaoImpl extends BaseDao  implements easybuy_product_Dao {

	//声明集合对象,将easybuy_assess表放进List集合中
	List<easybuy_assess> list = new ArrayList<easybuy_assess>();
	/**
	 * 功能:根据Id获取单个商品的评价信息
	 */
	@Override
	public List<easybuy_assess> getAssess(int cpage, int pageSize,int ep_id) {
		// TODO Auto-generated method stub
		//sql语句
		/*sqlserver数据库测试   select top 10 a.ea_assess,a.ea_create_time,a.ea_nike_name from easybuy_assess a,easybuy_product p where a.ea_assessid=p.ep_id and p.ep_id=2 and a.ea_assess not in (select top 0 a.ea_assess from easybuy_assess a order by a.ea_create_time desc) order by a.ea_create_time desc*/
		String sql = "select top "+pageSize+" a.ea_assess,a.ea_create_time,a.ea_nike_name from easybuy_assess a,easybuy_product p where a.ea_assessid=p.ep_id and p.ep_id=? and a.ea_assess not in (select top "+(cpage-1)*pageSize+" a.ea_assess from easybuy_assess a order by a.ea_create_time desc) order by a.ea_create_time desc";
		try {
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps = con.prepareStatement(sql);
			ps.setInt(1, ep_id);
			rs = ps.executeQuery();
			while(rs.next()) {
				easybuy_assess ea=new easybuy_assess();
				ea.setEa_Assess(rs.getString("ea_assess"));
				ea.setEa_Create_time(rs.getString("ea_create_time"));
				ea.setEa_Nike_name(rs.getString("ea_nike_name"));
				list.add(ea);			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//关闭数据库连接
			this.closeResource();
		}
		return list;
	}

	@Override
	public List<easybuy_product> Geteasybuy_product() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 功能:根据Id获取单个商品的评价信息数量
	 */
	@Override
	public int getAssessCount(int ep_id) {
		// TODO Auto-generated method stub
		//sql语句
		String sql = "select count(1) from easybuy_assess a,easybuy_product p where a.ea_assessid=p.ep_id and p.ep_id="+ep_id;
		return this.getCount(sql);
	}
	/**
	 * 功能:添加评价信息
	 */
	@Override
	public int addAssess(easybuy_assess assess) {
		// TODO Auto-generated method stub
		//sql语句添加留言信息
		String sql="insert easybuy_assess values(?,?,?,?)";
		List<Object> list = new ArrayList<Object>();
		list.add(assess.getEa_Assessid());
		list.add(assess.getEa_Assess());
		list.add(assess.getEa_Create_time());
		list.add(assess.getEa_Nike_name());
		return this.executeUpdata(sql, list);
	}

	/**
	 * 功能:分页获取所有商品的评价信息
	 */
	@Override
	public List<easybuy_assess> getAssessProduct(int cpage, int pageSize,String ea_assess) {
		// TODO Auto-generated method stub
		//sql语句
		String sql = "select top "+pageSize+" * from easybuy_assess where ea_assess like ? and ea_assess not in (select top "+(cpage-1)*pageSize+" ea_assess from easybuy_assess order by ea_create_time desc) order by ea_create_time desc";
		try {
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps = con.prepareStatement(sql);
			ps.setString(1, "%"+ea_assess+"%");
			rs = ps.executeQuery();
			while(rs.next()) {
				easybuy_assess ea=new easybuy_assess();
				ea.setEa_Assessid(rs.getInt("ea_assessid"));
				ea.setEa_Assess(rs.getString("ea_assess"));
				ea.setEa_Create_time(rs.getString("ea_create_time"));
				ea.setEa_Nike_name(rs.getString("ea_nike_name"));
				ea.setEa_Upid(rs.getInt("ea_upid"));
				list.add(ea);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//关闭数据库连接
			this.closeResource();
		}
		return list;
	}

	/**
	 * 功能:获取所有商品的评价信息数量
	 */
	@Override
	public int getAssessCount(String ea_assess) {
		// TODO Auto-generated method stub
		//sql语句
		String sql = "select count(1) from easybuy_assess where ea_assess like '%"+ea_assess+"%'";
		return this.getCount(sql);
	}

	/**
	 * 功能:根据id删除评价信息
	 */
	@Override
	public int delAssessById(String ea_upid) {
		// TODO Auto-generated method stub
		//sql语句
		String sql="delete from easybuy_assess where ea_upid in("+ea_upid+")";
		return this.executeSql(sql);
	}

	/**
	 * 功能:删除全部评价信息
	 */
	@Override
	public int delAssessAll() {
		// TODO Auto-generated method stub
		//sql语句
		String sql="delete from easybuy_assess";
		return this.executeSql(sql);
	}
}
