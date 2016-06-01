package cn.jbit.daoimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import cn.jbit.dao.BaseDao;
import cn.jbit.dao.easybuy_productDao;
import cn.jbit.entity.easybuy_product;
import cn.jbit.entity.easybuy_products;

/**
 * @author 任锯东
 */
@Repository
public class easybuy_productDaoImpl extends BaseDao implements easybuy_productDao {

	//声明集合对象,将easybuy_product表放进List集合中
	List<easybuy_product> list = new ArrayList<easybuy_product>();
	/**
	 * 功能:根据条件分页查询商品信息
	 */
	@Override
	public List<easybuy_product> getEasybuy_products(int pageSize,
			int pageCount, String category, int id) {
		//编写sql语句
		String sql = "select top " + pageSize
				+ " * from easybuy_product where ep_id not in(select top "
				+ (pageCount - 1) * pageSize + " ep_id from easybuy_product order by ep_id desc) order by ep_id desc";
		//如果是一级分类
		if (category.equals("one")) {
			sql = "select top " + pageSize
					+ " * from easybuy_product where ep_id not in(select top "
					+ (pageCount - 1) * pageSize
					+ " ep_id from easybuy_product order by ep_id desc) and epc_id=" + id + " order by ep_id desc";
		}
		//如果是二级分类
		if (category.equals("two")) {
			sql = "select top " + pageSize
					+ " * from easybuy_product where ep_id not in(select top "
					+ (pageCount - 1) * pageSize
					+ " ep_id from easybuy_product order by ep_id desc) and epc_child_id=" + id
					+ " order by ep_id desc";
		}
		try {
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				easybuy_product ep = new easybuy_product();
				ep.setEp_Id(rs.getInt("ep_id"));
				ep.setEp_Name(rs.getString("ep_name"));
				ep.setEp_Description(rs.getString("ep_description"));
				ep.setEp_Price(rs.getFloat("ep_price"));
				ep.setEp_Stock(rs.getInt("ep_stock"));
				ep.setEpc_id(rs.getInt("epc_id"));
				ep.setEpc_Child_id(rs.getInt("epc_child_id"));
				ep.setEp_File_name(rs.getString("ep_file_name"));
				ep.setEp_Address(rs.getString("ep_address"));
				ep.setEp_Create_time(rs.getString("ep_create_time"));
				ep.setEp_Sales(rs.getInt("ep_sales"));
				list.add(ep);
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
	 * 功能:查询单条商品信息
	 */
	@Override
	public easybuy_product getEasybuy_product(int ep_id) {
		easybuy_product ep = new easybuy_product();
		//sql语句
		String sql = "select * from easybuy_product where ep_id=?";
		try {
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps = con.prepareStatement(sql);
			ps.setInt(1, ep_id);
			rs = ps.executeQuery();
			if(rs.next()) {
				ep.setEp_Id(rs.getInt("ep_id"));
				ep.setEp_Name(rs.getString("ep_name"));
				ep.setEp_Description(rs.getString("ep_description"));
				ep.setEp_Price(rs.getFloat("ep_price"));
				ep.setEp_Stock(rs.getInt("ep_stock"));
				ep.setEpc_id(rs.getInt("epc_id"));
				ep.setEpc_Child_id(rs.getInt("epc_child_id"));
				ep.setEp_File_name(rs.getString("ep_file_name"));
				ep.setEp_Address(rs.getString("ep_address"));
				ep.setEp_Create_time(rs.getString("ep_create_time"));
				ep.setEp_Sales(rs.getInt("ep_sales"));
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
		return ep;
	}
	/**
	 * 功能:添加商品信息
	 */
	@Override
	public int addProduct(easybuy_product ep) {
		// TODO Auto-generated catch block
		//sql语句
		String sql = "insert easybuy_product values(?,?,?,?,?,?,?,?,?,?)";
		//创建一个List集合对象
		List<Object> list = new ArrayList<Object>();
		list.add(ep.getEp_Name());
		list.add(ep.getEp_Description());
		list.add(ep.getEp_Price());
		list.add(ep.getEp_Stock());
		list.add(ep.getEpc_id());
		list.add(ep.getEpc_Child_id());
		list.add(ep.getEp_File_name());
		list.add(ep.getEp_Address());
		list.add(ep.getEp_Create_time());
		list.add(ep.getEp_Sales());
		return this.executeUpdata(sql, list);
	}
	/**
	 * 功能:根据id删除商品信息
	 */
	@Override
	public int delProduct(String ep_id) {
		// TODO Auto-generated catch block
		String sql="delete from easybuy_product where ep_id in("+ep_id+")";
		return this.executeSql(sql);
	}
	/**
	 * 功能:修改商品信息
	 */
	@Override
	public int updateProduct(easybuy_product ep, String type) {
		// TODO Auto-generated catch block
		int result = 0;
		if (type.equals("file")) {
			//sql语句
			String sql = "update easybuy_product set ep_name=?,ep_description=?,ep_price=?,ep_stock=?,epc_id=?,epc_child_id=?,ep_file_name=?,ep_address=? where ep_id=?";
			//创建一个List集合对象
			List<Object> list = new ArrayList<Object>();
			list.add(ep.getEp_Name());
			list.add(ep.getEp_Description());
			list.add(ep.getEp_Price());
			list.add(ep.getEp_Stock());
			list.add(ep.getEpc_id());
			list.add(ep.getEpc_Child_id());
			list.add(ep.getEp_File_name());
			list.add(ep.getEp_Address());
			list.add(ep.getEp_Id());
			result = this.executeUpdata(sql, list);
		}
		if (type.equals("nofile")) {
			//sql语句
			String sql = "update easybuy_product set ep_name=?,ep_description=?,ep_price=?,ep_stock=?,epc_id=?,epc_child_id=?,ep_address=? where ep_id=?";
			//创建一个List集合对象
			List<Object> list = new ArrayList<Object>();
			list.add(ep.getEp_Name());
			list.add(ep.getEp_Description());
			list.add(ep.getEp_Price());
			list.add(ep.getEp_Stock());
			list.add(ep.getEpc_id());
			list.add(ep.getEpc_Child_id());
			list.add(ep.getEp_Address());
			list.add(ep.getEp_Id());
			result = this.executeUpdata(sql, list);
		}
		return result;
	}
	/**
	 * 功能:获取商品总数量
	 */
	@Override
	public int getProductCount() {
		// TODO Auto-generated catch block
		//sql语句
		String sql = "select count(1) from easybuy_product";
		return this.getCount(sql);
	}
	/**
	 * 功能:根据类别ID获取商品数量
	 */
	@Override
	public int getTypeCount(int id, String type) {
		int num = 0;
		String sql = "";
		if (type.equals("0")) {
			//sql语句
			sql = "select * from easybuy_product where epc_id=?";
		}
		if (type.equals("1")) {
			//sql语句
			sql = "select * from easybuy_product where epc_child_id=?";
		}
		try {
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				num++;
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
		return num;
	}
	/**
	 * 功能:模糊查询商品信息(根据一级分类模糊,二级分类模糊,商品名称模糊)
	 */
	@Override
	public List<easybuy_product> getInfo(String info,String infos,String infoss,String ep_price,String ep_address,String ep_description) {
		//sql语句模糊查询
		String sql = "select distinct * from easybuy_product_category a,easybuy_product b where  a.epc_name like (select a.epc_name from easybuy_product_category a where  a.epc_id=b.epc_child_id and a.epc_name like ?) or a.epc_id=b.epc_child_id and b.ep_name like ? or a.epc_name like(select a.epc_name from easybuy_product_category a where  a.epc_id=b.epc_id and a.epc_name like ?) or a.epc_id=b.epc_child_id and b.ep_price like ? or a.epc_id=b.epc_child_id and b.ep_address like ? or a.epc_id=b.epc_child_id and b.ep_description like ? ";
		try {
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps = con.prepareStatement(sql);
			ps.setString(1, "%"+info+"%");
			ps.setString(2, "%"+infos+"%");
			ps.setString(3, "%"+infoss+"%");
			ps.setString(4, "%"+ep_price+"%");
			ps.setString(5, "%"+ep_address+"%");
			ps.setString(6, "%"+ep_description+"%");
			rs = ps.executeQuery();
			while(rs.next()) {
				easybuy_products ep=new easybuy_products();
				ep.setEpc_name(rs.getString("epc_name"));
				ep.setEpc_parent_id(rs.getInt("epc_parent_id"));
				ep.setEp_Id(rs.getInt("ep_id"));
				ep.setEp_Name(rs.getString("ep_name"));
				ep.setEp_Description(rs.getString("ep_description"));
				ep.setEp_Price(rs.getFloat("ep_price"));
				ep.setEp_Stock(rs.getInt("ep_stock"));
				ep.setEpc_id(rs.getInt("epc_id"));
				ep.setEpc_Child_id(rs.getInt("epc_child_id"));
				ep.setEp_File_name(rs.getString("ep_file_name"));
				ep.setEp_Address(rs.getString("ep_address"));
				ep.setEp_Create_time(rs.getString("ep_create_time"));
				ep.setEp_Sales(rs.getInt("ep_sales"));
				list.add(ep);
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
	 * 功能:模糊查询商品信息
	 */
	@Override
	public List<easybuy_product> getInfos(String info) {
		//sql语句 模糊查询
		String sql = "select * from easybuy_product where ep_name like ?";
		try {
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps = con.prepareStatement(sql);
			ps.setString(1, "%"+info+"%");
			rs = ps.executeQuery();
			if (rs.next()) {
				easybuy_product ep = new easybuy_product();
				ep.setEp_Id(rs.getInt("ep_id"));
				ep.setEp_Name(rs.getString("ep_name"));
				ep.setEp_Description(rs.getString("ep_description"));
				ep.setEp_Price(rs.getFloat("ep_price"));
				ep.setEp_Stock(rs.getInt("ep_stock"));
				ep.setEpc_id(rs.getInt("epc_id"));
				ep.setEpc_Child_id(rs.getInt("epc_child_id"));
				ep.setEp_File_name(rs.getString("ep_file_name"));
				list.add(ep);
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
	 * 功能:相似模糊查询商品信息
	 */
	@Override
	public List<easybuy_product> getResemble(CharSequence ep_name, float ep_price,
			CharSequence ep_address, String ep_description) {
		//sql语句模糊查询
		String sql = "select distinct * from easybuy_product_category a,easybuy_product b where  a.epc_id=b.epc_child_id and b.ep_name like ? or a.epc_id=b.epc_child_id and b.ep_price like ? or a.epc_id=b.epc_child_id and b.ep_address like ? or a.epc_id=b.epc_child_id and b.ep_description like ?";
		try {
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps = con.prepareStatement(sql);
			ps.setString(1, "%"+ep_name+"%");
			ps.setString(2, "%"+ep_price+"%");
			ps.setString(3, "%"+ep_address+"%");
			ps.setString(4, "%"+ep_description+"%");
			rs = ps.executeQuery();
			while(rs.next()) {
				easybuy_products ep=new easybuy_products();
				ep.setEpc_name(rs.getString("epc_name"));
				ep.setEpc_parent_id(rs.getInt("epc_parent_id"));
				ep.setEp_Id(rs.getInt("ep_id"));
				ep.setEp_Name(rs.getString("ep_name"));
				ep.setEp_Description(rs.getString("ep_description"));
				ep.setEp_Price(rs.getFloat("ep_price"));
				ep.setEp_Stock(rs.getInt("ep_stock"));
				ep.setEpc_id(rs.getInt("epc_id"));
				ep.setEpc_Child_id(rs.getInt("epc_child_id"));
				ep.setEp_File_name(rs.getString("ep_file_name"));
				ep.setEp_Address(rs.getString("ep_address"));
				ep.setEp_Create_time(rs.getString("ep_create_time"));
				ep.setEp_Sales(rs.getInt("ep_sales"));
				list.add(ep);
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
	 * 功能:获取销量总数
	 */
	@Override
	public int getSalesCount(String ep_name) {
		// TODO Auto-generated method stub
		//sql语句
		String sql = "select count(1) from easybuy_product where ep_name like '%"+ep_name+"%'";
		return this.getCount(sql);
	}
	/**
	 * 功能分页获取销售信息
	 */
	@Override
	public List<easybuy_product> getSalesAllProduct(int cpage, int pageSize,
			String ep_name) {
		//sql语句
		// MONTH(eo_create_time) between MONTH(GETDATE()) and  MONTH(DATEADD(MONTH,1,GETDATE())) 
		String sql = "select top "+pageSize+" * from easybuy_product where ep_name like ? and ep_name not in(select top "+(cpage-1)*pageSize+" ep_name from easybuy_product order by ep_sales desc) order by ep_sales desc";
		try {
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps=con.prepareStatement(sql);
			ps.setString(1, "%"+ep_name+"%");
			rs=ps.executeQuery();
			while (rs.next()) {
				easybuy_product ep = new easybuy_product();
				ep.setEp_Id(rs.getInt("ep_id"));
				ep.setEp_Name(rs.getString("ep_name"));
				ep.setEp_Description(rs.getString("ep_description"));
				ep.setEp_Price(rs.getFloat("ep_price"));
				ep.setEp_Stock(rs.getInt("ep_stock"));
				ep.setEpc_id(rs.getInt("epc_id"));
				ep.setEpc_Child_id(rs.getInt("epc_child_id"));
				ep.setEp_File_name(rs.getString("ep_file_name"));
				ep.setEp_Address(rs.getString("ep_address"));
				ep.setEp_Create_time(rs.getString("ep_create_time"));
				ep.setEp_Sales(rs.getInt("ep_sales"));
				list.add(ep);
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
}