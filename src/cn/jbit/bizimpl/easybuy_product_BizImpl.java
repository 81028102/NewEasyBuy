package cn.jbit.bizimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.jbit.biz.easybuy_product_Biz;
import cn.jbit.dao.easybuy_product_Dao;
import cn.jbit.daoimpl.easybuy_product_DaoImpl;
import cn.jbit.entity.easybuy_assess;

/**
 * @author 任锯东
 */
@Service
public class easybuy_product_BizImpl implements easybuy_product_Biz{	

	@Autowired
	easybuy_product_Dao epd=new	easybuy_product_DaoImpl();

	@Override
	public List<easybuy_assess> getAssess(int cpage, int pageSize,int ep_id) {
		// TODO Auto-generated method stub
		return epd.getAssess(cpage,pageSize,ep_id);
	}
	@Override
	public int getAssessCount(int ep_id) {
		// TODO Auto-generated method stub
		return epd.getAssessCount(ep_id);
	}
	@Override
	public int addAssess(easybuy_assess assess) {
		// TODO Auto-generated method stub
		return epd.addAssess(assess);
	}
	@Override
	public List<easybuy_assess> getAssessProduct(int cpage, int pageSize,String ea_assess) {
		// TODO Auto-generated method stub
		return epd.getAssessProduct(cpage, pageSize, ea_assess);
	}
	@Override
	public int getAssessCount(String ea_assess) {
		// TODO Auto-generated method stub
		return epd.getAssessCount(ea_assess);
	}
	@Override
	public int delAssessById(String ea_upid) {
		// TODO Auto-generated method stub
		return epd.delAssessById(ea_upid);
	}
	@Override
	public int delAssessAll() {
		// TODO Auto-generated method stub
		return epd.delAssessAll();
	}
}