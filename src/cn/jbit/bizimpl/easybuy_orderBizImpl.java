package cn.jbit.bizimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.jbit.biz.easybuy_orderBiz;
import cn.jbit.dao.easybuy_orderDao;
import cn.jbit.daoimpl.easybuy_orderDaoImpl;
import cn.jbit.entity.easybuy_order;
import cn.jbit.entity.easybuy_order_status;

/**
 * @author 任锯东
 */
@Service
public class easybuy_orderBizImpl implements easybuy_orderBiz{
	
	@Autowired
	easybuy_orderDao eod=new easybuy_orderDaoImpl();
	
	@Override
	public List<easybuy_order> getEasybuy_orders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<easybuy_order_status> getOrder_Status() {
		// TODO Auto-generated method stub
		return eod.getOrder_Status();
	}
	
	@Override
	public int updateStatus(easybuy_order eo) {
		// TODO Auto-generated method stub
		return eod.updateStatus(eo);
	}

	@Override
	public int delOrderById(int eo_id) {
		// TODO Auto-generated method stub
		return eod.delOrderById(eo_id);
	}
}
