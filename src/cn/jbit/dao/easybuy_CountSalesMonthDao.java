package cn.jbit.dao;

import java.util.List;
import cn.jbit.entity.easybuy_product;

/**
 * @author 任锯东
 */
public interface easybuy_CountSalesMonthDao {

	public List<easybuy_product> countProduct();//查看月销量排名
}
