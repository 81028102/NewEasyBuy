package cn.jbit.bizimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.jbit.biz.easybuy_order_detailBiz;
import cn.jbit.dao.easybuy_order_detailDao;
import cn.jbit.daoimpl.easybuy_order_detailDaoImpl;
import cn.jbit.entity.easybuy_order_detail;

/**
 * @author 任锯东
 * @date 2016-5-20 下午5:44:50
 */
@Service
public class easybuy_order_detailBizImpl implements easybuy_order_detailBiz{

	@Autowired
	easybuy_order_detailDao eodd=new easybuy_order_detailDaoImpl();
	
	@Override
	public int adddetail(easybuy_order_detail eoo) {
		// TODO Auto-generated method stub
		return eodd.adddetail(eoo);
	}

	@Override
	public int delOrderDetailById(int eo_id) {
		// TODO Auto-generated method stub
		return eodd.delOrderDetailById(eo_id);
	}
}
