package cn.jbit.bizimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.jbit.biz.easybuy_product_categoryBiz;
import cn.jbit.dao.easybuy_product_categoryDao;
import cn.jbit.daoimpl.easybuy_product_categoryDaoImpl;
import cn.jbit.entity.easybuy_product_category;

/**
 * @author 任锯东
 */
@Service
public class easybuy_product_categoryBizImpl implements easybuy_product_categoryBiz{
	
	@Autowired
	easybuy_product_categoryDao epcb=new easybuy_product_categoryDaoImpl();
	
	@Override
	public List<easybuy_product_category> getAll() {
		// TODO Auto-generated method stub
		return epcb.getAll();
	}
	
	@Override
	public List<easybuy_product_category> getCategories(int id) {
		// TODO Auto-generated method stub
		return epcb.getCategories(id);
	}

	@Override
	public easybuy_product_category getCategory(int epc_id) {
		// TODO Auto-generated method stub
		return epcb.getCategory(epc_id);
	}

	@Override
	public int addCategory(easybuy_product_category epc) {
		// TODO Auto-generated method stub
		return epcb.addCategory(epc);
	}

	@Override
	public int delCategory(int epc_id) {
		// TODO Auto-generated method stub
		return epcb.delCategory(epc_id);
	}

	@Override
	public int updateCategory(easybuy_product_category epc) {
		// TODO Auto-generated method stub
		return epcb.updateCategory(epc);
	}

	@Override
	public boolean boolcate(String epc_name) {
		// TODO Auto-generated method stub
		return epcb.boolcate(epc_name);
	}

	@Override
	public int getparent(int id) {
		// TODO Auto-generated method stub
		return epcb.getparent(id);
	}

	@Override
	public int updateCategoryCount(easybuy_product_category epc) {
		// TODO Auto-generated method stub
		return epcb.updateCategoryCount(epc);
	}

	@Override
	public List<easybuy_product_category> getAlls(int cpage, int pageSize,
			String epc_name) {
		// TODO Auto-generated method stub
		return epcb.getAlls(cpage, pageSize, epc_name);
	}

	@Override
	public int getCategoryCount(String epc_name) {
		// TODO Auto-generated method stub
		return epcb.getCategoryCount(epc_name);
	}
}
