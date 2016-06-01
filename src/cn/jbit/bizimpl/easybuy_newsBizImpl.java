package cn.jbit.bizimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.jbit.biz.easybuy_newsBiz;
import cn.jbit.dao.easybuy_newsDao;
import cn.jbit.daoimpl.easybuy_newsDaoImpl;
import cn.jbit.entity.easybuy_news;
import cn.jbit.entity.easybuy_news_column;
import cn.jbit.entity.easybuy_news_type;

/**
 * @author 任锯东
 */
@Service
public class easybuy_newsBizImpl implements easybuy_newsBiz {
	
	@Autowired
	easybuy_newsDao en=new easybuy_newsDaoImpl();

	@Override
	public List<easybuy_news> getEasybuy_news(int cpage, int pageSize,String en_Title) {
		// TODO Auto-generated method stub
		return en.getEasybuy_news(cpage,pageSize,en_Title);
	}

	@Override
	public easybuy_news getNews(int en_id) {
		// TODO Auto-generated method stub
		return en.getNews(en_id);
	}

	@Override
	public int addNew(easybuy_news en) {
		// TODO Auto-generated method stub
		return this.en.addNew(en);
	}

	@Override
	public int delNew(String en_id) {
		// TODO Auto-generated method stub
		return en.delNew(en_id);
	}

	@Override
	public int updateNew(easybuy_news en) {
		// TODO Auto-generated method stub
		return this.en.updateNew(en);
	}

	@Override
	public List<easybuy_news> getTen() {
		// TODO Auto-generated method stub
		return en.getTen();
	}

	@Override
	public List<easybuy_news> AllNews() {
		// TODO Auto-generated method stub
		return en.AllNews();
	}

	@Override
	public int getNewsCount(String en_Title) {
		// TODO Auto-generated method stub
		return en.getNewsCount(en_Title);
	}

	@Override
	public int updateNewCount(easybuy_news en) {
		// TODO Auto-generated method stub
		return this.en.updateNewCount(en);
	}

	@Override
	public List<easybuy_news> getEasybuy_Click(int cpage, int pageSize,
			String en_Title) {
		// TODO Auto-generated method stub
		return en.getEasybuy_Click(cpage, pageSize, en_Title);
	}

	@Override
	public int delNewsAll() {
		// TODO Auto-generated method stub
		return en.delNewsAll();
	}

	@Override
	public List<easybuy_news> getAfterNew(int cpage, int pageSize,
			String en_Title) {
		// TODO Auto-generated method stub
		return en.getAfterNew(cpage, pageSize, en_Title);
	}

	@Override
	public int getAfterNewsCount(String en_Title) {
		// TODO Auto-generated method stub
		return en.getAfterNewsCount(en_Title);
	}

	@Override
	public List<easybuy_news_type> getNews_Type() {
		// TODO Auto-generated method stub
		return en.getNews_Type();
	}

	@Override
	public List<easybuy_news_column> getNews_Column() {
		// TODO Auto-generated method stub
		return en.getNews_Column();
	}

	@Override
	public List<easybuy_news_type> getNews_Type(int cpage, int pageSize,
			String ent_Name) {
		// TODO Auto-generated method stub
		return en.getNews_Type(cpage, pageSize, ent_Name);
	}

	@Override
	public int getTypesCount(String ent_Name) {
		// TODO Auto-generated method stub
		return en.getTypesCount(ent_Name);
	}

	@Override
	public List<easybuy_news_column> getNews_Column(int cpage, int pageSize,
			String enc_Name) {
		// TODO Auto-generated method stub
		return en.getNews_Column(cpage, pageSize, enc_Name);
	}

	@Override
	public int getColumnsCount(String enc_Name) {
		// TODO Auto-generated method stub
		return en.getColumnsCount(enc_Name);
	}

	@Override
	public int delNewsTypeById(String ent_Id) {
		// TODO Auto-generated method stub
		return en.delNewsTypeById(ent_Id);
	}

	@Override
	public int delNewsTypeAll() {
		// TODO Auto-generated method stub
		return en.delNewsTypeAll();
	}

	@Override
	public int delNewsColumnById(String enc_Id) {
		// TODO Auto-generated method stub
		return en.delNewsColumnById(enc_Id);
	}

	@Override
	public int delNewsColumnAll() {
		// TODO Auto-generated method stub
		return en.delNewsColumnAll();
	}

	@Override
	public int addNewType(easybuy_news_type ent) {
		// TODO Auto-generated method stub
		return en.addNewType(ent);
	}

	@Override
	public int addNewColumn(easybuy_news_column enc) {
		// TODO Auto-generated method stub
		return en.addNewColumn(enc);
	}

	@Override
	public easybuy_news_type getNewsTypeById(int ent_Id) {
		// TODO Auto-generated method stub
		return en.getNewsTypeById(ent_Id);
	}

	@Override
	public easybuy_news_column getNewsColumnById(int enc_Id) {
		// TODO Auto-generated method stub
		return en.getNewsColumnById(enc_Id);
	}

	@Override
	public int updateNewType(easybuy_news_type ent) {
		// TODO Auto-generated method stub
		return en.updateNewType(ent);
	}

	@Override
	public int updateNewColumn(easybuy_news_column enc) {
		// TODO Auto-generated method stub
		return en.updateNewColumn(enc);
	}

	@Override
	public boolean boolType(String ent_Name) {
		// TODO Auto-generated method stub
		return en.boolType(ent_Name);
	}

	@Override
	public boolean boolColumn(String enc_Name) {
		// TODO Auto-generated method stub
		return en.boolColumn(enc_Name);
	}
}
