package cn.jbit.bizimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.jbit.biz.easybuy_payBiz;
import cn.jbit.dao.easybuy_payDao;
import cn.jbit.daoimpl.easybuy_payDaoImpl;
import cn.jbit.entity.easybuy_pay;
import cn.jbit.entity.easybuy_product;

/**
 * @author 任锯东
 */
@Service
public class easybuy_payBizImpl implements easybuy_payBiz{
	
	@Autowired
	easybuy_payDao epi=new easybuy_payDaoImpl();
	
	@Override
	public String getEasyBuyPay(String eu_user_name, String paycardid,String paypwd) {
		// TODO Auto-generated method stub
		return epi.getEasyBuyPay(eu_user_name, paycardid,paypwd);
	}

	@Override
	public int getEasyBuyPayProduct(easybuy_pay epay) {
		// TODO Auto-generated method stub
		return epi.getEasyBuyPayProduct(epay);
	}

	@Override
	public int getEasyBuyPayCount(easybuy_product epdt) {
		// TODO Auto-generated method stub
		return epi.getEasyBuyPayCount(epdt);
	}
}
