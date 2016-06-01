package cn.jbit.bizimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.jbit.biz.easybuy_productBiz;
import cn.jbit.dao.easybuy_productDao;
import cn.jbit.daoimpl.easybuy_productDaoImpl;
import cn.jbit.entity.easybuy_product;

/**
 * @author 任锯东
 */
@Service
public class easybuy_productBizImpl implements easybuy_productBiz {
	
	@Autowired
	easybuy_productDao epb=new easybuy_productDaoImpl();

	@Override
	public List<easybuy_product> getEasybuy_products(int pageSize, int pageCount,String category,int id) {
		// TODO Auto-generated method stub
		return epb.getEasybuy_products(pageSize, pageCount,category,id);
	}

	@Override
	public easybuy_product getEasybuy_product(int ep_id) {
		// TODO Auto-generated method stub
		return epb.getEasybuy_product(ep_id);
	}

	@Override
	public int addProduct(easybuy_product ep) {
		// TODO Auto-generated method stub
		return epb.addProduct(ep);
	}

	@Override
	public int delProduct(String ep_id) {
		// TODO Auto-generated method stub
		return epb.delProduct(ep_id);
	}

	@Override
	public int updateProduct(easybuy_product ep,String type) {
		// TODO Auto-generated method stub
		return epb.updateProduct(ep,type);
	}

	@Override
	public int getProductCount() {
		// TODO Auto-generated method stub
		return epb.getProductCount();
	}

	@Override
	public int getTypeCount(int id, String type) {
		// TODO Auto-generated method stub
		return epb.getTypeCount(id, type);
	}

	@Override
	public List<easybuy_product> getInfo(String info,String infos,String infoss,String ep_price,String ep_address,String ep_description) {
		// TODO Auto-generated method stub
		return epb.getInfo(info,infos,infoss,ep_price,ep_address,ep_description);
	}

	@Override
	public List<easybuy_product> getInfos(String info) {
		// TODO Auto-generated method stub
		return epb.getInfos(info);
	}

	@Override
	public List<easybuy_product> getResemble(CharSequence ep_name, float ep_price,
			CharSequence ep_address, String ep_description) {
		// TODO Auto-generated method stub
		return epb.getResemble(ep_name,ep_price,ep_address,ep_description);
	}

	@Override
	public int getSalesCount(String ep_name) {
		// TODO Auto-generated method stub
		return epb.getSalesCount(ep_name);
	}

	@Override
	public List<easybuy_product> getSalesAllProduct(int cpage, int pageSize,
			String ep_name) {
		// TODO Auto-generated method stub
		return epb.getSalesAllProduct(cpage, pageSize, ep_name);
	}
}