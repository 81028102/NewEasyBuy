package cn.jbit.dao;

import java.util.List;
import cn.jbit.entity.easybuy_news;
import cn.jbit.entity.easybuy_news_column;
import cn.jbit.entity.easybuy_news_type;

/**
 * @author 任锯东
 */
public interface easybuy_newsDao {

	public List<easybuy_news> getEasybuy_news(int cpage, int pageSize,String en_Title);//获取新闻
	public int getNewsCount(String en_Title);//获取新闻的总数量
	public List<easybuy_news> getEasybuy_Click(int cpage, int pageSize,String en_Title);//模糊查询分页显示新闻点击率排行
	public List<easybuy_news> getTen();//获取最近十条新闻
	public easybuy_news getNews(int en_id);//获取指定ID新闻
	public int addNew(easybuy_news en);//添加新闻
	public int delNew(String en_id);//删除新闻
	public int updateNew(easybuy_news en);//更新新闻信息
	public List<easybuy_news> AllNews();//获取所有的新闻信息
	public int updateNewCount(easybuy_news en);//更新新闻点击数量
	public int delNewsAll();//删除全部新闻
	public List<easybuy_news> getAfterNew(int cpage, int pageSize,String en_Title);//分页获取除啦前十条的新闻
	public int getAfterNewsCount(String en_Title);//获取除啦前十条新闻的总数量
	public List<easybuy_news_type> getNews_Type();//获取新闻的所有分类名称
	public List<easybuy_news_column> getNews_Column();//获取新闻的所有栏目名称
	public List<easybuy_news_type> getNews_Type(int cpage, int pageSize,String ent_Name);//模糊查询分页显示获取新闻的所有分类名称
	public int getTypesCount(String ent_Name);//获取分类的总数量
	public int delNewsTypeById(String ent_Id);//根据Id删除分类
	public int delNewsTypeAll();//删除全部分类
	public int addNewType(easybuy_news_type ent);//添加分类信息
	public easybuy_news_type getNewsTypeById(int ent_Id);//获取指定ID分类
	public int updateNewType(easybuy_news_type ent);//更新分类信息
	public boolean boolType(String ent_Name);//判断是否存在指定分类信息
	public List<easybuy_news_column> getNews_Column(int cpage, int pageSize,String enc_Name);//模糊查询分页获取新闻的所有栏目名称
	public int getColumnsCount(String enc_Name);//获取栏目的总数量
	public int delNewsColumnById(String enc_Id);//根据Id删除栏目
	public int delNewsColumnAll();//删除全部栏目
	public int addNewColumn(easybuy_news_column enc);//添加栏目信息
	public easybuy_news_column getNewsColumnById(int enc_Id);//获取指定ID栏目
	public int updateNewColumn(easybuy_news_column enc);//更新栏目信息
	public boolean boolColumn(String enc_Name);//判断是否存在指定栏目信息
}
