package cn.jbit.biz;

import cn.jbit.entity.easybuy_order_detail;

/**
 * @author 任锯东
 */
public interface easybuy_order_detailBiz {

	public int adddetail(easybuy_order_detail eoo);//添加订单信息
	public int delOrderDetailById(int eo_id);//根据Id删除订单详情
}
