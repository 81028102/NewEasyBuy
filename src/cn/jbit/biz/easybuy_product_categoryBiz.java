package cn.jbit.biz;

import java.util.List;
import cn.jbit.entity.easybuy_product_category;

/**
 * @author 任锯东
 */
public interface easybuy_product_categoryBiz {

	public List<easybuy_product_category> getCategories(int id);//获取一级商品分类
	public easybuy_product_category getCategory(int epc_id);//获取指定Id分类
	public int addCategory(easybuy_product_category epc);//添加商品分类
	public int delCategory(int epc_id);//删除指定Id分类
	public int updateCategory(easybuy_product_category epc);//更新分类信息
	public List<easybuy_product_category> getAll();//获取所有分类信息
	public boolean boolcate(String epc_name);//判断是否存在指定分类信息
	public int getparent(int id);//根据二级分类ID获取一级分类ID
	public int updateCategoryCount(easybuy_product_category epc);//更新分类点击数量
	public List<easybuy_product_category> getAlls(int cpage, int pageSize,String epc_name);//分页获取所有分类信息
	public int getCategoryCount(String epc_name);//获取分类总数
}
