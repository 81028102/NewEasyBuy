package cn.jbit.biz;

import java.util.List;
import cn.jbit.entity.easybuy_order;
import cn.jbit.entity.easybuy_order_status;

/**
 * @author 任锯东
 */
public interface easybuy_orderBiz {

	public List<easybuy_order> getEasybuy_orders();//获取所有订单
	public List<easybuy_order_status> getOrder_Status();//获取所有订单状态
	public int updateStatus(easybuy_order eo);//修改订单状态
	public int delOrderById(int eo_id);//根据Id删除订单
}
