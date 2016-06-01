package cn.jbit.serviceimpl;

import java.util.Iterator;
import cn.jbit.dao.easybuy_CountSalesMonthDao;
import cn.jbit.service.StatisticsService;

public class StatisticsServiceImpl implements StatisticsService{
	
	private easybuy_CountSalesMonthDao dao;
	
	public easybuy_CountSalesMonthDao getDao() {
		return dao;
	}
	public void setDao(easybuy_CountSalesMonthDao dao) {
		this.dao = dao;
	}
	/**
	 * 功能:查看月销量最高的
	 */
	@Override
	public void ouputProduct() {
		// TODO Auto-generated method stub
		@SuppressWarnings("rawtypes")
		Iterator it = dao.countProduct().iterator();
		System.out.print("商品名称\t" + "商品价格\t" + "商品库存\t" + "商品产地\t" + "商品月销量\n");
		while(it.hasNext()){
			Object[] o = (Object[])it.next();
			//商品名称
			String ep_name = (String)o[0];
			//商品价格
			Double ep_price = (Double)o[1];
			//商品库存
			Integer ep_stock = (Integer)o[2];
			//商品地址
			String ep_address = (String)o[3];
			//商品月销量
			Integer ep_sales = (Integer)o[4];
			System.out.print(ep_name + "\t" + ep_price + "\t" +ep_stock+ "\t" + ep_address + "\t" + ep_sales);
		}
	}
}
