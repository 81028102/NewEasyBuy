package cn.jbit.daoimpl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import cn.jbit.dao.BaseDao;
import cn.jbit.dao.easybuy_newsDao;
import cn.jbit.entity.easybuy_news;
import cn.jbit.entity.easybuy_news_column;
import cn.jbit.entity.easybuy_news_type;

/**
 * @author 任锯东
 */
@Repository
public class easybuy_newsDaoImpl extends BaseDao implements easybuy_newsDao {

	//创建List集合将easybuy_news表放进List集合中
	List<easybuy_news> list=new ArrayList<easybuy_news>();
	//创建List集合将easybuy_news_type表放进List集合中
	List<easybuy_news_type> list1=new ArrayList<easybuy_news_type>();
	//创建List集合将easybuy_news_column表放进List集合中
	List<easybuy_news_column> list2=new ArrayList<easybuy_news_column>();
	/**
	 * 功能:分页获取新闻
	 */
	@Override
	public List<easybuy_news> getEasybuy_news(int cpage, int pageSize,String en_Title) {
		try {
			//打开数据库连接
			this.openConnection();
			//sql语句创建一个prepareStatement对象
			ps=con.prepareStatement("select top "+pageSize+" en_id,en_title,en_content,en_create_time,en_click_count,enc_name,ent_name from easybuy_news en,easybuy_news_column enc,easybuy_news_type ent where en.enc_id=enc.enc_id and en.ent_id=ent.ent_id and en_title like ? and en_id not in(select top "+(cpage-1)*pageSize+" en_id from easybuy_news en,easybuy_news_column enc,easybuy_news_type ent where en.enc_id=enc.enc_id and en.ent_id=ent.ent_id order by en_create_time desc) order by en_create_time desc");
			ps.setString(1,"%"+en_Title+"%" );
			rs=ps.executeQuery();
			while(rs.next()){
				easybuy_news en=new easybuy_news();
				en.setEn_Id(rs.getInt("en_id"));
				en.setEn_Title(rs.getString("en_title"));
				en.setEn_Content(rs.getString("en_content"));
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化时间
				en.setEn_Create_time(sf.format(rs.getDate("en_create_time")));
				en.setEn_Click_Count(rs.getInt("en_click_count"));
				en.setEnc_Name(rs.getString("enc_Name"));
				en.setEnt_Name(rs.getString("ent_Name"));
				list.add(en);
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
	 * 功能:获取指定ID新闻
	 */
	@Override
	public easybuy_news getNews(int en_id) {
		easybuy_news en=new easybuy_news();
		try {
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps=con.prepareStatement("select * from easybuy_news where en_id=?");
			ps.setInt(1,en_id);
			rs=ps.executeQuery();
			while(rs.next()){
				en.setEn_Id(rs.getInt("en_id"));
				en.setEn_Title(rs.getString("en_title"));
				en.setEn_Content(rs.getString("en_content"));
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化时间
				en.setEn_Create_time(sf.format(rs.getDate("en_create_time")));
				en.setEn_Click_Count(rs.getInt("en_click_count"));
				en.setEnc_Id(rs.getInt("enc_id"));
				en.setEnt_Id(rs.getInt("ent_id"));
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
		return en;
	}
	/**
	 * 功能:添加新闻
	 */
	@Override
	public int addNew(easybuy_news en) {
		// TODO Auto-generated method stub
		//sql语句
		String sql="insert easybuy_news values(?,?,?,?,?,?)";
		//创建List集合对象
		List<Object> list = new ArrayList<Object>();
		list.add(en.getEn_Title());
		list.add(en.getEn_Content());
		list.add(en.getEn_Create_time());
		list.add(en.getEn_Click_Count());
		list.add(en.getEnc_Id());
		list.add(en.getEnt_Id());
		return this.executeUpdata(sql, list);
	}
	/**
	 * 功能:删除新闻
	 */
	@Override
	public int delNew(String en_id) {
		// TODO Auto-generated method stub
		//sql语句
		String sql="delete from easybuy_news where en_id in("+en_id+")";
		return this.executeSql(sql);
	}
	/**
	 * 功能:更新新闻信息
	 */
	@Override
	public int updateNew(easybuy_news en) {
		// TODO Auto-generated method stub
		//sql语句
		String sql="update easybuy_news set en_title=?,en_content=?,en_create_time=?,enc_id=?,ent_id=? where en_id=?";
		//创建List集合对象
		List<Object> list = new ArrayList<Object>();
		list.add(en.getEn_Title());
		list.add(en.getEn_Content());
		list.add(en.getEn_Create_time());
		list.add(en.getEnc_Id());
		list.add(en.getEnt_Id());
		list.add(en.getEn_Id());
		return this.executeUpdata(sql, list);
	}
	/**
	 * 功能:获取最近十条新闻
	 */
	@Override
	public List<easybuy_news> getTen() {
		try {
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象 sql语句查询前10行
			ps=con.prepareStatement("select top 10 * from easybuy_news order by en_create_time desc");
			rs=ps.executeQuery();
			while(rs.next()){
				easybuy_news en=new easybuy_news();
				en.setEn_Id(rs.getInt("en_id"));
				en.setEn_Title(rs.getString("en_title"));
				en.setEn_Content(rs.getString("en_content"));
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化时间
				en.setEn_Create_time(sf.format(rs.getDate("en_create_time")));
				en.setEn_Click_Count(rs.getInt("en_click_count"));
				list.add(en);
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
	 * 功能:获取新闻的总数量
	 */
	@Override
	public int getNewsCount(String en_Title) {
		// TODO Auto-generated method stub
		String sql="select count(1) from easybuy_news where en_title like '%"+en_Title+"%'";
		return this.getCount(sql);
	}
	/**
	 * 功能:查询所有新闻
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<easybuy_news> AllNews() {
		// TODO Auto-generated method stub
		//sql语句
		String sql="select * from easybuy_news";
		return this.getList(easybuy_news.class, sql);
	}
	/**
	 * 功能:更新新闻点击数量
	 */
	@Override
	public int updateNewCount(easybuy_news en) {
		// TODO Auto-generated method stub
		//sql语句
		String sql="update easybuy_news set en_click_count=en_click_count+1 where en_id=?";
		//创建List集合对象
		List<Object> list = new ArrayList<Object>();
		list.add(en.getEn_Id());
		return this.executeUpdata(sql, list);
	}
	/**
	 * 功能:分页显示新闻点击率排行
	 */
	@Override
	public List<easybuy_news> getEasybuy_Click(int cpage, int pageSize,
			String en_Title) {
		// TODO Auto-generated method stub
		try {
			//打开数据库连接
			this.openConnection();
			//sql语句创建一个prepareStatement对象
			ps=con.prepareStatement("select top "+pageSize+" * from easybuy_news where en_title like ? and en_id not in(select top "+(cpage-1)*pageSize+" en_id from easybuy_news order by en_click_count desc) order by en_click_count desc");
			ps.setString(1,"%"+en_Title+"%" );
			rs=ps.executeQuery();
			while(rs.next()){
				easybuy_news en=new easybuy_news();
				en.setEn_Id(rs.getInt("en_id"));
				en.setEn_Title(rs.getString("en_title"));
				en.setEn_Content(rs.getString("en_content"));
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化时间
				en.setEn_Create_time(sf.format(rs.getDate("en_create_time")));
				en.setEn_Click_Count(rs.getInt("en_click_count"));
				list.add(en);
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
	 * 功能:删除全部新闻
	 */
	@Override
	public int delNewsAll() {
		// TODO Auto-generated method stub
		//sql语句
		String sql="delete from easybuy_news";
		return this.executeSql(sql);
	}
	/**
	 * 功能:分页获取除了前十条的新闻
	 */
	@Override
	public List<easybuy_news> getAfterNew(int cpage, int pageSize,String en_Title) {
		try {
			//打开数据库连接
			this.openConnection();
			//sql语句创建一个prepareStatement对象
			ps=con.prepareStatement("select top "+(cpage-0)*pageSize+" * from easybuy_news where en_title like ? and en_id not in(select top 10 en_id from easybuy_news order by en_create_time desc) order by en_create_time desc");
			ps.setString(1,"%"+en_Title+"%" );
			rs=ps.executeQuery();
			while(rs.next()){
				easybuy_news en=new easybuy_news();
				en.setEn_Id(rs.getInt("en_id"));
				en.setEn_Title(rs.getString("en_title"));
				en.setEn_Content(rs.getString("en_content"));
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化时间
				en.setEn_Create_time(sf.format(rs.getDate("en_create_time")));
				en.setEn_Click_Count(rs.getInt("en_click_count"));
				list.add(en);
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
	 * 功能:获取除了前十条新闻的总数量
	 */
	@Override
	public int getAfterNewsCount(String en_Title) {
		// TODO Auto-generated catch block
		String sql="select count(1) from easybuy_news where en_title like '%"+en_Title+"%' and en_id not in(select top 10 en_id from easybuy_news order by en_create_time desc)";
		return this.getCount(sql);
	}
	/**
	 * 功能:获取新闻的所有分类名称
	 */
	@Override
	public List<easybuy_news_type> getNews_Type() {
		// TODO Auto-generated method stub
		List<easybuy_news_type> list=new ArrayList<easybuy_news_type>();
		try {
			//打开数据库连接
			this.openConnection();
			//sql语句
			String sql="select ent_id,ent_name from easybuy_news_type";
			//创建一个prepareStatement对象
			ps= con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				easybuy_news_type ent = new easybuy_news_type();
				ent.setEnt_Id(rs.getInt("ent_id"));
				ent.setEnt_Name(rs.getString("ent_name"));
				list.add(ent);
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
	 * 功能:获取新闻的所有栏目名称
	 */
	@Override
	public List<easybuy_news_column> getNews_Column() {
		// TODO Auto-generated method stub
		List<easybuy_news_column> list=new ArrayList<easybuy_news_column>();
		try {
			//打开数据库连接
			this.openConnection();
			//sql语句
			String sql="select enc_id,enc_name from easybuy_news_column";
			//创建一个prepareStatement对象
			ps= con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				easybuy_news_column enc = new easybuy_news_column();
				enc.setEnc_Id(rs.getInt("enc_id"));
				enc.setEnc_Name(rs.getString("enc_name"));
				list.add(enc);
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
	 * 功能:模糊查询分页显示获取新闻的所有分类名称
	 */
	@Override
	public List<easybuy_news_type> getNews_Type(int cpage, int pageSize,
			String ent_Name) {
		// TODO Auto-generated method stub
		try {
			//打开数据库连接
			this.openConnection();
			//sql语句创建一个prepareStatement对象
			ps=con.prepareStatement("select top "+pageSize+" * from easybuy_news_type where ent_name like ? and ent_id not in(select top "+(cpage-1)*pageSize+" ent_id from easybuy_news_type order by ent_id desc) order by ent_id desc");
			ps.setString(1,"%"+ent_Name+"%" );
			rs=ps.executeQuery();
			while(rs.next()){
				easybuy_news_type ent=new easybuy_news_type();
				ent.setEnt_Id(rs.getInt("ent_id"));
				ent.setEnt_Name(rs.getString("ent_name"));
				ent.setEnt_Create_time(rs.getString("ent_create_time"));
				list1.add(ent);
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
		return list1;
	}
	/**
	 * 功能:获取分类的总数量
	 */
	@Override
	public int getTypesCount(String ent_Name) {
		// TODO Auto-generated method stub
		//sql语句
		String sql="select count(1) from easybuy_news_type where ent_name like '%"+ent_Name+"%'";
		return this.getCount(sql);
	}
	/**
	 * 功能:模糊查询分页获取新闻的所有栏目名称
	 */
	@Override
	public List<easybuy_news_column> getNews_Column(int cpage, int pageSize,
			String enc_Name) {
		// TODO Auto-generated method stub
		try {
			//打开数据库连接
			this.openConnection();
			//sql语句创建一个prepareStatement对象
			ps=con.prepareStatement("select top "+pageSize+" * from easybuy_news_column where enc_name like ? and enc_id not in(select top "+(cpage-1)*pageSize+" enc_id from easybuy_news_column order by enc_id desc) order by enc_id desc");
			ps.setString(1,"%"+enc_Name+"%" );
			rs=ps.executeQuery();
			while(rs.next()){
				easybuy_news_column enc=new easybuy_news_column();
				enc.setEnc_Id(rs.getInt("enc_id"));
				enc.setEnc_Name(rs.getString("enc_name"));
				enc.setEnc_Create_time(rs.getString("enc_create_time"));
				list2.add(enc);
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
		return list2;
	}
	/**
	 * 功能:获取栏目的总数量
	 */
	@Override
	public int getColumnsCount(String enc_Name) {
		// TODO Auto-generated method stub
		//sql语句
		String sql="select count(1) from easybuy_news_column where enc_name like '%"+enc_Name+"%'";
		return this.getCount(sql);
	}
	/**
	 * 功能:根据Id删除分类
	 */
	@Override
	public int delNewsTypeById(String ent_Id) {
		// TODO Auto-generated method stub
		//sql语句
		String sql="delete from easybuy_news_type where ent_id in("+ent_Id+")";
		return this.executeSql(sql);
	}
	/**
	 * 功能:删除全部分类
	 */
	@Override
	public int delNewsTypeAll() {
		// TODO Auto-generated method stub
		//sql语句
		String sql="delete from easybuy_news_type";
		return this.executeSql(sql);
	}
	/**
	 * 功能:根据Id删除栏目
	 */
	@Override
	public int delNewsColumnById(String enc_Id) {
		// TODO Auto-generated method stub
		//sql语句
		String sql="delete from easybuy_news_column where enc_id in("+enc_Id+")";
		return this.executeSql(sql);
	}
	/**
	 * 功能:删除全部栏目
	 */
	@Override
	public int delNewsColumnAll() {
		// TODO Auto-generated method stub
		//sql语句
		String sql="delete from easybuy_news_column";
		return this.executeSql(sql);
	}
	/**
	 * 功能:添加分类信息
	 */
	@Override
	public int addNewType(easybuy_news_type ent) {
		// TODO Auto-generated method stub
		//sql语句
		String sql="insert easybuy_news_type values(?,?)";
		//创建List集合对象
		List<Object> list = new ArrayList<Object>();
		list.add(ent.getEnt_Name());
		list.add(ent.getEnt_Create_time());
		return this.executeUpdata(sql, list);
	}
	/**
	 * 功能:添加栏目信息
	 */
	@Override
	public int addNewColumn(easybuy_news_column enc) {
		// TODO Auto-generated method stub
		//sql语句
		String sql="insert easybuy_news_column values(?,?)";
		//创建List集合对象
		List<Object> list = new ArrayList<Object>();
		list.add(enc.getEnc_Name());
		list.add(enc.getEnc_Create_time());
		return this.executeUpdata(sql, list);
	}
	/**
	 * 功能:根据id回显分类信息
	 */
	@Override
	public easybuy_news_type getNewsTypeById(int ent_Id) {
		// TODO Auto-generated method stub
		easybuy_news_type ent=new easybuy_news_type();
		try {
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps=con.prepareStatement("select * from easybuy_news_type where ent_id=?");
			ps.setInt(1,ent_Id);
			rs=ps.executeQuery();
			while(rs.next()){
				ent.setEnt_Id(rs.getInt("ent_id"));
				ent.setEnt_Name(rs.getString("ent_name"));
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化时间
				ent.setEnt_Create_time(sf.format(rs.getDate("ent_create_time")));
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
		return ent;
	}
	/**
	 * 功能:根据id回显栏目信息
	 */
	@Override
	public easybuy_news_column getNewsColumnById(int enc_Id) {
		// TODO Auto-generated method stub
		easybuy_news_column enc=new easybuy_news_column();
		try {
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps=con.prepareStatement("select * from easybuy_news_column where enc_id=?");
			ps.setInt(1,enc_Id);
			rs=ps.executeQuery();
			while(rs.next()){
				enc.setEnc_Id(rs.getInt("enc_id"));
				enc.setEnc_Name(rs.getString("enc_name"));
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化时间
				enc.setEnc_Create_time(sf.format(rs.getDate("enc_create_time")));
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
		return enc;
	}
	/**
	 * 功能:更新分类信息
	 */
	@Override
	public int updateNewType(easybuy_news_type ent) {
		// TODO Auto-generated method stub
		//sql语句
		String sql="update easybuy_news_type set ent_name=?,ent_create_time=? where ent_id=?";
		//创建List集合对象
		List<Object> list = new ArrayList<Object>();
		list.add(ent.getEnt_Name());
		list.add(ent.getEnt_Create_time());
		list.add(ent.getEnt_Id());
		return this.executeUpdata(sql, list);
	}
	/**
	 * 功能:更新栏目信息
	 */
	@Override
	public int updateNewColumn(easybuy_news_column enc) {
		// TODO Auto-generated method stub
		//sql语句
		String sql="update easybuy_news_column set enc_name=?,enc_create_time=? where enc_id=?";
		//创建List集合对象
		List<Object> list = new ArrayList<Object>();
		list.add(enc.getEnc_Name());
		list.add(enc.getEnc_Create_time());
		list.add(enc.getEnc_Id());
		return this.executeUpdata(sql, list);
	}
	/**
	 * 功能:判断是否存在指定分类信息
	 */
	@Override
	public boolean boolType(String ent_Name) {
		// TODO Auto-generated method stub
		//标记一下
		boolean flag=false;
		//sql语句
		String sql = "select * from easybuy_news_type where ent_name=?";
		try {
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps = con.prepareStatement(sql);
			ps.setString(1, ent_Name);
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
	 * 功能:判断是否存在指定栏目信息
	 */
	@Override
	public boolean boolColumn(String enc_Name) {
		// TODO Auto-generated method stub
		//标记一下
		boolean flag=false;
		//sql语句
		String sql = "select * from easybuy_news_column where enc_name=?";
		try {
			//打开数据库连接
			this.openConnection();
			//创建一个prepareStatement对象
			ps = con.prepareStatement(sql);
			ps.setString(1, enc_Name);
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
