package cn.jbit.daoimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import cn.jbit.dao.BaseDao;
import cn.jbit.dao.easybuy_product_categoryDao;
import cn.jbit.entity.easybuy_product_category;

/**
 * @author 任锯东
 */
@Repository
public class easybuy_product_categoryDaoImpl extends BaseDao implements easybuy_product_categoryDao {

	//声明集合对象,将easybuy_product_category表放进List集合中
	List<easybuy_product_category> list=new ArrayList<easybuy_product_category>();
	/**
	 * 功能:获取一级商品分类
	 */
	@Override
	public List<easybuy_product_category> getCategories(int id) {
		//sql语句
		String sql="select * from easybuy_product_category";
		if(id==0){
			sql="select * from easybuy_product_category where epc_parent_id =0";
		}else{
			sql="select * from easybuy_product_category where epc_parent_id !=0";
		}
		try {
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				easybuy_product_category epc=new easybuy_product_category();
				epc.setEpc_Id(rs.getInt("epc_id"));
				epc.setEpc_Name(rs.getString("epc_name"));
				epc.setEpc_Parent_id(rs.getInt("epc_parent_id"));
				list.add(epc);
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
	 * 功能:获取指定ID分类
	 */
	@Override
	public easybuy_product_category getCategory(int epc_id) {
		easybuy_product_category epc=new easybuy_product_category();
		//sql语句
		String sql = "select * from easybuy_product_category where epc_id=?";
		try {
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps = con.prepareStatement(sql);
			ps.setInt(1, epc_id);
			rs=ps.executeQuery();
			if(rs.next()){
				epc.setEpc_Id(rs.getInt("epc_id"));
				epc.setEpc_Name(rs.getString("epc_name"));
				epc.setEpc_Parent_id(rs.getInt("epc_parent_id"));
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
		return epc;
	}
	/**
	 * 功能:添加商品分类
	 */
	@Override
	public int addCategory(easybuy_product_category epc) {
		// TODO Auto-generated catch block
		//sql语句
		String sql="insert easybuy_product_category values(?,?,?)";
		//创建一个List集合对象
		List<Object> list = new ArrayList<Object>();
		list.add(epc.getEpc_Name());
		list.add(epc.getEpc_Parent_id());
		list.add(epc.getEpc_Click_Count());
		return this.executeUpdata(sql, list);
	}
	/**
	 * 功能:删除指定ID分类
	 */
	@Override
	public int delCategory(int epc_id) {
		// TODO Auto-generated catch block
		//sql语句
		String sql="delete from easybuy_product_category where epc_id="+epc_id+"";
		return this.executeSql(sql);
	}
	/**
	 * 功能:更新分类信息
	 */
	@Override
	public int updateCategory(easybuy_product_category epc) {
		// TODO Auto-generated catch block
		//sql语句
		String sql="update easybuy_product_category set epc_name=?,epc_parent_id=? where epc_id=?";
		//创建一个List集合对象
		List<Object> list = new ArrayList<Object>();
		list.add(epc.getEpc_Name());
		list.add(epc.getEpc_Parent_id());
		list.add(epc.getEpc_Id());
		return this.executeUpdata(sql, list);
	}
	/**
	 * 功能:获取所有分类信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<easybuy_product_category> getAll() {
		// TODO Auto-generated catch block
		//sql语句
		String sql="select * from easybuy_product_category";
		return this.getList(easybuy_product_category.class, sql);
	}
	/**
	 * 功能:判断是否存在指定分类信息
	 */
	@Override
	public boolean boolcate(String epc_name) {
		//标记一下
		boolean flag=false;
		//sql语句
		String sql = "select * from easybuy_product_category where epc_name=?";
		try {
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps = con.prepareStatement(sql);
			ps.setString(1, epc_name);
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
	 * 功能:根据二级分类ID获取一级分类ID
	 */
	@Override
	public int getparent(int id) {
		int parent=0;
		//sql语句
		String sql = "select epc_parent_id from easybuy_product_category where epc_id=?";
		try {
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next()){
				parent=rs.getInt(1);
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
		return parent;
	}
	/**
	 * 功能:更新分类点击数量
	 */
	@Override
	public int updateCategoryCount(easybuy_product_category epc) {
		// TODO Auto-generated method stub
		//sql语句
		String sql="update easybuy_product_category set epc_click_count=epc_click_count+1 where epc_id=?";
		//创建List集合对象
		List<Object> list = new ArrayList<Object>();
		list.add(epc.getEpc_Id());
		return this.executeUpdata(sql, list);
	}
	/**
	 * 功能:分页获取所有分类信息
	 */
	@Override
	public List<easybuy_product_category> getAlls(int cpage, int pageSize,String epc_name) {
		//sql语句
		String sql="select top "+pageSize+" * from easybuy_product_category where epc_name like ? and  epc_name not in(select top "+(cpage-1)*pageSize+" epc_name from easybuy_product_category order by epc_click_count desc) order by epc_click_count desc";
		try {
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps = con.prepareStatement(sql);
			ps.setString(1,"%"+epc_name+"%");
			rs=ps.executeQuery();
			while(rs.next()){
				easybuy_product_category epc=new easybuy_product_category();
				epc.setEpc_Id(rs.getInt("epc_id"));
				epc.setEpc_Name(rs.getString("epc_name"));
				epc.setEpc_Parent_id(rs.getInt("epc_parent_id"));
				epc.setEpc_Click_Count(rs.getInt("epc_click_count"));
				list.add(epc);
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
	 * 功能:获取分类总数
	 */
	@Override
	public int getCategoryCount(String epc_name) {
		int count=0;
		//sql语句
		String sql = "select count(1) from easybuy_product_category where epc_name like ?";
		try {
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps=con.prepareStatement(sql);
			ps.setString(1,"%"+epc_name+"%");
			rs=ps.executeQuery();
			if(rs.next()){
				count=rs.getInt(1);
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
		return count;//返回结果
	}
}
