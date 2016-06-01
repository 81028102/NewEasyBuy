package cn.jbit.biz;

import cn.jbit.entity.easybuy_pay;
import cn.jbit.entity.easybuy_product;

/**
 * @author 任锯东
 */
public interface easybuy_payBiz {

	public String getEasyBuyPay(String eu_user_name,String paycardid,String paypwd);//根据eu_user_id,payid获取指定用户信息(银行卡号)
	public int getEasyBuyPayCount(easybuy_product epdt);//根据(用户姓名)购买金额(实现付款功能商品数量减去)
	public int getEasyBuyPayProduct(easybuy_pay epay);//根据(用户姓名)购买金额(实现付款功能)
}
