package cn.jbit.dao;

import java.util.List;
import cn.jbit.entity.easybuy_assess;
import cn.jbit.entity.easybuy_product;

/**
 * @author 任锯东
 */
public interface easybuy_product_Dao {

	public List<easybuy_product> Geteasybuy_product();//获取所有商品信息
	public List<easybuy_assess> getAssess(int cpage, int pageSize,int ep_id);//根据Id获取单个商品的评价信息
	public int getAssessCount(int ep_id);//根据Id获取单个商品的评价信息数量
	public int addAssess(easybuy_assess assess);//添加评价信息
	public List<easybuy_assess> getAssessProduct(int cpage, int pageSize,String ea_assess);//分页获取所有商品的评价信息
	public int getAssessCount(String ea_assess);//获取所有商品的评价信息数量
	public int delAssessById(String ea_upid);//根据id删除评价信息
	public int delAssessAll();//删除全部评价信息
}
