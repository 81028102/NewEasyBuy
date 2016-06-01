package cn.jbit.dao;

import java.util.List;
import cn.jbit.entity.easybuy_order;
import cn.jbit.entity.easybuy_order_detail;
import cn.jbit.entity.easybuy_order_status;

/**
 * @author 任锯东
 */
public interface easybuy_orderDao {

	public int countNums(int eo_id,String eu_user_id);//获取指定用户的订单数量
	public int countNum(int eu_id);//获取所有订单的数量
	public List<easybuy_order> getUserOrders(int pageSize,int pageCurr,String eu_user_id);//分页获取指定用户的订单集合
	public List<easybuy_order> getEasybuy_orders(int pageSize,int pageCurrpage,int status,String id);//分页获取所有订单
	public easybuy_order getEasybuy_order(int eo_id);//获取单个订单信息
	public int add(easybuy_order eo);//添加订单信息
	public List<easybuy_order_detail> getDetail();//获取所有订单详情
	public List<easybuy_order> getDateOrder(int pageSize,int pageCurr, String date,String date1,String eu_user_id);//按购买日期查询订单
	public int getNoProducts(String eo_user_id);//查询为发货的商品
	public List<easybuy_order_status> getOrder_Status();//获取所有订单状态
	public int updateStatus(easybuy_order eo);//修改订单状态
	public int delOrderById(int eo_id);//根据id删除订单
}
