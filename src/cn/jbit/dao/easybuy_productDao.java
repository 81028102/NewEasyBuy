package cn.jbit.dao;

import java.util.List;
import cn.jbit.entity.easybuy_product;

/**
 * @author 任锯东
 */
public interface easybuy_productDao {

	public int getProductCount();//获取商品总数量
	public int getTypeCount(int id,String type);//根据类别ID获取商品数量
	public List<easybuy_product> getEasybuy_products(int pageSize,int pageCount,String category,int id);//分页查询商品信息
	public easybuy_product getEasybuy_product(int ep_id);//获得指定ID商品信息
	public int addProduct(easybuy_product ep);//添加商品信息
	public int delProduct(String ep_id);//删除商品信息
	public int updateProduct(easybuy_product ep, String type);//修改商品信息
	public List<easybuy_product> getInfo(String info,String infos,String infoss,String ep_price,String ep_address,String ep_description);//模糊查询商品分类信息(根据一级分类模糊,二级分类模糊,商品名称模糊)
	public List<easybuy_product> getInfos(String  info);//模糊查询商品信息
	public List<easybuy_product> getResemble(CharSequence ep_name, float ep_price,
			CharSequence ep_address, String ep_description);//模糊相似查询商品信息
	public int getSalesCount(String ep_name);//获取销量总数
	public  List<easybuy_product> getSalesAllProduct(int cpage, int pageSize,String ep_name);//分页获取销售信息
}