package cn.jbit.daoimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import cn.jbit.dao.BaseDao;
import cn.jbit.dao.easybuy_folderDao;
import cn.jbit.entity.easybuy_folder;

/**
 * @author 任锯东
 */
@Repository
public class eaybuy_folderDaoImpl extends BaseDao implements easybuy_folderDao{

	//创建集合对象将easybuy_user表放进List集合中
	List<easybuy_folder> list=new ArrayList<easybuy_folder>();
	/**
	 * 功能:新建文件夹
	 */
	@Override
	public int save(easybuy_folder folder) {
		// TODO Auto-generated method stub
		//sql语句添加留言信息
		String sql="insert into easybuy_folder(fname,fusername,ftime,fremark,fpath,fuptime) values(?,?,?,?,?,?)";
		List<Object> list = new ArrayList<Object>();
		list.add(folder.getFname());
		list.add(folder.getFusername());
		list.add(folder.getFtime());
		list.add(folder.getFremark());
		list.add(folder.getFpath());
		list.add(folder.getFuptime());
		return this.executeUpdata(sql, list);
	}
	/**
	 * 功能:分页查询文档列表
	 */
	@Override
	public List<easybuy_folder> querylist(int cpage, int pageSize,String fname) {
		//sql语句
		String sql = "select top "+pageSize+" * from easybuy_folder where fdele=1 and fname like ? and  fname not in(select top "+(cpage-1)*pageSize+" fname from easybuy_folder where fdele=1 order by fid desc) order by fid desc";
		try {
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps=con.prepareStatement(sql);
			ps.setString(1, "%"+fname+"%");
			rs=ps.executeQuery();
			while (rs.next()) {
				easybuy_folder ef=new easybuy_folder();
				ef.setFid(rs.getInt("fid"));
				ef.setFname(rs.getString("fname"));
				ef.setFusername(rs.getString("fusername"));
				ef.setFtime(rs.getString("ftime"));
				ef.setFdele(rs.getInt("fdele"));
				ef.setFpath(rs.getString("fpath"));
				ef.setFuptime(rs.getString("fuptime"));
				list.add(ef);
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
	 * 功能:根据id查询
	 */
	@Override
	public easybuy_folder querybyid(int fid) {
		easybuy_folder ef=new easybuy_folder();
		//sql语句
		String sql = "select * from easybuy_folder where fid=?";
		try {
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps=con.prepareStatement(sql);
			ps.setInt(1, fid);
			rs=ps.executeQuery();
			if(rs.next()){
				ef.setFid(rs.getInt("fid"));
				ef.setFname(rs.getString("fname"));
				ef.setFusername(rs.getString("fusername"));
				ef.setFtime(rs.getString("ftime"));
				ef.setFremark(rs.getString("fremark"));
				ef.setFdele(rs.getInt("fdele"));
				ef.setFpath(rs.getString("fpath"));
				ef.setFuptime(rs.getString("fuptime"));
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
		return ef;
	}
	/**
	 * 功能:修改文件夹
	 */
	@Override
	public int update(easybuy_folder folder) {
		// TODO Auto-generated method stub
		String sql="update easybuy_folder set fname=?,fremark=?,fuptime=? where fid=?";
		List<Object> list = new ArrayList<Object>();
		list.add(folder.getFname());
		list.add(folder.getFremark());
		list.add(folder.getFuptime());
		list.add(folder.getFid());
		return this.executeUpdata(sql, list);
	}
	/**
	 * 功能:删除（(修改)加入回收站）
	 */
	@Override
	public int upd(easybuy_folder folder) {
		// TODO Auto-generated method stub
		String sql="update easybuy_folder set ftime=?,fdele=?,fuptime=? where fid=?";
		List<Object> list = new ArrayList<Object>();
		list.add(folder.getFtime());
		list.add(folder.getFdele());
		list.add(folder.getFuptime());
		list.add(folder.getFid());
		return this.executeUpdata(sql, list);
	}
	/**
	 * 功能:分页查询回收站列表
	 */
	@Override
	public List<easybuy_folder> queryrecycle(int cpage, int pageSize,String fname) {
		// TODO Auto-generated method stub
		//sql语句
		String sql = "select top "+pageSize+" * from easybuy_folder where fdele=2 and fname like ? and  fname not in(select top "+(cpage-1)*pageSize+" fname from easybuy_folder where fdele=2 order by fid desc) order by fid desc";
		try {
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps=con.prepareStatement(sql);
			ps.setString(1, "%"+fname+"%");
			rs=ps.executeQuery();
			while (rs.next()) {
				easybuy_folder ef=new easybuy_folder();
				ef.setFid(rs.getInt("fid"));
				ef.setFname(rs.getString("fname"));
				ef.setFusername(rs.getString("fusername"));
				ef.setFtime(rs.getString("ftime"));
				ef.setFdele(rs.getInt("fdele"));
				ef.setFpath(rs.getString("fpath"));
				ef.setFuptime(rs.getString("fuptime"));
				list.add(ef);
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
	 * 功能:根据id删除文档信息
	 */
	@Override
	public int dele(int fid) {
		// TODO Auto-generated method stub
		//sql语句
		String sql="delete from easybuy_folder where fid="+fid;
		return this.executeSql(sql);
	}
	/**
	 * 功能:获取文件总数
	 */
	@Override
	public int getFolderCount(int fdele,String fname) {
		// TODO Auto-generated method stub
		//sql语句
		String sql = "select count(1) from easybuy_folder where fdele="+fdele+" and fname like '%"+fname+"%'";
		return this.getCount(sql);
	}
	/**
	 * 功能:判断是否存在指定文档信息
	 */
	@Override
	public boolean boolfolder(String fname) {
		//标记一下
		boolean flag=false;
		//sql语句
		String sql = "select * from easybuy_folder where fname=?";
		try {
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps = con.prepareStatement(sql);
			ps.setString(1, fname);
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