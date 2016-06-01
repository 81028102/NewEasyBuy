package cn.jbit.daoimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import cn.jbit.dao.BaseDao;
import cn.jbit.dao.easybuy_orderDao;
import cn.jbit.entity.easybuy_order;
import cn.jbit.entity.easybuy_order_detail;
import cn.jbit.entity.easybuy_order_status;

/**
 * @author 任锯东
 */
@Repository
public class easybuy_orderDaoImpl extends BaseDao implements easybuy_orderDao {

	//创建一个List集合将easybuy_order表放进List集合中
	List<easybuy_order> list = new ArrayList<easybuy_order>();
	/**
	 * 功能:获取指定用户的订单数量
	 */
	@Override
	public int countNums(int eo_id, String eu_user_id) {
		// TODO Auto-generated catch block
		//sql语句永久成立
		String sql = "select count(1) from easybuy_order where 1=1";
		if (eo_id != 0) {
			sql += "  and eo_id=" + eo_id + "";
		}
		if (eu_user_id != null) {
			sql += "  and eo_user_id='" + eu_user_id + "'";
		}
		return this.getCount(sql);
	}
	/**
	 * 功能:获取所有订单的数量
	 */
	@Override
	public int countNum(int eu_id) {
		// TODO Auto-generated catch block
		//sql语句
		String sql = "select count(1) from easybuy_order where eo_user_id="+eu_id+"";
		return this.getCount(sql);
	}
	/**
	 * 功能:分页获取指定用户的订单集合
	 */
	@Override
	public List<easybuy_order> getEasybuy_orders(int pageSize,
			int pageCurrpage, int status, String id) {
		String sql = "select top " + pageSize
				+ " * from easybuy_order where eo_id not in(select top "
				+ (pageCurrpage - 1) * pageSize
				+ " eo_id from easybuy_order order by eo_id desc) and 1=1 order by eo_id desc";
		if (id != null) {
			sql += " and eo_user_id='" + id + "'";
		}
		if (status != 0) {
			sql += " and eo_id=" + status + ""; 
		}
		try {
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				easybuy_order eo = new easybuy_order();
				eo.setEo_Id(rs.getInt("eo_id"));
				eo.setEo_User_id(rs.getString("eo_user_id"));
				eo.setEo_User_name(rs.getString("eo_user_name"));
				eo.setEo_User_address(rs.getString("eo_user_address"));
				eo.setEo_Create_time(rs.getString("eo_create_time"));
				eo.setEo_Cost(rs.getFloat("eo_cost"));
				eo.setEo_Status(rs.getInt("eo_status"));
				list.add(eo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			//关闭数据库连接
			this.closeResource();
		}
		return list;
	}
	/**
	 * 功能:分页获取所有订单
	 */
	@Override
	public easybuy_order getEasybuy_order(int eo_id) {
		// TODO Auto-generated catch block
		//sql语句
		String sql = "select * from easybuy_order where eo_id="+eo_id+"";
		return	(easybuy_order)this.getObjectById(easybuy_order.class, sql);
	}
	/**
	 * 功能:获取单个订单信息
	 */
	@Override
	public int add(easybuy_order eo) {
		// TODO Auto-generated catch block
		//sql语句插入
		String sql = "insert easybuy_order values(?,?,?,?,?,?,?)";
		//创建一个List集合对象
		List<Object> list = new ArrayList<Object>();
		list.add(eo.getEo_User_id());
		list.add(eo.getEo_User_name());
		list.add(eo.getEo_User_address());
		list.add(eo.getEo_Create_time());
		list.add(eo.getEo_Cost());
		list.add(eo.getEo_Status());
		list.add(eo.getEo_Score());
		return this.executeUpdata(sql, list);
	}
	/**
	 * 功能:添加订单信息
	 */
	@Override
	public List<easybuy_order> getUserOrders(int pageSize, int pageCurr,
			String eu_user_id) {
		//sql语句
		String sql = "select top " + pageSize
				+ " * from easybuy_order where eo_id not in(select top "
				+ (pageCurr - 1) * pageSize
				+ " eo_id from easybuy_order) and eo_user_id='" + eu_user_id
				+ "' ";
		try {
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				easybuy_order eo = new easybuy_order();
				eo.setEo_Id(rs.getInt("eo_id"));
				eo.setEo_User_id(rs.getString("eo_user_id"));
				eo.setEo_User_name(rs.getString("eo_user_name"));
				eo.setEo_User_address(rs.getString("eo_user_address"));
				eo.setEo_Create_time(rs.getString("eo_create_time"));
				eo.setEo_Cost(rs.getFloat("eo_cost"));
				eo.setEo_Status(rs.getInt("eo_status"));
				list.add(eo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			//关闭数据库连接
			this.closeResource();
		}
		return list;
	}
	/**
	 * 功能:获取所有订单详情
	 */
	@Override
	public List<easybuy_order_detail> getDetail() {
		List<easybuy_order_detail> list = new ArrayList<easybuy_order_detail>();
		String sql = "select a.*,b.ep_name,b.ep_file_name from easybuy_order_detail as a,easybuy_product as b where a.ep_id=b.ep_id";
		try {
			//打开数据库连接、
			this.openConnection();
			//创建一个prepareStatement对象
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				easybuy_order_detail eo = new easybuy_order_detail();
				eo.setEo_Id(rs.getInt("eo_id"));
				eo.setEod_Id(rs.getInt("eod_id"));
				eo.setEp_Id(rs.getInt("ep_id"));
				eo.setEp_Name(rs.getString("ep_name"));
				eo.setEp_File_name(rs.getString("ep_file_name"));
				eo.setEod_Cost(rs.getFloat("eod_cost"));
				eo.setEod_Quantity(rs.getInt("eod_quantity"));
				list.add(eo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			//关闭数据库连接
			this.closeResource();
		}
		return list;
	}
	/**
	 * 功能:按购买日期查询订单
	 */
	@Override
	public List<easybuy_order> getDateOrder(int pageSize, int pageCurr,
			String date, String date1, String eu_user_id) {
		//sql语句
		String sql = "select top " + pageSize
				+ " * from easybuy_order where eo_id not in(select top "
				+ (pageCurr - 1) * pageSize
				+ " eo_id from easybuy_order) and eo_user_id='" + eu_user_id
				+ "' ";
		if (date != null) {
			sql = "select top " + pageSize
					+ " * from easybuy_order where eo_id not in(select top "
					+ (pageCurr - 1) * pageSize
					+ " eo_id from easybuy_order) and eo_user_id='"
					+ eu_user_id + "' and eo_create_time>='" + date + "' ";
		}
		if (date1 != null) {
			sql = "select top " + pageSize
					+ " * from easybuy_order where eo_id not in(select top "
					+ (pageCurr - 1) * pageSize
					+ " eo_id from easybuy_order) and eo_user_id='"
					+ eu_user_id + "' and eo_create_time between '" + date
					+ "' and '" + date1 + "' ";
		}
		try {
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				easybuy_order eo = new easybuy_order();
				eo.setEo_Id(rs.getInt("eo_id"));
				eo.setEo_User_id(rs.getString("eo_user_id"));
				eo.setEo_User_name(rs.getString("eo_user_name"));
				eo.setEo_User_address(rs.getString("eo_user_address"));
				eo.setEo_Create_time(rs.getString("eo_create_time"));
				eo.setEo_Cost(rs.getFloat("eo_cost"));
				eo.setEo_Status(rs.getInt("eo_status"));
				list.add(eo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			//关闭数据库连接
			this.closeResource();
		}
		return list;
	}
	/**
	 * 功能:查询为发货的商品
	 */
	@Override
	public int getNoProducts(String eo_user_id) {
		int i = 0;
		//sql语句
		String sql = "select * from easybuy_order where easybuy_order.eo_status=1 and easybuy_order.eo_user_id=?";
		try {
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps = con.prepareStatement(sql);
			ps.setString(1, eo_user_id);
			rs = ps.executeQuery();
			if (rs.next()) {
				i = 1;
			} else {
				i = 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			//关闭数据库连接
			this.closeResource();
		}
		return i;
	}
	/**
	 * 功能:获取所有订单状态
	 */
	@Override
	public List<easybuy_order_status> getOrder_Status() {
		// TODO Auto-generated method stub
		List<easybuy_order_status> list=new ArrayList<easybuy_order_status>();
		try {
			//打开数据库连接
			this.openConnection();
			//sql语句
			String sql="select * from easybuy_order_status";
			//创建一个prepareStatement对象
			ps= con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				easybuy_order_status eos = new easybuy_order_status();
				eos.setEs_Id(rs.getInt("es_id"));
				eos.setEs_StatusName(rs.getString("es_statusname"));
				list.add(eos);
			}
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
	 * 功能:修改订单状态
	 */
	@Override
	public int updateStatus(easybuy_order eo) {
		// TODO Auto-generated method stub
		//sql语句
		String sql="update easybuy_order set eo_status=? where eo_id=?";
		//创建一个List对象集合
		List<Object> list = new ArrayList<Object>();
		list.add(eo.getEo_Status());
		list.add(eo.getEo_Id());
		return this.executeUpdata(sql, list);
	}
	/**
	 * 功能:根据id删除订单
	 */
	@Override
	public int delOrderById(int eo_id) {
		// TODO Auto-generated method stub
		//sql语句
		String sql="delete from easybuy_order where eo_id="+eo_id+"";
		return this.executeSql(sql);
	}
}
